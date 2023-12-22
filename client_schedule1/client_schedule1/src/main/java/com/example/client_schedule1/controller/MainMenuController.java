package com.example.client_schedule1.controller;

import com.example.client_schedule1.helper.DAO.AppointmentDAO;
import com.example.client_schedule1.helper.DAO.CustomerDAO;
import com.example.client_schedule1.helper.Impl.AppointmentDAOImpl;
import com.example.client_schedule1.helper.Impl.CustomerDAOImpl;
import com.example.client_schedule1.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.client_schedule1.model.Appointment;
import com.example.client_schedule1.model.Customer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;
/**This is the main menu controller*/
public class MainMenuController implements Initializable{
    public TableView<Appointment> appointmentsTable;
    public TableColumn<Appointment, Integer> appointmentID;
    public TableColumn<Appointment, String> title;
    public TableColumn<Appointment, String> description;
    public TableColumn<Appointment, String> location;
    public TableColumn<Appointment, Integer> contact;
    public TableColumn<Appointment, String> type;
    public TableColumn<Appointment, Timestamp> start;
    public TableColumn<Appointment, Timestamp> end;
    public TableColumn<Appointment, Integer> customerID;
    public TableColumn<Appointment, Integer> userID;
    public Button updateAppointment;
    public Button deleteAppointment;
    public TableView<Customer> customerTable;
    public TableColumn<Customer, Integer> customer;
    public TableColumn<Customer, String> name;
    public TableColumn<Customer, String> address;
    public TableColumn<Customer, String> postalCode;
    public TableColumn<Customer, Integer> division;
    public TableColumn<Customer, String> phone;
    public Button addCustomer;
    public Button updateCustomer;
    public Button deleteCustomer;
    public RadioButton weekRadio;
    public Button addAppointment;
    public ComboBox<String> monthBox;
    public ToggleGroup appointmentTableTG;
    public RadioButton monthRadio;
    public RadioButton viewAll;
    public Button scheduleContacts;
    public Button reportsButton;
    private ObservableList<Appointment> Appointments = FXCollections.observableArrayList();
    private ObservableList<Customer> Customers = FXCollections.observableArrayList();
    private ObservableList<Appointment> appointmentsByMonth = FXCollections.observableArrayList();
    private ObservableList<Appointment> appointmentsByWeek = FXCollections.observableArrayList();
    private Appointment selectedAppointment = null;
    private Customer selectedCustomer = null;
    private static User currentUser = null;
    /**This gets the current user from the login controller.
     * @param user the current user.*/
    public static void getCurrentUser(User user){
        currentUser = user;
    }
    /**This opens the update appointment form.
     * This method hands of the selected appointment and opens update appointment form.*/
    public void onUpdateAppointment(ActionEvent actionEvent) {
        //hand off appointments to Update Appointment Controller
        com.example.client_schedule1.controller.UpdateAppointmentController.setAppointments(Appointments);
        //hand off current user to Update Appointment Controller
        com.example.client_schedule1.controller.UpdateAppointmentController.getCurrentUser(currentUser);
        //get appointment object from table
        selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
        if(selectedAppointment != null) {
            //transfer appointment object to UpdatAppointment controller
            com.example.client_schedule1.controller.UpdateAppointmentController.getSelectedAppointment(selectedAppointment);
            try {
                //get current stage and close it
                Stage currentStage = (Stage) updateAppointment.getScene().getWindow();
                currentStage.close();
                //open Update Appointment form
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/client_schedule1/UpdateAppointment.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Update Appointment");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**This deletes an appointment.*/
    public void onDeleteAppointment(ActionEvent actionEvent) throws SQLException {
        //get selected appointment from appointment table
        selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
        //Confirm the user wants to delete appointment
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Appointment");
        alert.setContentText("Delete Appointment: " + selectedAppointment.getID() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            //remove appointment from table and array list
            Appointments.remove(selectedAppointment);
            appointmentsByMonth.remove(selectedAppointment);
            appointmentsByWeek.remove(selectedAppointment);
            //delete Appointment from database
            AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
            appointmentDAO.delete(selectedAppointment);
            //Tell User that Appointment was deleted
            Alert appoinmentDeleted = new Alert(Alert.AlertType.INFORMATION);
            appoinmentDeleted.setContentText("Appointment: " + selectedAppointment.getID() + " Type: " + selectedAppointment.getType() + " was deleted");
            appoinmentDeleted.showAndWait();
        }
    }
    /**This opens the add appointment form.*/
    public void onAddAppointment(ActionEvent actionEvent) {
        //Hand off all appointments to Add Appointment Controller
        AddAppointmentController.setAppointments(Appointments);
        //hand off current user to add appointment controller
        AddAppointmentController.getCurrentUser(currentUser);
        try {
            //get current stage and close it
            Stage currentStage = (Stage) addAppointment.getScene().getWindow();
            currentStage.close();
            //open Add Appointment form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/client_schedule1/AddAppointment.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Appointment");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /**This adds the add customer form.*/
    public void onAddCustomer(ActionEvent actionEvent) {
        //hand off current user to add customer user to Add Customer Controller
        AddCustomerController.getCurrentUser(currentUser);
        try {
            //get current stage and close it
            Stage currentStage = (Stage) addCustomer.getScene().getWindow();
            currentStage.close();
            //open Add Customer form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/client_schedule1/AddCustomer.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Customer");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /**This opens the update customer form.
     * This event handler hands off selected customer and opens update customer form.*/
    public void onUpdateCustomer(ActionEvent actionEvent) {
        //hand off curent user to Update Customer Controller
        com.example.client_schedule1.controller.UpdateCustomerController.getCurrentUser(currentUser);
        //get Customer object from customer table
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if(selectedCustomer != null){
            try {
                //transfer Customer Object from table to the UpdateCustomer Controller
                com.example.client_schedule1.controller.UpdateCustomerController.getSelectedCustomer(selectedCustomer);
                //get current stage and close it
                Stage currentStage = (Stage) updateAppointment.getScene().getWindow();
                currentStage.close();
                //open Update Customer form
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/client_schedule1/UpdateCustomer.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Update Customer");
                stage.setScene(new Scene(root));
                stage.show();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /**This deletes a customer.
     * This deletes all associated appointments of a customer first then deletes the customer.*/
    public void onDeleteCustomer(ActionEvent actionEvent) throws SQLException {
        //get selected object from customer table
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        //confirm that the user wants to delete customer and associated appointments
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Customer");
        alert.setContentText("All associated appointments will be deleted, delete CUSTOMER: " + selectedCustomer.getId() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            //make sure that the customers appointments are deleted first
            AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
            //dont use enhanced for loop because of concurrent modification exception
            for (int i = 0; i < Appointments.size(); i++) {
                if (Appointments.get(i).getCustomer() == selectedCustomer.getId()) {
                    //delete appointment from database
                    appointmentDAO.delete(Appointments.get(i));
                    //delete appointment from table
                    Appointments.remove(Appointments.get(i));
                    --i;
                }
            }
            //remove from appointments by month list
            for (int i = 0; i < appointmentsByMonth.size(); i++) {
                if (appointmentsByMonth.get(i).getCustomer() == selectedCustomer.getId()) {
                    //delete appointment from table
                    appointmentsByMonth.remove(appointmentsByMonth.get(i));
                }
            }
            //remove from appointments by week list
            for (int i = 0; i < appointmentsByWeek.size(); i++) {
                if (appointmentsByWeek.get(i).getCustomer() == selectedCustomer.getId()) {
                    //delete appointment from table
                    appointmentsByWeek.remove(appointmentsByWeek.get(i));
                }
            }
            //delete customer from database
            CustomerDAO customerDAO = new CustomerDAOImpl();
            customerDAO.delete(selectedCustomer);
            //remove selected customer from table and array list
            Customers.remove(selectedCustomer);
            //show user that customer was deleted
            Alert customerDelete = new Alert(Alert.AlertType.INFORMATION);
            customerDelete.setContentText("CUSTOMER: " + selectedCustomer.getId() + " and associated appointments are deleted!");
            customerDelete.showAndWait();
        }
    }
    /**This allows user to view all appointments in table.*/
    public void onViewAll(ActionEvent actionEvent) {
        monthBox.setDisable(true);
        appointmentsTable.setItems(Appointments);
    }
    /**This selects the month to view appointments*/
    public void onMonthBox(ActionEvent actionEvent) {
        appointmentsByMonth.clear();
        for(Appointment appointment: Appointments){
            if(appointment.getStart().toLocalDateTime().getMonth().equals(Month.valueOf(monthBox.getSelectionModel().getSelectedItem()))){
                appointmentsByMonth.add(appointment);
            }
        }
        appointmentsTable.setItems(appointmentsByMonth);
    }
    /**This allows user to view appointments by month*/
    public void onMonthRadio(ActionEvent actionEvent) {
        monthBox.setDisable(false);
        appointmentsTable.setItems(appointmentsByMonth);
    }
    /**This allows users to view the appointments by the current week*/
    public void onWeekRadio(ActionEvent actionEvent) {
        monthBox.setDisable(true);
        appointmentsByWeek.clear();
        for(Appointment appointment: Appointments){
            if(appointment.getStart().toLocalDateTime().isAfter(LocalDateTime.now().with(DayOfWeek.MONDAY)) &&
                    appointment.getEnd().toLocalDateTime().isBefore(LocalDateTime.now().with(DayOfWeek.SUNDAY))){
                appointmentsByWeek.add(appointment);
            }
        }
        appointmentsTable.setItems(appointmentsByWeek);
    }
    /**This initializes the form.
     * This sets all table items and checks if there is an upcoming appointment on log-in */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //view all radio is selected so disable view by month box
        monthBox.setDisable(true);
        //import months to month box
        ObservableList<String> months = FXCollections.observableArrayList();
        months.addAll("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");
        monthBox.setItems(months);
        try {
            //Gather Appointment and Customer Data from the Database
            AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
            CustomerDAO customerDAO = new CustomerDAOImpl();
            Appointments = FXCollections.observableArrayList(appointmentDAO.getAll());
            Customers = FXCollections.observableArrayList(customerDAO.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //convert start and end from appointment from utc to local
        for(Appointment appointment:Appointments) {
            //start
            LocalDateTime start = appointment.getStart().toLocalDateTime();
            ZonedDateTime startUTCZDT = ZonedDateTime.of(start, ZoneId.of("UTC"));
            ZonedDateTime startLocalZDT = ZonedDateTime.ofInstant(startUTCZDT.toInstant(), ZoneId.systemDefault());
            Timestamp startStamp = Timestamp.valueOf(startLocalZDT.toLocalDateTime());
            appointment.setStart(startStamp);
            //end
            LocalDateTime end = appointment.getEnd().toLocalDateTime();
            ZonedDateTime endUTCZDT = ZonedDateTime.of(end, ZoneId.of("UTC"));
            ZonedDateTime endLocalZDT = ZonedDateTime.ofInstant(endUTCZDT.toInstant(), ZoneId.systemDefault());
            Timestamp endStamp = Timestamp.valueOf(endLocalZDT.toLocalDateTime());
            appointment.setEnd(endStamp);
        }
        //Import Data to the appointments table
        appointmentsTable.setItems(Appointments);
        appointmentID.setCellValueFactory(new PropertyValueFactory<>("iD"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customer"));
        userID.setCellValueFactory(new PropertyValueFactory<>("user"));
        //Import Data to the customer table
        customerTable.setItems(Customers);
        customer.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        division.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        //Check to see if an upcoming appointment is 15 minutes within the time of logging in, send message if so or if not
        boolean userLoggedIn = LoginController.userLogin();
        if(userLoggedIn){
            boolean upcomingAppointment = false;
            //make sure just on login
            LoginController.setUserLogin(false);
            for(Appointment appointment: Appointments){
                if(appointment.getStart().toLocalDateTime().isBefore(LocalDateTime.now().plusMinutes(15)) &&
                        !appointment.getStart().toLocalDateTime().isBefore(LocalDateTime.now())){
                    upcomingAppointment = true;
                    Alert upcomingAlert = new Alert(Alert.AlertType.INFORMATION);
                    upcomingAlert.setContentText("UPCOMING APPOINTMENT: Appointment: " + appointment.getID() + " Date: " + appointment.getStart().toLocalDateTime().toLocalDate().toString() + " Time: "
                            + appointment.getStart().toLocalDateTime().toLocalTime().toString());
                    upcomingAlert.showAndWait();
                }
            }
            if(!upcomingAppointment){
                Alert noUpcoming = new Alert(Alert.AlertType.INFORMATION);
                noUpcoming.setContentText("No Upcoming Appointments within 15 minutes");
                noUpcoming.showAndWait();
            }
        }
    }
    /**This opens the Contact Schedules form.*/
    public void onScheduleContacts(ActionEvent actionEvent) {
        //hand off appointments list to contact schedules controller
        ContactSchedulesController.setAllAppointments(Appointments);
        //Open Contact Schedules Report
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/client_schedule1/ContactSchedules.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Contact Schedules");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /**This opens the report form.*/
    public void onReportsButton(ActionEvent actionEvent) {
        //hand off appointments list to reports controller
        com.example.client_schedule1.controller.ReportsController.setAllAppointments(Appointments);
        //Open Reports form
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/client_schedule1/Reports.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Reports");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
