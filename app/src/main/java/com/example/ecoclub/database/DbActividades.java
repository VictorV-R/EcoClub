package com.example.ecoclub.database;

import android.util.Log;

import com.example.ecoclub.Entities.Actividad;
import com.example.ecoclub.Entities.Comunidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DbActividades extends DataBaseHelper{
    private static final int cantidadCampos=7;

    public void insertarActividad(String nombre, int id_comunidad, String fecha,String descripcion,double latitud, double longitud){
        String query= "INSERT INTO sys."+TABLE_ACTIVIDADES+" " +
                "(nombre,id_comunidad,fecha, descripcion, latitud, longitud ) VALUES " +
                "('"+nombre+"' , '"+id_comunidad+"','"+fecha+"' ,'"+descripcion+"', '"+latitud+"' , '"+longitud+"')";
        ejecutarSentencia(query);
    }

    public void modificarActividad(int id,String nombre, int id_comunidad, String fecha,String descripcion,double latitud, double longitud){
        String query="UPDATE sys."+TABLE_ACTIVIDADES+" SET " +
                "nombre = '"+nombre+"' ," +
                "id_comunidad = '"+id_comunidad+"' ," +
                "fecha = '"+fecha+"' ," +
                "descripcion = '"+descripcion+"' ," +
                "latitud = '"+latitud+"' ," +
                "longitud = '"+longitud+"' " +
                "WHERE (id_actividad = '"+id+"')";
        ejecutarSentencia(query);
    }

    public void eliminarActividad(int id){
        String query="DELETE FROM sys."+TABLE_ACTIVIDADES+" " +
                "WHERE (id_actividad = '"+id+"')";
        ejecutarSentencia(query);
    }

    public ArrayList<Actividad> obtenerActividades(){
        ArrayList<Actividad> listaActividades=new ArrayList<Actividad>();
        String query= "SELECT * FROM sys."+TABLE_ACTIVIDADES;

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

    public Actividad obtenerActividad(int id){
        Actividad actividad=new Actividad();
        String query= "SELECT * FROM sys."+TABLE_ACTIVIDADES+" WHERE id_actividad='"+id+"' ";

        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        actividad.setId(rs.getInt(1));
                        actividad.setNombre(rs.getString(2));
                        actividad.setId_comunidad(rs.getInt(3));
                        actividad.setFecha(rs.getString(4));
                        actividad.setDescripcion(rs.getString(5));
                        actividad.setLatitud(rs.getDouble(6));
                        actividad.setLongitud(rs.getDouble(7));
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
        return actividad;
    }
}
