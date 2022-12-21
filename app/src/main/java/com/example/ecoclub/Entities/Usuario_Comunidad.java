package com.example.ecoclub.Entities;

public class Usuario_Comunidad {
    private int id_usuario;
    private int id_comunidad;
    private String tipo_usuario;
    private int id_rango;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_comunidad() {
        return id_comunidad;
    }

    public void setId_comunidad(int id_comunidad) {
        this.id_comunidad = id_comunidad;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public int getId_rango() {
        return id_rango;
    }

    public void setId_rango(int id_rango) {
        this.id_rango = id_rango;
    }
}
