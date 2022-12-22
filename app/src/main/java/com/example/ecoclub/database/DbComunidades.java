package com.example.ecoclub.database;

import android.util.Log;

import com.example.ecoclub.Entities.Comunidad;
import com.example.ecoclub.Entities.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DbComunidades extends DataBaseHelper{
    private static final int cantidadCampos=5;

    public void insertarComunidad(String nombre, String informacion, double latitud, double longitud){
        String query= "INSERT INTO sys."+TABLE_COMUNIDADES+" " +
                "(nombre, informacion, latitud, longitud ) VALUES " +
                "('"+nombre+"' , '"+informacion+"' , '"+latitud+"' , '"+longitud+"')";
        ejecutarSentencia(query);
    }

    public void modificarComunidad(int id,String nombre, String informacion, double latitud, double longitud){
        String query="UPDATE sys."+TABLE_COMUNIDADES+" SET " +
                "nombre = '"+nombre+"' ," +
                "informacion = '"+informacion+"' ," +
                "latitud = '"+latitud+"' ," +
                "longitud = '"+longitud+"' " +
                "WHERE (id_comunidad = '"+id+"')";
        ejecutarSentencia(query);
    }

    public void eliminarComunidad(int id){
        String query="DELETE FROM sys."+TABLE_COMUNIDADES+" " +
                "WHERE (id_comunidad = '"+id+"')";
        ejecutarSentencia(query);
    }

    public ArrayList<Comunidad> obtenerComunidades(){
        ArrayList<Comunidad> listaComunidades=new ArrayList<Comunidad>();
        String query= "SELECT * FROM sys."+TABLE_COMUNIDADES;

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

    public Comunidad obtenerComunidad(int id){
        Comunidad comunidad=new Comunidad();
        String query= "SELECT * FROM sys."+TABLE_COMUNIDADES+" WHERE id_comunidad='"+id+"' ";

        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        comunidad.setId(rs.getInt(1));
                        comunidad.setNombre(rs.getString(2));
                        comunidad.setInformacion(rs.getString(3));
                        comunidad.setLatitud(rs.getDouble(4));
                        comunidad.setLongitud(rs.getDouble(5));
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
        return comunidad;
    }
}
