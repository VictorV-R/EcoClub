package com.example.ecoclub.database;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import com.example.ecoclub.MainActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseHelper {
    public static final String url="jdbc:mysql://database-nuna.c1gztg4ki2uu.sa-east-1.rds.amazonaws.com";
    public static final String DATABASE_NAME="database-nuna";
    public static final String username="admin", password="XiQ5nzUrRUQ8fKc";

    public static final String TABLE_USUARIOS = "Usuarios";
    public static final String TABLE_COMUNIDADES = "Comunidades";
    public static final String TABLE_LOGROS = "Logros";
    public static final String TABLE_RANGOS = "Rangos";
    public static final String TABLE_USUARIOS_COMUNIDADES = "Usuarios_comunidades";
    public static final String TABLE_PARTICIPANTES_ACTIVIDADES = "Participantes_actividades";
    public static final String TABLE_ACTIVIDADES = "Actividades";

    public String respuesta="";

    protected void ejecutarSentencia(String query){
        new Thread(() -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                connection.close();
            } catch (Exception e) {
                Log.d("INFO",e.toString());
            }
        }).start();
    }


    protected String obtenerDeSentencia(String query,int cantidadCampos){
        new Thread(() -> {
            StringBuilder records = new StringBuilder();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    for (int i=1;i<=cantidadCampos;i++){
                        records.append(rs.getString(i));
                        if (i!=cantidadCampos) {records.append(":");}
                    }
                }
                respuesta=records.toString();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        String aux=respuesta;
        respuesta="";
        return aux;
    }


    /*public void testConnection() throws SQLException {
        try {
            utilFun();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void utilFun() throws SQLException {
        new Thread(() -> {
            StringBuilder records = new StringBuilder();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);

                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery("SELECT * FROM sys." + TABLE_LOGROS);
                while (rs.next()) {
                    records.append("ID: ").append(rs.getString(1)).append(", Name: ").append(rs.getString(2)).append("\n");
                }

                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();*/

}
