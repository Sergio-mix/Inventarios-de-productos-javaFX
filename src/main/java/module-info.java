 module com.example.reto3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;
    requires com.jfoenix;


     opens com.example.reto3 to javafx.fxml;
     exports com.example.reto3.model;
     exports com.example.reto3;
     opens com.example.reto3.model;
 }