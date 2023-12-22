package com.example.client_schedule1.controller;

import com.example.client_schedule1.helper.DAO.ContactDAO;
import com.example.client_schedule1.helper.Impl.ContactDAOImpl;
import com.example.client_schedule1.model.Appointment;
import com.example.client_schedule1.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
/**This is the contact schedules controller (lambda expression)*/
public class ContactSchedulesController implements Initializable {
    public ComboBox<Contact> contactBox;
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
    public Button closeButton;
    public ObservableList<Appointment> appointmentByContact = FXCollections.observableArrayList();
    public static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    /**This grabs all appointments from the main menu controller.
     * @param Appointments all appointments from main menu*/
    public static void setAllAppointments(ObservableList<Appointment> Appointments){
        allAppointments = Appointments;
    }
    /**This initializes the form (lambda expression).
     * This method uses a lambda expression to listen for an event from the contact combo box then auto-poulates the table based off
     * the box selection. The lambda expression eliminates an event handler for the contact box. It is located on the bottom of the
     * code block.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //get and set data for contact combo box selections
        try {
            ContactDAO contactDAO = new ContactDAOImpl();
            ObservableList<Contact> contactList = FXCollections.observableArrayList();
            contactList = FXCollections.observableArrayList(contactDAO.getAll());
            contactBox.setItems(contactList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Import Data to the appointments table
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

        //Lambda expression for contact box event listener
        contactBox.setOnAction((event) -> {
            appointmentByContact.clear();
            //get all appointments for contact selected and display it in table view
            for(Appointment appointment: allAppointments){
                if(appointment.getContact() == contactBox.getSelectionModel().getSelectedItem().getId()){
                    appointmentByContact.add(appointment);
                }
            }
            appointmentsTable.setItems(appointmentByContact);
        });
    }

    /**This closes the window.*/
    public void onClose(ActionEvent actionEvent) {
        //close window
        try {
            //get current stage and close it
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
