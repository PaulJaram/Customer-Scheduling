package com.example.client_schedule1.controller;

import com.example.client_schedule1.helper.DAO.AppointmentDAO;
import com.example.client_schedule1.helper.DAO.ContactDAO;
import com.example.client_schedule1.helper.DAO.CustomerDAO;
import com.example.client_schedule1.helper.DAO.UserDAO;
import com.example.client_schedule1.helper.Impl.AppointmentDAOImpl;
import com.example.client_schedule1.helper.Impl.ContactDAOImpl;
import com.example.client_schedule1.helper.Impl.CustomerDAOImpl;
import com.example.client_schedule1.helper.Impl.UserDAOImpl;
import com.example.client_schedule1.model.Appointment;
import com.example.client_schedule1.model.Contact;
import com.example.client_schedule1.model.Customer;
import com.example.client_schedule1.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
/**The add appointment controller*/
public class AddAppointmentController implements Initializable {

    public ComboBox<Contact> contactBox;
    public TextArea descriptionTA;
    public TextField titleTF;
    public TextField typeTF;
    public TextField locationTF;
    public Button submitButton;
    public ComboBox<LocalTime> startTimes;
    public ComboBox<LocalTime> endTimes;
    public DatePicker Dates;
    public Button cancelButton;
    public static User currentUser = null;
    public ComboBox<Customer> customerBox;
    public ComboBox<User> userBox;
    public static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    /**this grabs appointments from the main menu controller
     * @param Appointments all appointments from main menu*/
    public static void setAppointments(ObservableList<Appointment> Appointments){
        allAppointments = Appointments;
    }
    /**This gets the current user from the main menu controller.
     * @param user current user*/
    public static void getCurrentUser(User user){
        currentUser = user;
    }
    /**This grabs data from all fields on the form and updates the appointment
     * @param actionEvent event*/
    public void onSubmitButton(ActionEvent actionEvent) throws SQLException {
        //create new appointment object
        Appointment newAppointment = new Appointment();
        //gather info from text boxes
        String title = titleTF.getText();
        String description = descriptionTA.getText();
        String location = locationTF.getText();
        String  type = typeTF.getText();
        //get id from contact box and set contact of appointment to value of contact box
        int contactID = 0;
        if(!contactBox.getSelectionModel().isEmpty()) {
            contactID = contactBox.getSelectionModel().getSelectedItem().getId();
        }
        //get selection from customer box and set customer of appointment to id of customer
        int customerID = 0;
        if(!customerBox.getSelectionModel().isEmpty()){
            customerID = customerBox.getSelectionModel().getSelectedItem().getId();
        }
        //get selection from user box and set user of appointment to id of user
        int userID = 0;
        if(!userBox.getSelectionModel().isEmpty()) {
            userID = userBox.getSelectionModel().getSelectedItem().getId();
        }
        //get current date
        LocalDateTime ldt = LocalDateTime.now();
        //convert current date to utc
        ZonedDateTime currentZDT = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime currentUTCZDT = ZonedDateTime.ofInstant(currentZDT.toInstant(), ZoneId.of("UTC"));
        Timestamp currentDateTime = Timestamp.valueOf(currentUTCZDT.toLocalDateTime());
        //gather local time and date time variables for start and create start timestamp variable
        LocalTime startTime = startTimes.getSelectionModel().getSelectedItem();
        LocalDate startDate = Dates.getValue();
        LocalDateTime startLDT = LocalDateTime.now();
        if(!startTimes.getSelectionModel().isEmpty() &&
                !(Dates.getValue() == null)) {
            startLDT = LocalDateTime.of(startDate, startTime);
        }
        //make timestamp for overlap comparison at end of method
        Timestamp startStamp = Timestamp.valueOf(startLDT);
        //convert start to UTC
        ZonedDateTime localStartZDT = ZonedDateTime.of(startLDT, ZoneId.systemDefault());
        ZonedDateTime utcStartZDT = ZonedDateTime.ofInstant(localStartZDT.toInstant(), ZoneId.of("UTC"));
        LocalDateTime utcStartLDT = utcStartZDT.toLocalDateTime();
        Timestamp start = Timestamp.valueOf(utcStartLDT);
        //gather local time and date time variables for end and create end timestamp variable
        LocalTime endTime = endTimes.getSelectionModel().getSelectedItem();
        LocalDate endDate = Dates.getValue();
        LocalDateTime endLDT = LocalDateTime.now();
        if(!endTimes.getSelectionModel().isEmpty() &&
                !(Dates.getValue() == null)) {
            endLDT = LocalDateTime.of(endDate, endTime);
        }
        //make timestamp for overlap comparison at end of method
        Timestamp endStamp = Timestamp.valueOf(endLDT);
        //convert end to UTC
        ZonedDateTime localEndZDT = ZonedDateTime.of(endLDT, ZoneId.systemDefault());
        ZonedDateTime utcEndZDT = ZonedDateTime.ofInstant(localEndZDT.toInstant(), ZoneId.of("UTC"));
        LocalDateTime utcEndLDT = utcEndZDT.toLocalDateTime();
        Timestamp end = Timestamp.valueOf(utcEndLDT);
        //check to see if start and end times are within business hours
        ZonedDateTime businessStartZDT = ZonedDateTime.ofInstant(utcStartZDT.toInstant(), ZoneId.of("US/Eastern"));
        LocalTime etStartLT = businessStartZDT.toLocalTime();
        ZonedDateTime businessEndZDT = ZonedDateTime.ofInstant(utcEndZDT.toInstant(), ZoneId.of("US/Eastern"));
        LocalTime etEndLT = businessEndZDT.toLocalTime();
        LocalTime businessStart = LocalTime.of(8, 0);
        LocalTime businessEnd = LocalTime.of(22, 0);
        //check to see if there is any empty text fields
        if( titleTF.getText().isBlank() ||
                descriptionTA.getText().isBlank() ||
                locationTF.getText().isBlank() ||
                typeTF.getText().isBlank() ||
                contactBox.getSelectionModel().isEmpty() ||
                customerBox.getSelectionModel().isEmpty() ||
                userBox.getSelectionModel().isEmpty() ||
                Dates.getValue() == null ||
                startTimes.getSelectionModel().isEmpty() ||
                endTimes.getSelectionModel().isEmpty()){
            Alert empty = new Alert(Alert.AlertType.ERROR);
            empty.setContentText("Please fill out every field!");
            empty.showAndWait();
        }
        //check if description is too big for database
        else if(description.length() > 50) {
            Alert tooBig = new Alert(Alert.AlertType.ERROR);
            tooBig.setContentText("Description has too many characters! 50 character limit");
            tooBig.showAndWait();
        }
        //check if date pick is before today's date
        else if(Dates.getValue().isBefore(LocalDateTime.now().toLocalDate())){
            Alert past = new Alert(Alert.AlertType.ERROR);
            past.setContentText("Date cannot be set before today");
            past.showAndWait();
        }
        //check to see if start time is before or equal to end time
        else if(start.after(end) || start.equals(end)){
            Alert startEnd = new Alert(Alert.AlertType.ERROR);
            startEnd.setContentText("Start Time has to be before End Time");
            startEnd.showAndWait();
        }
        //check business hours
        else if(etStartLT.isBefore(businessStart)){
            Alert businessStartHours = new Alert(Alert.AlertType.ERROR);
            businessStartHours.setContentText("The start time entered for the appointment is before business hours (8:00 a.m US/Eastern)");
            businessStartHours.showAndWait();
        }
        else if(etEndLT.isAfter(businessEnd)){
            Alert businessEndHours = new Alert(Alert.AlertType.ERROR);
            businessEndHours.setContentText("The end time entered for the appointment is after business hours (10:00 p.m US/Eastern)");
            businessEndHours.showAndWait();
        }
        //if appointment is within business hours continue
        else {
            //check to see if this appointment overlaps with other appointments for customers
            boolean overlapAppointment = false;
            for(Appointment appointment: allAppointments){
                if(customerID == appointment.getCustomer()) {
                    if (startStamp.equals(appointment.getStart()) || endStamp.equals(appointment.getEnd()) ||
                            startStamp.equals(appointment.getEnd()) || endStamp.equals(appointment.getStart()) ||
                            (startStamp.after(appointment.getStart()) && startStamp.before(appointment.getEnd())) ||
                            (endStamp.after(appointment.getStart()) && endStamp.before(appointment.getEnd())) ||
                            (appointment.getStart().after(startStamp) && appointment.getStart().before(endStamp)) ||
                            (appointment.getEnd().after(startStamp) && appointment.getEnd().before(endStamp))){
                        overlapAppointment = true;
                        Alert overlap = new Alert(Alert.AlertType.ERROR);
                        overlap.setTitle("Overlapping Appointments");
                        overlap.setContentText("There is at least one overlapping appointment for Customer: " + customerID);
                        overlap.showAndWait();
                    }
                }
            }
            if(!overlapAppointment) {
                //set appointment values from text fields
                newAppointment.setTitle(title);
                newAppointment.setDescription(description);
                newAppointment.setLocation(location);
                newAppointment.setType(type);
                newAppointment.setCreatedBy(currentUser.getName());
                newAppointment.setDateCreated(currentDateTime);
                newAppointment.setUpdatedBy(currentUser.getName());
                newAppointment.setLastUpdated(currentDateTime);
                newAppointment.setContact(contactID);
                newAppointment.setCustomer(customerID);
                newAppointment.setUser(userID);
                //set start and end values for appointment
                newAppointment.setStart(start);
                newAppointment.setEnd(end);
                //update DAO
                AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
                appointmentDAO.insert(newAppointment);
                try {
                    //get current stage and close it
                    Stage stage = (Stage) submitButton.getScene().getWindow();
                    stage.close();
                    //open stage for Main Menu
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/client_schedule1/MainMenu.fxml"));
                    Parent root = loader.load();

                    Stage newStage = new Stage();
                    stage.setTitle("Main Menu");
                    newStage.setScene(new Scene(root));
                    newStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**This initializes the window for the form*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //get and set data for contact combo box selections
        try {
            //get and set data for contact combo box selections
            ContactDAO contactDAO = new ContactDAOImpl();
            ObservableList<Contact> contactList = null;
            contactList = FXCollections.observableArrayList(contactDAO.getAll());
            contactBox.setItems(contactList);
            //get and set data for customer combo box selections
            CustomerDAO customerDAO = new CustomerDAOImpl();
            ObservableList<Customer> customerList = null;
            customerList = FXCollections.observableArrayList(customerDAO.getAll());
            customerBox.setItems(customerList);
            //get and set data for user combo box selections
            UserDAO userDAO = new UserDAOImpl();
            ObservableList<User> userList = null;
            userList = FXCollections.observableArrayList(userDAO.getAll());
            userBox.setItems(userList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Set items for start and end time combo boxes
        ObservableList<LocalTime> times = FXCollections.observableArrayList();
        times.add(LocalTime.parse("08:00"));
        times.add(LocalTime.parse("08:30"));
        times.add(LocalTime.parse("09:00"));
        times.add(LocalTime.parse("09:30"));
        times.add(LocalTime.parse("10:00"));
        times.add(LocalTime.parse("10:30"));
        times.add(LocalTime.parse("11:00"));
        times.add(LocalTime.parse("11:30"));
        times.add(LocalTime.parse("12:00"));
        times.add(LocalTime.parse("12:30"));
        times.add(LocalTime.parse("13:00"));
        times.add(LocalTime.parse("13:30"));
        times.add(LocalTime.parse("14:00"));
        times.add(LocalTime.parse("14:30"));
        times.add(LocalTime.parse("15:00"));
        times.add(LocalTime.parse("15:30"));
        times.add(LocalTime.parse("16:00"));
        times.add(LocalTime.parse("16:30"));
        times.add(LocalTime.parse("17:00"));
        times.add(LocalTime.parse("17:30"));
        times.add(LocalTime.parse("18:00"));
        times.add(LocalTime.parse("18:30"));
        times.add(LocalTime.parse("19:00"));
        times.add(LocalTime.parse("19:30"));
        times.add(LocalTime.parse("20:00"));
        times.add(LocalTime.parse("20:30"));
        times.add(LocalTime.parse("21:00"));
        times.add(LocalTime.parse("21:30"));
        times.add(LocalTime.parse("22:00"));
        //Make sure selections are within business hours in eastern standard time
        TimeZone localZDT = TimeZone.getTimeZone( ZoneId.systemDefault());
        TimeZone estZDT = TimeZone.getTimeZone("US/Eastern");
        int offset = localZDT.getRawOffset() - estZDT.getRawOffset();
        for(int i = 0; i < times.size(); i++){
            times.set(i, times.get(i).plus(offset, ChronoUnit.MILLIS));
        }
        startTimes.setItems(times);
        endTimes.setItems(times);
    }
    /**This closes the form*/
    public void onCancel(ActionEvent actionEvent) {
        //close window and redirect to the main menu
        try {
            //get current stage and close it
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
            //open stage for Main Menu
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/client_schedule1/MainMenu.fxml"));
            Parent root = loader.load();

            Stage newStage = new Stage();
            stage.setTitle("Main Menu");
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
