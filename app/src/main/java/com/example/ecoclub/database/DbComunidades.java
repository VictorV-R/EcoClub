package com.example.ecoclub.database;

public class DbComunidades extends DataBaseHelper{
    private static final int cantidadCampos=5;

    public void insertarComunidad(String nombre, String informacion, double latitud, double longitud){
        String query= "INSERT INTO sys."+TABLE_COMUNIDADES+" " +
                "(nombre, informacion, latitud, longitud ) VALUES " +
                "('"+nombre+"' , '"+informacion+"' , '"+latitud+"' , '"+longitud+"')";
        ejecutarSentencia(query);
    }

    public void modificarComunidad(int id,String nombre, String informacion, double latitud, double longitud){
        String query="UPDATE sys."+TABLE_COMUNIDADES+" SET " +
                "nombre = '"+nombre+"' ," +
                "informacion = '"+informacion+"' ," +
                "latitud = '"+latitud+"' ," +
                "longitud = '"+longitud+"' " +
                "WHERE (id_comunidad = '"+id+"')";
        ejecutarSentencia(query);
    }

    public void eliminarComunidad(int id){
        String query="DELETE FROM sys."+TABLE_COMUNIDADES+" " +
                "WHERE (id_comunidad = '"+id+"')";
        ejecutarSentencia(query);
    }
}
