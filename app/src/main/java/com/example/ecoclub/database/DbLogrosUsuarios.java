package com.example.ecoclub.database;

import android.util.Log;

import com.example.ecoclub.Entities.Logro;
import com.example.ecoclub.Entities.Logro_Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DbLogrosUsuarios extends DataBaseHelper{
    private static final int cantidadCampos=2;

    public void insertarLogroUsuario(int id_usuario, int id_logro){
        String query= "INSERT INTO sys."+TABLE_LOGROS_USUARIOS+" (id_usuario, id_logro ) VALUES ('"+id_usuario+"' , '"+id_logro+"')";
        ejecutarSentencia(query);
    }

    public void modificarLogroUsuario(int id_usuario_old,int id_logro_old,int id_usuario_new, int id_logro_new){
        String query="UPDATE sys."+TABLE_LOGROS_USUARIOS+" SET id_usuario = '"+id_usuario_new+"' , id_logro  = '"+id_logro_new+"'" +
                "WHERE (id_usuario = '"+id_usuario_old+"') and (id_logro = '"+id_logro_old+"')";
        ejecutarSentencia(query);
    }

    public void eliminarLogroUsuario(int id_usuario,int id_logro){
        String query="DELETE FROM sys."+TABLE_LOGROS_USUARIOS+" " +
                "WHERE (id_usuario = '"+id_usuario+"') and (id_logro = '"+id_logro+"')";
        ejecutarSentencia(query);
    }
    public ArrayList<Logro_Usuario> obtenerLogrosUsuario(int id_usuario){
        ArrayList<Logro_Usuario> listaLogros=new ArrayList<>();
        String query= "SELECT * FROM sys."+TABLE_LOGROS_USUARIOS+" WHERE id_usuario='"+id_usuario+"' ";
        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        Logro_Usuario aux=new Logro_Usuario();
                        aux.setId_usuario(rs.getInt(1));
                        aux.setId_logro(rs.getInt(2));
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


}
