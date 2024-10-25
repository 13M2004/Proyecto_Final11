package com.umg.proyecto_final.BaseDatos.Entidades;

public class Postres {
    private String nombre;
    private double precio;

    public Postres(String nombre, double precio) {
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
