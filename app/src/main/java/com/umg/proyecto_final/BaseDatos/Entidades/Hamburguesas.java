package com.umg.proyecto_final.BaseDatos.Entidades;

public class Hamburguesas {

    public Hamburguesas(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    private String nombre;
        private double precio;


        @Override
        public String toString() {
            return nombre + " - Q" + precio;
        }
    }

