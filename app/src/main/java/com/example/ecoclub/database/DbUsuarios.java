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

    public void insertarUsuario(String nombre, String email){
        String query= "INSERT INTO sys."+TABLE_USUARIOS+" (nombre, email ) VALUES ('"+nombre+"','"+email+"')";
        ejecutarSentencia(query);
    }

    public void modificarUsuario(int id,String nombre){
        String query="UPDATE sys."+TABLE_USUARIOS+" SET nombre='"+nombre+"'" +
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
                        aux.setName(rs.getString(2));
                        aux.setEmail(rs.getString(4));
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
                        usuario.setName(rs.getString(2));
                        usuario.setEmail(rs.getString(4));
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

    public int recuperarUsuarioID(String email) {
        Usuario usuario=new Usuario();
        Log.i("INFOMACION CORREO",email.toString());
        String query= "SELECT id_usuario FROM sys."+TABLE_USUARIOS+" " + " WHERE (email = '"+email+"')";
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
                        usuario.setName(rs.getString(2));
                        usuario.setEmail(rs.getString(4));
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
        return usuario.getId();
    }
}
