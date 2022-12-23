package com.example.ecoclub.database;

import android.util.Log;

import com.example.ecoclub.Entities.Comunidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DbViewsHelpers extends DataBaseHelper{

    public ArrayList<Comunidad> obtenerComunidadesPorUsuarioID(int id_usuario){
        ArrayList<Comunidad> listaComunidades=new ArrayList<Comunidad>();
        String query= "SELECT * FROM sys."+TABLE_COMUNIDADES
                +"select * from sys."+TABLE_COMUNIDADES+" "+
                "where id_comunidad in (select id_comunidad from sys."+TABLE_USUARIOS_COMUNIDADES+" where id_usuario = '"+id_usuario+"');";

        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        Comunidad aux=new Comunidad();
                        aux.setId(rs.getInt(1));
                        aux.setNombre(rs.getString(2));
                        aux.setInformacion(rs.getString(3));
                        aux.setLatitud(rs.getDouble(4));
                        aux.setLongitud(rs.getDouble(5));
                        listaComunidades.add(aux);
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
        return listaComunidades;
    }
}
