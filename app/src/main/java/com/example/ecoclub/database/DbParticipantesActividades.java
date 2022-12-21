package com.example.ecoclub.database;

import android.util.Log;

import com.example.ecoclub.Entities.Logro;
import com.example.ecoclub.Entities.Participante_Actividad;
import com.example.ecoclub.Entities.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DbParticipantesActividades extends DataBaseHelper{
    public void insertarParticipanteActividad(int id_usuario, int id_comunidad,
                                              int id_actividad,
                                              String nivel_participacion){

        String query= "INSERT INTO sys."+TABLE_PARTICIPANTES_ACTIVIDADES+" " +
                "(id_usuario, id_comunidad_comunidad, id_actividad, id_comunidad_actividad,nivel_participacion  ) " +
                "VALUES ('"+id_usuario+"' , '"+id_comunidad+"' , '"+id_actividad+"' , '"+id_comunidad+"' , '"+nivel_participacion+"')";
        ejecutarSentencia(query);
    }

    public void modificarParticipanteActividad(int id_usuario, int id_comunidad,
                                               int id_actividad,
                                               String nivel_participacion){
        String query="UPDATE sys."+TABLE_PARTICIPANTES_ACTIVIDADES+" SET nivel_participacion = '"+nivel_participacion+"' " +
                "WHERE (id_usuario = '"+id_usuario+"') and (id_comunidad_comunidad = '"+id_comunidad+"')" +
                "and (id_actividad='"+id_actividad+"') and (id_comunidad_actividad='"+id_comunidad+"')";
        ejecutarSentencia(query);
    }

    public void eliminarParticipanteActividad (int id_usuario, int id_comunidad,
                                               int id_actividad){
        String query="DELETE FROM sys."+TABLE_PARTICIPANTES_ACTIVIDADES+" " +
                "WHERE (id_usuario = '"+id_usuario+"') and (id_comunidad_comunidad = '"+id_comunidad+"')" +
                "and (id_actividad='"+id_actividad+"') and (id_comunidad_actividad='"+id_comunidad+"')";
        ejecutarSentencia(query);
    }

    public ArrayList<Participante_Actividad> obtenerParticipantesActividad(int id_actividad, int id_comunidad){
        ArrayList<Participante_Actividad> listaUsuarios=new ArrayList<Participante_Actividad>();
        String query= "SELECT * FROM sys."+TABLE_PARTICIPANTES_ACTIVIDADES+" " +
                "WHERE (id_comunidad_comunidad = '"+id_comunidad+"') and (id_actividad='"+id_actividad+"') and (id_comunidad_actividad='"+id_comunidad+"')";
        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        Participante_Actividad aux=new Participante_Actividad();
                        aux.setId_usuario(rs.getInt(1));
                        aux.setId_usuario_comunidad(rs.getInt(2));
                        aux.setId_actividad(rs.getInt(3));
                        aux.setId_actividad_comunidad(rs.getInt(4));
                        aux.setNivel_participacion(rs.getString(5));
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
}
