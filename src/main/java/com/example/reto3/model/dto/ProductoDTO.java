package com.example.reto3.model.dto;

import com.example.reto3.model.Producto;
import com.example.reto3.model.Validaciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ProductoDTO {

    /**
     * Lista de productos
     */
    private static ObservableList<Producto> list = FXCollections.observableArrayList();

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
            ProductoDTO.getList().add(producto);
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
        if (getList().contains(producto)) {
            ProductoDTO.getList().remove(producto);
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
        List<Float> listaDePrecios = new ArrayList<>();

        for (Producto producto : ProductoDTO.getList()) {
            listaDePrecios.add((float) producto.getPrecio());
        }

        float precioMayor = Collections.max(listaDePrecios);

        for (int i = 0; i < listaDePrecios.size(); i++) {
            if (precioMayor == (float) ProductoDTO.getList().get(i).getPrecio()) {
                return String.valueOf(ProductoDTO.getList().get(i).getNombre());
            }
        }
        return null;
    }

    /**
     * Método para obtener el nombre del Producto con el precio más bajo
     *
     * @return Nombre del producto mas económico || null si no existe ningún producto
     */
    public static String min() {
        List<Float> listaDePrecios = new ArrayList<>();

        for (Producto producto : ProductoDTO.getList()) {
            listaDePrecios.add((float) producto.getPrecio());
        }

        float precioMenor = Collections.min(listaDePrecios);

        for (int i = 0; i < listaDePrecios.size(); i++) {
            if (precioMenor == (float) ProductoDTO.getList().get(i).getPrecio()) {
                return String.valueOf(ProductoDTO.getList().get(i).getNombre());
            }
        }
        return null;
    }

    /**
     * Método para obtener el promedio de precios de la lista
     *
     * @return Promedio de precios
     */
    public static float promedioDePrecios() {
        float result = 0;

        for (Producto producto : ProductoDTO.getList()) {
            result += (float) producto.getPrecio();
        }

        result = result / ProductoDTO.getList().size();
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

        for (Producto producto : ProductoDTO.getList()) {
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
        StringBuilder cadena = new StringBuilder();
        List<String> result = new ArrayList<>();
        List<Float> listaDePrecios = new ArrayList<>();

        for (Producto producto : ProductoDTO.getList()) {
            listaDePrecios.add((float) producto.getPrecio());
        }

        int num;

        if (getList().size() >= 3) {
            num = 3;
        } else {
            num = getList().size();
        }

        int x = 1;
        while (x <= num) {
            float precioMayor = Collections.max(listaDePrecios);

            for (int i = 0; i < listaDePrecios.size(); i++) {
                if (precioMayor == ProductoDTO.getList().get(i).getPrecio()) {
                    result.add(ProductoDTO.getList().get(i).getNombre());
                    listaDePrecios.remove(i);
                }
            }
            x++;
        }

        for (int i = 0; i < result.size(); i++) {
            if (i + 1 != result.size()) {
                cadena.append(result.get(i)).append(", ");
            }else {
                cadena.append(result.get(i));
            }
        }

        return cadena.toString();
    }

    /**
     * Get
     *
     * @return
     */
    public static ObservableList<Producto> getList() {
        return list;
    }

    /**
     * Set
     *
     * @param list
     */
    public static void setList(ObservableList<Producto> list) {
        ProductoDTO.list = list;
    }
}
