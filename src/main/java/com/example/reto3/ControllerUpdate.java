package com.example.reto3;

import com.example.reto3.model.Producto;
import com.example.reto3.model.dao.ProductoDao;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerUpdate implements Initializable {


    @FXML
    private TextField textNombre;

    @FXML
    private TextField textPrecio;

    @FXML
    private TextField textInventario;

    @FXML
    private JFXButton buttonSalir;

    private Producto producto;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    /**
     * Método para obtener objeto Producto de la tabla
     *
     * @param producto producto de la tabla
     */
    public void iniAttributes(Producto producto) {
        this.producto = producto;
        this.textNombre.setText(producto.getNombre());
        this.textPrecio.setText(String.valueOf(producto.getPrecio()));
        this.textInventario.setText(String.valueOf(producto.getInventario()));
    }

    /**
     * Método para actualizar lista y tabla de productos
     */
    @FXML
    private void actualizar() {
        ControllerIndex controllerIndex = new ControllerIndex();
        try {
            String nombre = this.textNombre.getText();
            String precio = this.textPrecio.getText();
            String inventario = this.textInventario.getText();

            if (!nombre.equals("")
                    && !precio.equals("")
                    && !inventario.equals("")) {

                //Nuevo Producto
                Producto nuevo = new Producto(producto.getCodigo(), nombre, Float.parseFloat(precio), Integer.parseInt(inventario));

                if (ProductoDao.actualizar(nuevo, producto)) {
                    salir();
                 controllerIndex.mensaje();
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
     * Método para cerrar la ventana antualizar
     */
    @FXML
    private void salir() {
        Stage stage = (Stage) this.buttonSalir.getScene().getWindow();
        stage.close();
    }

    /**
     * Producto
     * @return
     */
    public Producto getProducto() {
        return producto;
    }
}
