package com.example.ecoclub.database;

public class DbActividades extends DataBaseHelper{
    private static final int cantidadCampos=7;

    public void insertarActividad(String nombre, int id_comunidad, String fecha,String descripcion,double latitud, double longitud){
        String query= "INSERT INTO sys."+TABLE_ACTIVIDADES+" " +
                "(nombre,id_comunidad,fecha, descripcion, latitud, longitud ) VALUES " +
                "('"+nombre+"' , '"+id_comunidad+"','"+fecha+"' ,'"+descripcion+"', , '"+latitud+"' , '"+longitud+"')";
        ejecutarSentencia(query);
    }

    public void modificarActividad(int id,String nombre, int id_comunidad, String fecha,String descripcion,double latitud, double longitud){
        String query="UPDATE sys."+TABLE_ACTIVIDADES+" SET " +
                "'nombre' = '"+nombre+"' " +
                "'id_comunidad' = '"+id_comunidad+"' " +
                "'fecha' = '"+fecha+"' " +
                "'descripcion' = '"+descripcion+"' " +
                "'latitud' = '"+latitud+"' " +
                "'longitud' = '"+longitud+"' " +
                "WHERE ('id_actividad' = '"+id+"')";
        ejecutarSentencia(query);
    }

    public void eliminarActividad(int id){
        String query="DELETE FROM sys."+TABLE_ACTIVIDADES+" " +
                "WHERE ('id_actividad' = '"+id+"')";
        ejecutarSentencia(query);
    }
}
