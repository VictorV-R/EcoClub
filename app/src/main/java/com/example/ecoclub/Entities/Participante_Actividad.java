package com.example.ecoclub.Entities;

public class Participante_Actividad {
    private int id_usuario;
    private int id_usuario_comunidad;
    private int id_actividad;
    private int id_actividad_comunidad;
    private String nivel_participacion;


    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_usuario_comunidad() {
        return id_usuario_comunidad;
    }

    public void setId_usuario_comunidad(int id_usuario_comunidad) {
        this.id_usuario_comunidad = id_usuario_comunidad;
    }

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public int getId_actividad_comunidad() {
        return id_actividad_comunidad;
    }

    public void setId_actividad_comunidad(int id_actividad_comunidad) {
        this.id_actividad_comunidad = id_actividad_comunidad;
    }

    public String getNivel_participacion() {
        return nivel_participacion;
    }

    public void setNivel_participacion(String nivel_participacion) {
        this.nivel_participacion = nivel_participacion;
    }
}
