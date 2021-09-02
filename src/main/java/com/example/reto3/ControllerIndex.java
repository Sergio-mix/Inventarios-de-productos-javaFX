package com.example.reto3;

import com.example.reto3.model.Data;
import com.example.reto3.model.Producto;
import com.example.reto3.model.dto.ProductoDTO;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.reto3.model.dto.ProductoDTO.getProductos;


public class ControllerIndex implements Initializable {

    @FXML
    private JFXButton buttonReport;

    @FXML
    private JFXButton buttonTable;

    @FXML
    private Pane tablePanel;

    @FXML
    private TableView<Producto> table;

    @FXML
    private TableColumn<Producto, Object> tableNombre;

    @FXML
    private TableColumn<Producto, Object> tablePrecio;

    @FXML
    private TableColumn<Producto, Object> tableInventario;

    @FXML
    private Pane reportPanel;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Text label5;

    @FXML
    private TextField textBuscar;

    private ObservableList<Producto> filtrarProductos = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Data.data();
        try {
            tableNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tablePrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
            tableInventario.setCellValueFactory(new PropertyValueFactory<>("inventario"));
        } catch (Exception ignored) {

        }

        buttonTable.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-text-fill: #000000;"
        );

        table.setItems(getProductos());//agregar data a la tabla
    }

    /**
     * Método para agregar los datos del informe a los campos
     */
    @FXML
    private void informe() {
        try {
            if (getProductos().size() != 0) {
                label1.setText(ProductoDTO.max());
                label2.setText(ProductoDTO.min());
                label3.setText(String.valueOf(ProductoDTO.promedioDePrecios()));
                label4.setText(String.valueOf(ProductoDTO.valorInventario()));
                label5.setText(ProductoDTO.precioDeLos3ProductosConLosPreciosMasAltos());
            } else {
                label1.setText(null);
                label2.setText(null);
                label3.setText(null);
                label4.setText(null);
                label5.setText(null);
            }
        } catch (Exception ignored) {

        }
    }

    /**
     * Método para activar el panel tabla
     *
     * @param event
     */
    @FXML
    private void buttonActiveTable(ActionEvent event) {
        reportPanel.setVisible(false);
        tablePanel.setVisible(true);
        buttonReport.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #FFFFFF;"
        );
        buttonTable.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-text-fill: #000000;"
        );
    }

    /**
     * Método para activar el panel informes
     *
     * @param event
     */
    @FXML
    private void buttonActiveReport(ActionEvent event) {
        informe();//Método informe

        tablePanel.setVisible(false);
        reportPanel.setVisible(true);
        buttonTable.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #FFFFFF;"
        );
        buttonReport.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-text-fill: #000000;"
        );
    }

    /**
     * Método para abrir la ventana agregar
     *
     * @param event
     */
    @FXML
    private void buttonRegister(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/reto3/register.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            ControllerRegister controllerRegister = (ControllerRegister) fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Agregar producto");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ignored) {

        }
    }

    /**
     * Método para agregar lista de productos a la tabla
     */
    @FXML
    public void addRow() {
        try {
            this.table.setItems(getProductos());
        } catch (Exception ignored) {

        }
    }

    /**
     * Método para eliminar un producto
     */
    @FXML
    public void eliminar() {
        Producto producto = table.getSelectionModel().getSelectedItem();
        if (producto != null) {
            ProductoDTO.eliminar(producto);//Método eliminar
            table.refresh();
            mostrarAlertInfo("El producto fue borrado exitosamente");
        } else {
            mostrarAlertInfo("Seleccione un producto primero");
        }
    }

    /**
     * Método para abrir ventana actualizar
     */
    @FXML
    public void actualizar() {
        Producto p = this.table.getSelectionModel().getSelectedItem();//Producto seleccionado

        if (p == null) {
            mostrarAlertInfo("Seleccione un producto primero");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/reto3/update.fxml"));

                Parent root = loader.load();

                ControllerUpdate controlador = loader.getController();
                controlador.iniAttributes(p);//Enviar producto a la clase ControllerUpdate

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Actualizar producto");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                Producto aux = controlador.getProducto();
                if (aux != null) {
                    this.table.refresh();//Refrescar tabla
                }

            } catch (IOException ex) {
                mostrarAlertError("Error");
            }
        }
    }

    /**
     * Método para filtrar los datos de la tabla
     *
     * @param event
     */
    @FXML
    public void filtrar(ActionEvent event) {
        String filtro = this.textBuscar.getText().toLowerCase();

        if (filtro.isEmpty()) {
            this.table.setItems(getProductos());
        } else {
            this.filtrarProductos.clear();

            for (Producto producto : getProductos()) {
                String nombre = producto.getNombre().toLowerCase();
                String precio = String.valueOf(producto.getPrecio());
                String inventario = String.valueOf(producto.getInventario());
                if (nombre.contains(filtro)
                        || precio.contains(filtro)
                        || inventario.contains(filtro)) {

                    this.filtrarProductos.add(producto);
                }
            }

            this.table.setItems(filtrarProductos);
        }
    }

    /**
     * Alert de error
     *
     * @param text texto del alert
     */
    @FXML
    public void mostrarAlertError(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(text);
        alert.showAndWait();
    }

    /**
     * Alert de info
     *
     * @param text texto del alert
     */
    @FXML
    public void mostrarAlertInfo(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Información");
        alert.setContentText(text);
        alert.showAndWait();
    }

    /**
     * Alert de advertencia
     *
     * @param text texto del alert
     */
    @FXML
    public void mostrarAlertWarning(String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("Advertencia");
        alert.setContentText(text);
        alert.showAndWait();
    }

    /**
     * Método para mostrar mensaje de actualización
     */
    public void mensaje() {
        this.mostrarAlertInfo("Actualización completada");
    }

}