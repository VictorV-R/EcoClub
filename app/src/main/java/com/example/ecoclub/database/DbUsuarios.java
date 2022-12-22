package com.example.ecoclub.database;

import android.util.Log;

import com.example.ecoclub.Entities.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DbUsuarios extends DataBaseHelper{
    private static final int cantidadCampos=3;

    public void insertarUsuario(String nombre, String contraseña){
        String query= "INSERT INTO sys."+TABLE_USUARIOS+" (nombre, contrasena ) VALUES ('"+nombre+"','"+contraseña+"')";
        ejecutarSentencia(query);
    }

    public void modificarUsuario(int id,String nombre, String contraseña){
        String query="UPDATE sys."+TABLE_USUARIOS+" SET nombre = '"+nombre+"' , contrasena='"+contraseña+"' " +
                "WHERE (id_usuario = '"+id+"')";
        ejecutarSentencia(query);
    }

    public void eliminarUsuario(int id){
        String query="DELETE FROM sys."+TABLE_USUARIOS+" " +
                "WHERE (id_usuario = '"+id+"')";
        ejecutarSentencia(query);
    }

    public ArrayList<Usuario> obtenerUsuarios(){
        ArrayList<Usuario> listaUsuarios=new ArrayList<Usuario>();
        String query= "SELECT * FROM sys."+TABLE_USUARIOS;

        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        Usuario aux=new Usuario();
                        aux.setId(rs.getInt(1));
                        aux.setNombre(rs.getString(2));
                        aux.setContrasena(rs.getString(3));
                        listaUsuarios.add(aux);
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
        return listaUsuarios;
    }

    public Usuario obtenerUsuario(int id){
        Usuario usuario=new Usuario();
        String query= "SELECT * FROM sys."+TABLE_USUARIOS+" WHERE id_usuario='"+id+"' ";

        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        usuario.setId(rs.getInt(1));
                        usuario.setNombre(rs.getString(2));
                        usuario.setContrasena(rs.getString(3));
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
        return usuario;
    }
}
