package com.example.reto3.model;

import com.example.reto3.model.dao.ProductoDao;

public class Data {
    /**
     * Método para agregar 10 objetos Producto a la lista de Productos
     */
    public static void data() {
        Producto producto1 = new Producto(1, "Manzanas", 6000.0f, 25);
        ProductoDao.getList().add(producto1);

        Producto producto2 = new Producto(2, "Limones", 2500.0f, 15);
        ProductoDao.getList().add(producto2);

        Producto producto3 = new Producto(3, "Peras", 2700.0f, 33);
        ProductoDao.getList().add(producto3);

        Producto producto4 = new Producto(4, "Arandanos", 9300.0f, 34);
        ProductoDao.getList().add(producto4);

        Producto producto5 = new Producto(5, "Tomates", 2100.0f, 42);
        ProductoDao.getList().add(producto5);

        Producto producto6 = new Producto(6, "Fresas", 4100.0f, 10);
        ProductoDao.getList().add(producto6);

        Producto producto7 = new Producto(7, "Helado", 4500.0f, 41);
        ProductoDao.getList().add(producto7);

        Producto producto8 = new Producto(8, "Galletas", 500.0f, 8);
        ProductoDao.getList().add(producto8);

        Producto producto9 = new Producto(9, "Chocolates", 4500.0f, 80);
        ProductoDao.getList().add(producto9);

        Producto producto10 = new Producto(10, "Jamon", 15000.0f, 10);
        ProductoDao.getList().add(producto10);
    }
}
