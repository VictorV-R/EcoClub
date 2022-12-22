package com.example.ecoclub.database;

import android.util.Log;

import com.example.ecoclub.Entities.Logro;
import com.example.ecoclub.Entities.Rango;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DbRangos extends DataBaseHelper{
    private static final int cantidadCampos=2;

    public void insertarRango(String nombre){
        String query= "INSERT INTO sys."+TABLE_RANGOS+" (nombre) VALUES ('"+nombre+"')";
        ejecutarSentencia(query);
    }

    public void modificarRango(int id,String nombre){
        String query="UPDATE sys."+TABLE_RANGOS+" SET nombre = '"+nombre+"' " +
                "WHERE (id_rango = '"+id+"')";
        ejecutarSentencia(query);
    }

    public void eliminarRango(int id){
        String query="DELETE FROM sys."+TABLE_RANGOS+" " +
                "WHERE (id_rango = '"+id+"')";
        ejecutarSentencia(query);
    }

    public ArrayList<Rango> obtenerRangos(){
        ArrayList<Rango> listaRangos=new ArrayList<Rango>();
        String query= "SELECT * FROM sys."+TABLE_RANGOS;

        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        Rango aux=new Rango();
                        aux.setId(rs.getInt(1));
                        aux.setNombre(rs.getString(2));
                        listaRangos.add(aux);
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
        return listaRangos;
    }

    public Rango obtenerLogro(int id){
        Rango rango=new Rango();
        String query= "SELECT * FROM sys."+TABLE_RANGOS+" WHERE id_rango='"+id+"' ";

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
}
