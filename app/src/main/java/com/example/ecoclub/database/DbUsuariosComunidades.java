package com.example.ecoclub.database;

public class DbUsuariosComunidades extends DataBaseHelper{
    public void insertarUsuarioComunidad(int id_usuario, int id_comunidad,String tipo_usuario,int id_rango){
        String query= "INSERT INTO sys."+TABLE_USUARIOS_COMUNIDADES+" (id_usuario, id_comunidad,tipo_usuario,id_rango ) VALUES ('"+id_usuario+"' , '"+id_comunidad+"'" +
                ", '"+tipo_usuario+"', '"+id_rango+"')";
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
}
