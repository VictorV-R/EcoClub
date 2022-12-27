package com.example.ecoclub.database;

import android.util.Log;

public class DBProcedures extends DataBaseHelper{

    public void insertarComunidadYUsuarioComunidad(int id_usuario, String tipo_usuario, String nombre, String informacion,
                                                   double latitud, double longitud, int id_rango){
        //este metodo es para el usuario que creo
        //la comunidad este en ella de forma predeterminada

        String query = "CALL sys.sp_insertarComunidadyUsuarioComunidad('"+nombre+"'" +
                ", '"+ informacion +"', "+latitud+", "+longitud+", "+id_usuario+ ", '"+
                tipo_usuario+"', "+id_rango+");";

        Log.d("Query", query);
        ejecutarSentencia(query);
    }
}
