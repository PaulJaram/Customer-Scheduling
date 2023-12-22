package com.example.client_schedule1.controller;

import com.example.client_schedule1.helper.DAO.CountryDAO;
import com.example.client_schedule1.helper.DAO.CustomerDAO;
import com.example.client_schedule1.helper.DAO.FirstLevelDivisionDAO;
import com.example.client_schedule1.helper.Impl.CountryDAOImpl;
import com.example.client_schedule1.helper.Impl.CustomerDAOImpl;
import com.example.client_schedule1.helper.Impl.FirstLevelDivisionDAOImpl;
import com.example.client_schedule1.model.Country;
import com.example.client_schedule1.model.Customer;
import com.example.client_schedule1.model.FirstLevelDivision;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/**This is the Update Customer Controller*/
public class UpdateCustomerController implements Initializable {
    private static Customer selectedCustomer = null;
    public ComboBox<FirstLevelDivision> divisionBox;
    public TextField nameTF;
    public TextField addressTF;
    public TextField postalCodeTF;
    public TextField phoneTF;
    public ComboBox<Country> countryBox;
    public Button submitButton;
    public TextField idTF;
    public Button cancelButton;
    public static User currentUser = null;
    /**This gets the current user from the main menu.
     * @param user current user*/
    public static void getCurrentUser(User user){
        currentUser = user;
    }
    /**This gets the selected Customer from the main menu.
     * @param customer selected customer*/
    public static void getSelectedCustomer(Customer customer){
        selectedCustomer = customer;
    }
    /**This grabs all data from the text fields and updates a customer.*/
    public void onSubmitButton(ActionEvent actionEvent) throws SQLException {
        //get data from text fields
        String name = nameTF.getText();
        String address = addressTF.getText();
        String postalCode = postalCodeTF.getText();
        String phone = phoneTF.getText();
        //gather division data
        FirstLevelDivision division = divisionBox.getValue();
        int divisionID = 0;
        if(!divisionBox.getSelectionModel().getSelectedItem().toString().isBlank()){
            divisionID = division.getId();
        }
        //get current datetime
        LocalDateTime ldt = LocalDateTime.now();
        //convert current date to utc
        ZonedDateTime currentZDT = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime currentUTCZDT = ZonedDateTime.ofInstant(currentZDT.toInstant(), ZoneId.of("UTC"));
        Timestamp currentDateTime = Timestamp.valueOf(currentUTCZDT.toLocalDateTime());
        //regex string for phone comparison number and dashes only
        String regexStr = "^[0-9\\-]*$";
        //check to see if there is no null values
        if(nameTF.getText().isEmpty() ||
                addressTF.getText().isEmpty() ||
                postalCodeTF.getText().isEmpty() ||
                divisionBox.getSelectionModel().getSelectedItem().toString().isBlank() ||
                phoneTF.getText().isEmpty()){
            Alert empty = new Alert(Alert.AlertType.ERROR);
            empty.setContentText("Please fill out every field!");
            empty.showAndWait();
        }
        else if(!phoneTF.getText().strip().matches(regexStr)){
            Alert numberNoGood = new Alert(Alert.AlertType.ERROR);
            numberNoGood.setContentText("Phone number not valid, no parentheses, number and dashes only");
            numberNoGood.showAndWait();
        }
        else {
            //update values for the selected customer
            selectedCustomer.setName(name);
            selectedCustomer.setAddress(address);
            selectedCustomer.setPostalCode(postalCode);
            selectedCustomer.setPhone(phone);
            selectedCustomer.setDivisionID(divisionID);
            selectedCustomer.setUpdatedBy(currentUser.getName());
            selectedCustomer.setLastUpdated(currentDateTime);
            //update customer in database
            CustomerDAO customerDAO = new CustomerDAOImpl();
            customerDAO.update(selectedCustomer);
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
    /**This initializes the form.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //import data from selected customer into corresponding text fields
        String id = String.valueOf(selectedCustomer.getId());
        idTF.setText(id);
        nameTF.setText(selectedCustomer.getName());
        addressTF.setText(selectedCustomer.getAddress());
        postalCodeTF.setText(selectedCustomer.getPostalCode());
        phoneTF.setText((selectedCustomer.getPhone()));
        //populate combo box selections for countries and divisions
        try {
            CountryDAO countryDAO = new CountryDAOImpl();
            ObservableList<Country> countries = FXCollections.observableArrayList(countryDAO.getAll());
            countryBox.setItems(countries);
            //pre=select country for combo cox that matches customer to be updated
            FirstLevelDivisionDAO divisionDAO = new FirstLevelDivisionDAOImpl();
            FirstLevelDivision selectedDivision = divisionDAO.get(selectedCustomer.getDivisionID());
            Country selectedCountry = countryDAO.get(selectedDivision.getCountryID());
            countryBox.getSelectionModel().select(selectedCountry);
            //auto-populate combo box for divisions based off country selection
            List<FirstLevelDivision> divisions = divisionDAO.getAll();
            ObservableList<FirstLevelDivision> observableDivisions = FXCollections.observableArrayList();
            //iterate through divisions and grab all divisions that are in the selected country
            int countryID = countryBox.getSelectionModel().getSelectedItem().getId();
            for (FirstLevelDivision division : divisions) {
                if (division.getCountryID() == countryID) {
                    observableDivisions.add(division);
                }
            }
            divisionBox.setItems(observableDivisions);
            //pre-select division for combo box that matches customer to be updated
            divisionBox.getSelectionModel().select(selectedDivision);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    /**This pre-populates the division combo box*/
    public void onCountry(ActionEvent actionEvent) throws SQLException {
        //auto-populate combo box for divisions based off country selection
        FirstLevelDivisionDAO divisionDAO = new FirstLevelDivisionDAOImpl();
        List<FirstLevelDivision> divisions = divisionDAO.getAll();
        ObservableList<FirstLevelDivision> observableDivisions = FXCollections.observableArrayList();
        //iterate through divisions and grab all divisions that are in the selected country
        int countryID = countryBox.getSelectionModel().getSelectedItem().getId();
        if(!countryBox.getSelectionModel().isEmpty()) {
            for (FirstLevelDivision division : divisions) {
                if (division.getCountryID() == countryID) {
                    observableDivisions.add(division);
                }
            }
        }
        divisionBox.setItems(observableDivisions);
    }
    /**This closes the form*/
    public void onCancel(ActionEvent actionEvent) {
        //close window and redirect to the main menu
        try {
            //get current stage and close it
            Stage stage = (Stage) cancelButton.getScene().getWindow();
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
