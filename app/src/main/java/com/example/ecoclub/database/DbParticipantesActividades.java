package com.example.ecoclub.database;

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
}
