package com.example.client_schedule1;

import com.example.client_schedule1.helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

/** This class creates an app that schedules appointments*/
public class Main extends Application {
    /** This method shows the Login Form.
     * @param stage This is the stage to start*/
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/client_schedule1/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    /** This method is the first class to run.
     * The method opens a database connection, launches the app, and closes the database connection when finished.*/
    public static void main(String[] args) {
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();
    }
}