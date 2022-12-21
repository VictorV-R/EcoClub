package com.example.ecoclub.database;

public class DbLogrosUsuarios extends DataBaseHelper{
    private static final int cantidadCampos=2;

    public void insertarLogroUsuario(int id_usuario, int id_logro){
        String query= "INSERT INTO sys."+TABLE_LOGROS_USUARIOS+" (id_usuario, id_logro ) VALUES ('"+id_usuario+"' , '"+id_logro+"')";
        ejecutarSentencia(query);
    }

    public void modificarLogroUsuario(int id_usuario_old,int id_logro_old,int id_usuario_new, int id_logro_new){
        String query="UPDATE sys."+TABLE_LOGROS_USUARIOS+" SET 'id_usuario' = '"+id_usuario_new+"' , 'id_logro'  = '"+id_logro_new+"'" +
                "WHERE ('id_usuario' = '"+id_usuario_old+"') and ('id_logro' = '"+id_logro_old+"')";
        ejecutarSentencia(query);
    }

    public void eliminarLogroUsuario(int id_usuario,int id_logro){
        String query="DELETE FROM sys."+TABLE_LOGROS_USUARIOS+" " +
                "WHERE ('id_usuario' = '"+id_usuario+"') and ('id_logro' = '"+id_logro+"')";
        ejecutarSentencia(query);
    }
}
