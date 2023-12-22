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
import java.util.List;
import java.util.ResourceBundle;
/**The add customer controller*/
public class AddCustomerController implements Initializable {

    public ComboBox<FirstLevelDivision> divisionBox;
    public TextField nameTF;
    public TextField addressTF;
    public TextField postalCodeTF;
    public TextField phoneTF;
    public ComboBox<Country> countryBox;
    public Button submitButton;
    public Button cancelButton;
    public static User currentUser = null;
    /**this gets the current user from the main controller
     * @param user the current user*/
    public static void getCurrentUser(User user){
        currentUser = user;
    }
    /**This pre-populates the division combo box*/
    public void onCountryBox(ActionEvent actionEvent) throws SQLException {
        //auto-populate combo box for divisions based off country selection
        FirstLevelDivisionDAO divisionDAO = new FirstLevelDivisionDAOImpl();
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
    }
    /**This grabs data from all fields on the form and adds a customer*/
    public void onSubmitButton(ActionEvent actionEvent) throws SQLException {
        //add new customer object
        Customer newCustomer = new Customer();
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
        //get current date
        LocalDateTime ldt = LocalDateTime.now();
        //convert current date to utc
        ZonedDateTime currentZDT = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime currentUTCZDT = ZonedDateTime.ofInstant(currentZDT.toInstant(), ZoneId.of("UTC"));
        Timestamp currentDateTime = Timestamp.valueOf(currentUTCZDT.toLocalDateTime());
        //regex string for phone comparison
        String regexStr = "^[0-9\\-]*$";
        //check to see if there is no null values
        if(nameTF.getText().isEmpty() ||
                addressTF.getText().isEmpty() ||
                postalCodeTF.getText().isEmpty() ||
                divisionBox.getSelectionModel().isEmpty() ||
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
            //set values for the new customer
            newCustomer.setName(name);
            newCustomer.setAddress(address);
            newCustomer.setPostalCode(postalCode);
            newCustomer.setPhone(phone);
            newCustomer.setDivisionID(divisionID);
            newCustomer.setCreatedBy(currentUser.getName());
            newCustomer.setDateCreated(currentDateTime);
            newCustomer.setUpdatedBy(currentUser.getName());
            newCustomer.setLastUpdated(currentDateTime);
            //add customer to database
            CustomerDAO customerDAO = new CustomerDAOImpl();
            customerDAO.insert(newCustomer);
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
    /**This closes the window*/
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
    /**This initializes the add customer form.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //populate combo box selections for countries
        try {
            CountryDAO countryDAO = new CountryDAOImpl();
            ObservableList<Country> countries = FXCollections.observableArrayList(countryDAO.getAll());
            countryBox.setItems(countries);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
