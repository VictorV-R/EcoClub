package com.example.ecoclub.database;

import android.database.SQLException;
import android.util.Log;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;

public class DataBaseHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "database-nuna.db";
    public  static final String url="jdbc:mysql://database-nuna.c1gztg4ki2uu.sa-east-1.rds.amazonaws.com";

    public static final String username="admin", password="XiQ5nzUrRUQ8fKc";

    public static final String TABLE_USUARIOS = "Usuarios";
    public static final String TABLE_COMUNIDADES = "Comunidades";
    public static final String TABLE_LOGROS = "Logros";
    public static final String TABLE_RANGOS = "Rangos";
    public static final String TABLE_USUARIOS_COMUNIDADES = "Usuarios_comunidades";
    public static final String TABLE_PARTICIPANTES_ACTIVIDADES = "Participantes_actividades";
    public static final String TABLE_ACTIVIDADES = "Actividades";

    public void testConnection() throws SQLException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= (Connection) DriverManager.getConnection(url,username,password);
            if (connection == null){
                Log.d("INFO","NEL");
            } else {
                Log.d("INFO","SIMON");
            }
            connection.close();
        } catch (Exception e){
            Log.d("INFO","ERROR");
            e.printStackTrace();
        }
    }
}
