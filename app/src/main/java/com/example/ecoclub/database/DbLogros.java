package com.example.ecoclub.database;

import android.util.Log;

import com.example.ecoclub.Entities.Actividad;
import com.example.ecoclub.Entities.Logro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DbLogros extends DataBaseHelper{
    private static final int cantidadCampos=2;

    public void insertarLogro(String nombre){
        String query= "INSERT INTO sys."+TABLE_LOGROS+" (nombre) VALUES ('"+nombre+"')";
        ejecutarSentencia(query);
    }

    public void modificarLogro(int id,String nombre){
        String query="UPDATE sys."+TABLE_LOGROS+" SET nombre = '"+nombre+"' " +
                "WHERE (id_logro = '"+id+"')";
        ejecutarSentencia(query);
    }

    public void eliminarLogro(int id){
        String query="DELETE FROM sys."+TABLE_LOGROS+" " +
                "WHERE (id_logro = '"+id+"')";
        ejecutarSentencia(query);
    }

    public ArrayList<Logro> obtenerLogros(){
        ArrayList<Logro> listaLogros=new ArrayList<Logro>();
        String query= "SELECT * FROM sys."+TABLE_LOGROS;

        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        Logro aux=new Logro();
                        aux.setId(rs.getInt(1));
                        aux.setNombre(rs.getString(2));
                        listaLogros.add(aux);
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
        return listaLogros;
    }

    public Logro obtenerLogro(int id){
        Logro logro=new Logro();
        String query= "SELECT * FROM sys."+TABLE_LOGROS+" WHERE id_logro='"+id+"' ";

        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        logro.setId(rs.getInt(1));
                        logro.setNombre(rs.getString(2));
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
        return logro;
    }

}
