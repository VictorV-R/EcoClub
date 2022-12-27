package com.example.ecoclub.database;

import android.util.Log;

import com.example.ecoclub.Entities.Actividad;
import com.example.ecoclub.Entities.Comunidad;
import com.example.ecoclub.Entities.Rango;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DbViewsHelpers extends DataBaseHelper{

    //Todo:Comunidades
    public ArrayList<Comunidad> obtenerComunidadesPorUsuarioID(int id_usuario){
        ArrayList<Comunidad> listaComunidades=new ArrayList<Comunidad>();
        String query=
                "select * from sys."+TABLE_COMUNIDADES+" "+
                "where id_comunidad in (select id_comunidad from sys."+TABLE_USUARIOS_COMUNIDADES+" where id_usuario = '"+id_usuario+"');";

        listaComunidades = ejecutarSentenciaComunidades(query);
        return listaComunidades;
    }


    //aqui se buscan todas las comunidades no asociadas al usuario
    public ArrayList<Comunidad> obtenerComunidadesNoAsociadasPorUsuarioID(int id_usuario){
        ArrayList<Comunidad> listaComunidades=new ArrayList<Comunidad>();
        String query=
                "select * from sys."+TABLE_COMUNIDADES+ " where id_comunidad not in ("+
                "select id_comunidad from sys."+TABLE_COMUNIDADES+" "+
                        "where id_comunidad in (select id_comunidad from sys."+
                        TABLE_USUARIOS_COMUNIDADES+" where id_usuario = '"+id_usuario+"'));";


        listaComunidades = ejecutarSentenciaComunidades(query);
        return listaComunidades;
    }
    private ArrayList<Comunidad> ejecutarSentenciaComunidades(String query){

        ArrayList<Comunidad> listaComunidades=new ArrayList<Comunidad>();

        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        Comunidad aux=new Comunidad();
                        aux.setId(rs.getInt(1));
                        aux.setNombre(rs.getString(2));
                        aux.setInformacion(rs.getString(3));
                        aux.setLatitud(rs.getDouble(4));
                        aux.setLongitud(rs.getDouble(5));
                        listaComunidades.add(aux);
                    }
                    connection.close();

                } catch (Exception e) {
                    Log.d("INFO",e.toString());
                    e.printStackTrace();
                }
            }
        });
        t.start();

        try {
            t.join();
        } catch (Exception e){ Log.d("INFO",e.toString());}
        return listaComunidades;
    }

    //Todo: Rangos
    public Rango obtenerRangoMedianteUsuarioComunidad(int id_usuario, int id_comunidad){
        Rango rango = new Rango();
        String query= "SELECT * FROM sys."+TABLE_RANGOS+" WHERE id_rango in (" +
                "SELECT id_rango FROM sys."+TABLE_USUARIOS_COMUNIDADES
                +" WHERE (id_usuario='"+id_usuario+"') and (id_comunidad ='"+id_comunidad+"'))";
        rango = ejecutarSentenciaRango(query);
        return rango;
    }

    public Rango ejecutarSentenciaRango(String query){
        Rango rango = new Rango();
        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        rango.setId(rs.getInt(1));
                        rango.setNombre(rs.getString(2));
                    }
                    connection.close();
                } catch (Exception e) {
                    Log.d("INFO",e.toString());
                    e.printStackTrace();
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (Exception e){ Log.d("INFO",e.toString());}
        return rango;
    }

    //Todo:Actividades
    public ArrayList<Actividad> obtenerActividadesDeComunidadesSegunUsuario(int id_usuario){
        ArrayList<Actividad> listaActividades = new ArrayList<>();
        String query = "select * from sys."+TABLE_ACTIVIDADES+" "+
                "where id_comunidad in (select id_comunidad from sys."+TABLE_USUARIOS_COMUNIDADES+" where id_usuario = '"+id_usuario+"');";
        listaActividades = ejecutarSentenciaActividades(query);
        return listaActividades;
    }

    private ArrayList<Actividad> ejecutarSentenciaActividades(String query) {
        ArrayList<Actividad> listaActividades = new ArrayList<>();

        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        Actividad aux=new Actividad();
                        aux.setId(rs.getInt(1));
                        aux.setNombre(rs.getString(2));
                        aux.setId_comunidad(rs.getInt(3));
                        aux.setFecha(rs.getString(4));
                        aux.setDescripcion(rs.getString(5));
                        aux.setLatitud(rs.getDouble(6));
                        aux.setLongitud(rs.getDouble(7));

                        listaActividades.add(aux);
                    }
                    connection.close();

                } catch (Exception e) {
                    Log.d("INFO",e.toString());
                    e.printStackTrace();
                }
            }
        });
        t.start();

        try {
            t.join();
        } catch (Exception e){ Log.d("INFO",e.toString());}

        return listaActividades;
    }
}
