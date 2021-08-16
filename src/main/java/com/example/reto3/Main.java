package com.example.reto3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    /**
     * @author Sergio Alejandro Hernandez Zambrano - Gurpo: G35
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/com/example/reto3/index.fxml"));
            Pane ventana = (Pane) loader.load();
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Inventario de productos");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
