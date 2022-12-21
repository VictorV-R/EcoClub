package com.example.ecoclub.database;

import android.util.Log;

public class DbLogros extends DataBaseHelper{
    private static final int cantidadCampos=2;

    public void insertarLogro(String nombre, String contraseña){
        String query= "INSERT INTO sys."+TABLE_LOGROS+" (nombre ) VALUES ('"+nombre+"')";
        ejecutarSentencia(query);
    }

    public void modificarLogro(int id,String nombre, String contraseña){
        String query="UPDATE sys."+TABLE_LOGROS+" SET 'nombre' = '"+nombre+"' " +
                "WHERE ('id_logro' = '"+id+"')";
        ejecutarSentencia(query);
    }

    public void eliminarLogro(int id){
        String query="DELETE FROM sys."+TABLE_LOGROS+" " +
                "WHERE ('id_logro' = '"+id+"')";
        ejecutarSentencia(query);
    }
}
