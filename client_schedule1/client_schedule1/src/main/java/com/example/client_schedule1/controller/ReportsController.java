package com.example.client_schedule1.controller;

import com.example.client_schedule1.helper.DAO.CustomerDAO;
import com.example.client_schedule1.helper.Impl.CustomerDAOImpl;
import com.example.client_schedule1.model.Appointment;
import com.example.client_schedule1.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.ResourceBundle;
/**This is the reports controller (lambda expression)*/
public class ReportsController implements Initializable {
    public ComboBox<Month> monthBox;
    public ComboBox<String> typeBox;
    public ComboBox<Customer> customerBox;
    public Label typeLabels;
    public Label customersLabel;
    public ObservableList<Appointment> appointmentByMonth = FXCollections.observableArrayList();
    public ObservableList<String> types = FXCollections.observableArrayList();
    public static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    public Button closeButton;
    /**This grabs all users from the main menu.
     * @param Appointments all users from the main menu*/
    public static void setAllAppointments(ObservableList<Appointment> Appointments){
        allAppointments = Appointments;
    }
    /**This initializes the form (lambda expression).
     * This method contains a Lambda expression that listens for an event from the customer combo box then counts
     * all appointments that customer is scheduled for. This eliminates an event handler for the customer combo box. It is located
     * on the bottom of the code block.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set month combo box items
        ObservableList<Month> months = FXCollections.observableArrayList();
        months.addAll(Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER,
                Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
        monthBox.setItems(months);
        //set customer combo box items
        try {
            CustomerDAO customerDAO = new CustomerDAOImpl();
            ObservableList<Customer> customers = FXCollections.observableArrayList(customerDAO.getAll());
            customerBox.setItems(customers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Lambda expression for customer box event listener
        customerBox.setOnAction((event) -> {
            //count number of appointments that have the selected customer
            int numberOfCustomers = 0;
            for(Appointment appointment: allAppointments){
                if(appointment.getCustomer() == customerBox.getSelectionModel().getSelectedItem().getId()){
                    ++numberOfCustomers;
                }
            }
            customersLabel.setText(String.valueOf(numberOfCustomers));
        });
    }
    /**This counts the number of appointments with the particular type during a certain month.*/
    public void onTypeBox(ActionEvent actionEvent) {
        //get count number of appointments that have the selected type
        int numberOfAppointments = 0;
        for(Appointment appointment: appointmentByMonth){
            if(appointment.getType().strip().toUpperCase().equals(typeBox.getSelectionModel().getSelectedItem())){
                ++numberOfAppointments;
            }
        }
        typeLabels.setText(String.valueOf(numberOfAppointments));
    }
    /**This grabs the appointment during that month and populates the type combo box with types during that month*/
    public void onMonthBox(ActionEvent actionEvent) {
        appointmentByMonth.clear();
        types.clear();
        //set items for type combo box
        //add all appointments in selected month to the appointment by month list
        for(Appointment appointment: allAppointments){
            if(appointment.getStart().toLocalDateTime().getMonth().equals(monthBox.getSelectionModel().getSelectedItem())){
                appointmentByMonth.add(appointment);
            }
        }
        //iterate through the appointment by month list to collect all different types
        for(Appointment appointment: appointmentByMonth){
            String type = appointment.getType().strip().toUpperCase();
            boolean typeExists = false;
            for(String type1: types){
                if(type1.equals(type)){
                    typeExists = true;
                    break;
                }
            }
            if(!typeExists){
                types.add(type);
            }
        }
        typeBox.setItems(types);
    }
    /**closes the form.*/
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
