package com.example.ecoclub.database;

import android.util.Log;

import com.example.ecoclub.Entities.Logro;
import com.example.ecoclub.Entities.Usuario;
import com.example.ecoclub.Entities.Usuario_Comunidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DbUsuariosComunidades extends DataBaseHelper{
    public void insertarUsuarioComunidad(int id_usuario, int id_comunidad,String tipo_usuario,int id_rango){
        String query= "INSERT INTO sys."+TABLE_USUARIOS_COMUNIDADES+" (id_usuario, id_comunidad,tipo_usuario,id_rango ) VALUES ('"+id_usuario+"' , '"+id_comunidad+"'" +
                ", '"+tipo_usuario+"', '"+id_rango+"')";
        Log.d("Query", query);
        ejecutarSentencia(query);
    }

    public void modificarUsuarioComunidad(int id_usuario,int id_comunidad,String tipo_usuario,int id_rango){
        String query="UPDATE sys."+TABLE_USUARIOS_COMUNIDADES+" SET tipo_usuario = '"+tipo_usuario+"' , id_rango  = '"+id_rango+"'" +
                "WHERE (id_usuario = '"+id_usuario+"') and (id_comunidad = '"+id_comunidad+"')";
        ejecutarSentencia(query);
    }

    public void eliminarUsuarioComunidad (int id_usuario,int id_comunidad){
        String query="DELETE FROM sys."+TABLE_USUARIOS_COMUNIDADES+" " +
                "WHERE (id_usuario = '"+id_usuario+"') and (id_comunidad = '"+id_comunidad+"')";
        ejecutarSentencia(query);
    }

    public ArrayList<Usuario_Comunidad> obtenerUsuariosComunidad(int id_comunidad){
        ArrayList<Usuario_Comunidad> listaUsuarios_Comunidad=new ArrayList<Usuario_Comunidad>();
        String query= "SELECT * FROM sys."+TABLE_USUARIOS_COMUNIDADES+" WHERE id_comunidad='"+id_comunidad+"' ";
        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        Usuario_Comunidad aux=new Usuario_Comunidad();
                        aux.setId_usuario(rs.getInt(1));
                        aux.setId_comunidad(rs.getInt(2));
                        aux.setTipo_usuario(rs.getString(3));
                        aux.setId_rango(rs.getInt(4));
                        listaUsuarios_Comunidad.add(aux);
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
        return listaUsuarios_Comunidad;
    }
    public Usuario_Comunidad obtenerUsuarioComunidad(int id_comunidad,int id_usuario){
        Usuario_Comunidad aux=new Usuario_Comunidad();
        String query= "SELECT * FROM sys."+TABLE_USUARIOS_COMUNIDADES+" WHERE (id_comunidad='"+id_comunidad+"') and (id_usuario='"+id_usuario+"')";
        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        aux.setId_usuario(rs.getInt(1));
                        aux.setId_comunidad(rs.getInt(2));
                        aux.setTipo_usuario(rs.getString(3));
                        aux.setId_rango(rs.getInt(4));
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
        return aux;
    }

    public ArrayList<Usuario_Comunidad> obtenerComunidadesdeUsuario(int id_usuario){
        ArrayList<Usuario_Comunidad> listaUsuarios_Comunidad=new ArrayList<Usuario_Comunidad>();
        String query= "SELECT * FROM sys."+TABLE_USUARIOS_COMUNIDADES+" WHERE id_usuario='"+id_usuario+"' ";
        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        Usuario_Comunidad aux=new Usuario_Comunidad();
                        aux.setId_usuario(rs.getInt(1));
                        aux.setId_comunidad(rs.getInt(2));
                        aux.setTipo_usuario(rs.getString(3));
                        aux.setId_rango(rs.getInt(4));
                        listaUsuarios_Comunidad.add(aux);
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
        return listaUsuarios_Comunidad;
    }
}
