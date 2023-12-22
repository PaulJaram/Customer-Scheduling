module com.example.client_schedule1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.client_schedule1 to javafx.fxml;
    opens com.example.client_schedule1.model to javafx.fxml;
    exports com.example.client_schedule1;
    exports com.example.client_schedule1.controller;
    exports com.example.client_schedule1.model;
    opens com.example.client_schedule1.controller to javafx.fxml;
}