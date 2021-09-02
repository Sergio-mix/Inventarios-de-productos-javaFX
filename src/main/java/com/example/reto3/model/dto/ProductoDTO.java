package com.example.reto3.model.dto;

import com.example.reto3.model.Producto;
import com.example.reto3.model.Validaciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ProductoDTO {

    /**
     * Lista de productos
     */
    private static ObservableList<Producto> productos = FXCollections.observableArrayList();

    /**
     * Método para agregar un objeto Producto a pa lista producto
     *
     * @param codigo     atributo objeto
     * @param nombre     atributo nombré
     * @param precio     atributo preció
     * @param inventario atributo inventario
     * @return true si se agrego || false si no
     */
    public static boolean agregar(int codigo, String nombre, float precio, int inventario) {

        Producto producto = new Producto(codigo, nombre, precio, inventario);

        if (!Validaciones.validar(nombre)) {
            ProductoDTO.getProductos().add(producto);
            return true;
        }
        return false;
    }

    /**
     * Método para eliminar un objeto Producto
     *
     * @param producto producto a eliminar
     * @return true si el objeto se elimino || false si no
     */
    public static boolean eliminar(Producto producto) {
        if (getProductos().contains(producto)) {
            ProductoDTO.getProductos().remove(producto);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para actualizar un objeto Producto
     *
     * @param nuevo  objeto Producto nuevo
     * @param actual objeto Producto Actualizado
     * @return true si se actualizo || false si no
     */
    public static boolean actualizar(Producto nuevo, Producto actual) {
        if (Validaciones.validar(nuevo.getNombre(), actual.getCodigo())) {
            actual.setNombre(nuevo.getNombre());
            actual.setPrecio(nuevo.getPrecio());
            actual.setInventario(nuevo.getInventario());
            return true;
        }
        return false;
    }

    /**
     * Método para obtener el nombre del Producto con el precio más alto
     *
     * @return Nombre del producto mas costoso || null si no existe ningún producto
     */
    public static String max() {
        List<Producto> produc = new ArrayList<>(getProductos());

        produc.sort(new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return Integer.compare((int) p1.getPrecio(), (int) p2.getPrecio());
            }
        });

        return produc.get(produc.size() - 1).getNombre();
    }

    /**
     * Método para obtener el nombre del Producto con el precio más bajo
     *
     * @return Nombre del producto mas económico || null si no existe ningún producto
     */
    public static String min() {
        List<Producto> produc = new ArrayList<>(getProductos());

        produc.sort(new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return Integer.compare((int) p1.getPrecio(), (int) p2.getPrecio());
            }
        });

        return produc.get(0).getNombre();
    }

    /**
     * Método para obtener el promedio de precios de la lista
     *
     * @return Promedio de precios
     */
    public static float promedioDePrecios() {
        float result = 0;

        for (Producto producto : ProductoDTO.getProductos()) {
            result += (float) producto.getPrecio();
        }

        result = result / ProductoDTO.getProductos().size();
        BigDecimal bd = new BigDecimal(result);
        bd = bd.setScale(1, RoundingMode.HALF_UP);

        float flo = Float.parseFloat(String.valueOf(bd));

        return flo;
    }

    /**
     * Método para obtener el valor del inventario
     *
     * @return valor del inventario
     */
    public static float valorInventario() {
        float result = 0;

        for (Producto producto : ProductoDTO.getProductos()) {
            result += (float) producto.getPrecio() * (int) producto.getInventario();
        }

        BigDecimal bd = new BigDecimal(result);
        bd = bd.setScale(1, RoundingMode.HALF_UP);

        float flo = Float.parseFloat(String.valueOf(bd));

        return flo;
    }

    /**
     * Método para obtener los tres productos más costosos
     *
     * @return nombres de los tre productos
     */
    public static String precioDeLos3ProductosConLosPreciosMasAltos() {
        String result = "";

        List<Producto> produc = new ArrayList<>(getProductos());

        produc.sort(new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return Integer.compare((int) p1.getPrecio(), (int) p2.getPrecio());
            }
        });

        int num = getProductos().size();

        if (num >= 3) {
            result = result + produc.get(produc.size() - 1).getNombre();
            result = result + " ," + produc.get(produc.size() - 2).getNombre();
            result = result + " ," + produc.get(produc.size() - 3).getNombre();
        } else if (num == 2) {
            result = result + produc.get(produc.size() - 1).getNombre();
            result = result + " ," + produc.get(produc.size() - 2).getNombre();
        } else if (num == 1) {
            result = result + produc.get(produc.size() - 1).getNombre();
        } else {
            result = "No hay productos";
        }

        return result;
    }

    /**
     * Get
     *
     * @return list
     */
    public static ObservableList<Producto> getProductos() {
        return productos;
    }

    /**
     * Set
     *
     * @param productos
     */
    public static void setProductos(ObservableList<Producto> productos) {
        ProductoDTO.productos = productos;
    }
}
