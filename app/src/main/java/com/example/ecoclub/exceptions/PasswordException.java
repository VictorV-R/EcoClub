package com.example.ecoclub.exceptions;

import android.content.Context;
import android.widget.Toast;

public class PasswordException extends Exception{

    private String msg;
    private Context context;

    public PasswordException(Context context){
        this.context = context;
        this.msg = "CONTRASEÑA NO VALIDA !!!" +
                    "\n\n  * Tamaño minimo 8 caracteres" +
                    "\n  * Usa caracteres especiales" +
                    "\n  * Usa minusculas, mayusculas y numeros";
    }

    public void getMsg(){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
