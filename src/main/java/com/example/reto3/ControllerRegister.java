package com.example.reto3;

import com.example.reto3.model.Validaciones;
import com.example.reto3.model.dao.ProductoDao;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRegister implements Initializable {

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textPrecio;

    @FXML
    private TextField textInventario;

    @FXML
    private JFXButton buttonSalir;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Método para registrar un Producto
     *
     * @param event
     */
    @FXML
    private void registrar(ActionEvent event) {
        ControllerIndex controllerIndex = new ControllerIndex();
        try {
            int codigo = ProductoDao.getList().size() + 1;

            while (Validaciones.validar(codigo)) {
                codigo += 1;
            }

            String nombre = this.textNombre.getText();
            String precio = this.textPrecio.getText();
            String inventario = this.textInventario.getText();

            if (!nombre.equals("")
                    && !precio.equals("")
                    && !inventario.equals("")) {

                if (ProductoDao.agregar(codigo,
                        nombre, Float.parseFloat(precio),
                        Integer.parseInt(inventario))) {
                    controllerIndex.addRow();// agregar a la tabla
                    salir();
                    controllerIndex.mostrarAlertInfo("Producto agregado correctamente");
                } else {
                    controllerIndex.mostrarAlertError("Error");
                }

            } else {
                controllerIndex.mostrarAlertWarning("Todos los campos son obligatorios");
            }
        } catch (Exception ignored) {

        }
    }

    /**
     * Método para salir de la ventana
     */
    @FXML
    private void salir() {
        Stage stage = (Stage) this.buttonSalir.getScene().getWindow();
        stage.close();
    }
}