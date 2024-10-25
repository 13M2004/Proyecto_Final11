package com.umg.proyecto_final.BaseDatos.Entidades;

public class Bebidas {


    private String nombre;
    private double precio;

    public Bebidas(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return nombre + " - Q" + precio;
    }
}