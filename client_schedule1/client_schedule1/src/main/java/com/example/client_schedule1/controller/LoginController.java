package com.example.client_schedule1.controller;

import com.example.client_schedule1.helper.DAO.UserDAO;
import com.example.client_schedule1.helper.Impl.UserDAOImpl;
import com.example.client_schedule1.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
/**This is the login controller*/
public class LoginController implements Initializable {
    public Button loginButton;
    public TextField usernameTF;
    public PasswordField passwordTF;
    public Label Username;
    public Label Password;
    public Label schedulingSystem;
    public Label location;
    public Label locationLabel;
    public static boolean userLogin = false;
    /**This passes along a boolean value to the main controller to see if a user just logged in.
     * @return userLogin*/
    public static boolean userLogin(){
        return userLogin;
    }
    /**this sets the attribute userlogin.
     * @param bool the boolean value userlogin is set to*/
    public static void setUserLogin(boolean bool){
        userLogin = bool;
    }
    /**This checks to see if username and password are correct. This method writes to a file
     * to track user login attempts. */
    public void onLogin(ActionEvent actionEvent) throws SQLException, IOException {
        //provide file path for log-in attempts
        FileWriter fw = new FileWriter("login_activity.txt", true);
        PrintWriter pw = new PrintWriter(fw);
        //translate errors to french
        ResourceBundle rb = ResourceBundle.getBundle("Nat", Locale.getDefault());
        //get text from user name and password fields
        String userName = usernameTF.getText();
        String password = passwordTF.getText();
        //iterate through users to find user with the username provided
        UserDAO userDAO = new UserDAOImpl();
        List<User> users = userDAO.getAll();
        boolean userFound = false;
        boolean userNull = false;
        for(User user: users){
            //if username field is blank show error message
            if(userName.isBlank()){
                userNull = true;
                Alert nullUser = new Alert(Alert.AlertType.ERROR);
                nullUser.setContentText("no username entered");
                //translate to french
                if(Locale.getDefault().getLanguage().equals("fr")){
                    nullUser.setContentText(rb.getString("usernameBlank"));
                }
                nullUser.showAndWait();
                break;
            }
            //find user with the username provided
            if(user.getName().equals(userName)){
                userFound = true;
                //check to see if user entered correct password for username
                if(user.getPassword().equals(password)){
                    //get current user and hand it off to the main menu controller
                    com.example.client_schedule1.controller.MainMenuController.getCurrentUser(user);
                    //set boolean userLogin to true for main menu controller
                    userLogin = true;
                    //close window and open main menu
                    try {
                        //get current date
                        LocalDateTime ldt = LocalDateTime.now();
                        //convert current date to utc
                        ZonedDateTime currentZDT = ZonedDateTime.of(ldt, ZoneId.systemDefault());
                        ZonedDateTime currentUTCZDT = ZonedDateTime.ofInstant(currentZDT.toInstant(), ZoneId.of("UTC"));
                        Timestamp currentDateTime = Timestamp.valueOf(currentUTCZDT.toLocalDateTime());
                        //get string to print to file
                        String userString = user.toString();
                        String currentDate = currentDateTime.toString();
                        String loginSuccess = " Logged in Successfully: ";
                        //print to file
                        pw.println( "User ID: " + userString + loginSuccess + currentDate + " UTC");
                        pw.close();
                        //get current stage and close it
                        Stage stage = (Stage) loginButton.getScene().getWindow();
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
                else{
                    //get current date
                    LocalDateTime ldt = LocalDateTime.now();
                    //convert current date to utc
                    ZonedDateTime currentZDT = ZonedDateTime.of(ldt, ZoneId.systemDefault());
                    ZonedDateTime currentUTCZDT = ZonedDateTime.ofInstant(currentZDT.toInstant(), ZoneId.of("UTC"));
                    Timestamp currentDateTime = Timestamp.valueOf(currentUTCZDT.toLocalDateTime());
                    //get string to print to file
                    String userString = user.toString();
                    String currentDate = currentDateTime.toString();
                    String loginSuccess = " Invalid Log-In Attempt: ";
                    //print to file
                    pw.println("User ID: " + userString + loginSuccess + currentDate + " UTC");
                    pw.close();
                    //show alert
                    Alert wrongPassword = new Alert(Alert.AlertType.ERROR);
                    wrongPassword.setTitle("Wrong Password");
                    wrongPassword.setContentText("The password that was entered for is incorrect");
                    //translate to french
                    if(Locale.getDefault().getLanguage().equals("fr")){
                        wrongPassword.setTitle(rb.getString("wrongPasswordTitle"));
                        wrongPassword.setContentText(rb.getString("WrongPassword"));
                    }
                    wrongPassword.showAndWait();
                }
                break;
            }
        }
        //if username is not found but was entered show error message
        if(!userFound && !userNull) {
            Alert noUser = new Alert(Alert.AlertType.ERROR);
            noUser.setTitle("User Not Found");
            noUser.setContentText("The username that was entered was not found");
            //translate to french
            if(Locale.getDefault().getLanguage().equals("fr")){
                noUser.setTitle(rb.getString("userNotFoundTitle"));
                noUser.setContentText(rb.getString("userNotFound"));
            }
            noUser.showAndWait();
        }
    }
    /**This initializes the login form.
     * The login form can be translated to French.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //show zone id
        location.setText(ZoneId.systemDefault().toString());
        //translate login to french
        ResourceBundle rb = ResourceBundle.getBundle("Nat", Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("fr")){
            schedulingSystem.setText(rb.getString("SchedulingSystem"));
            Username.setText(rb.getString("Username"));
            Password.setText(rb.getString("Password"));
            loginButton.setText(rb.getString("Login"));
            locationLabel.setText(rb.getString("Location"));
        }


    }
}
