package com.example.ecoclub.exceptions;

import android.content.Context;
import android.widget.Toast;

public class EmailExistsException extends Exception{

    private String msg;
    private Context context;

    public EmailExistsException(Context context){
        this.context = context;
        this.msg = "CORREO YA REGISTRADO !!!";
    }

    public void getMsg(){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
