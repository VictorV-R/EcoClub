package com.example.ecoclub.database;

public class DbRangos extends DataBaseHelper{
    private static final int cantidadCampos=2;

    public void insertarRango(String nombre){
        String query= "INSERT INTO sys."+TABLE_RANGOS+" (nombre) VALUES ('"+nombre+"')";
        ejecutarSentencia(query);
    }

    public void modificarRango(int id,String nombre){
        String query="UPDATE sys."+TABLE_RANGOS+" SET nombre = '"+nombre+"' " +
                "WHERE (id_rango = '"+id+"')";
        ejecutarSentencia(query);
    }

    public void eliminarRango(int id){
        String query="DELETE FROM sys."+TABLE_RANGOS+" " +
                "WHERE (id_rango = '"+id+"')";
        ejecutarSentencia(query);
    }
}
