package com.example.reto3.model;

import com.example.reto3.model.dto.ProductoDTO;


public class Validaciones {
    /**
     * Método para validar la existencia de un codigo
     *
     * @param codigo codigo a evaluar
     * @return true si el codigo ya existe || false si no existe
     */
    public static boolean validar(int codigo) {
        for (Producto producto : ProductoDTO.getProductos()) {
            if (codigo == (int) producto.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para validar que no se repita un nombre de un producto el el metodo actualizar
     *
     * @param nombre nombre del producto
     * @param codigo codigo del producto
     * @return false si el nombre sel producta ya se encuntra en uso || true si no esta en uso
     */
    public static boolean validar(String nombre, int codigo) {
        for (Producto producto : ProductoDTO.getProductos()) {
            if ((int) producto.getCodigo() != codigo)
                if (nombre.toLowerCase().equals(producto.getNombre().toLowerCase())) {
                    return false;
                }
        }
        return true;
    }

    /**
     * Método para comprobar si un nombre de producto está registrado
     *
     * @param nombre nombre a buscar
     * @return true si el nombre ya se encuentra registrado || false si no esta
     */
    public static boolean validar(String nombre) {
        for (Producto producto : ProductoDTO.getProductos()) {
            if (producto.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
