package com.example.ecoclub.database;

import android.util.Log;

public class DbUsuarios extends DataBaseHelper{
    private static final int cantidadCampos=3;

    public void insertarUsuario(String nombre, String contraseña){
        String query= "INSERT INTO sys."+TABLE_USUARIOS+" (nombre, contrasena ) VALUES ('"+nombre+"','"+contraseña+"')";
        Log.d("INFO",query);
        ejecutarSentencia(query);
    }

    public void modificarUsuario(int id,String nombre, String contraseña){
        String query="UPDATE sys."+TABLE_USUARIOS+" SET 'nombre' = '"+nombre+"' , 'contrasena'='"+contraseña+"' " +
                "WHERE ('id_usuario' = '"+id+"')";
        Log.d("INFO",query);
        ejecutarSentencia(query);
    }

    public void eliminarUsuario(int id){
        String query="DELETE FROM sys."+TABLE_USUARIOS+" " +
                "WHERE ('id_usuario' = '"+id+"')";
        Log.d("INFO",query);
        ejecutarSentencia(query);
    }

    /*public String obtenerUsuarios(){
        String query= "SELECT * FROM sys."+TABLE_USUARIOS;
        String salida="";
        return obtenerDeSentencia(query,cantidadCampos);
    }*/
}
