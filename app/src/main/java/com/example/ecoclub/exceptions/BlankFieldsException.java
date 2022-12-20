package com.example.ecoclub.exceptions;

import android.content.Context;
import android.widget.Toast;

public class BlankFieldsException extends Exception{

    private String msg;
    private Context context;

    public BlankFieldsException(Context context){
        this.context = context;
        this.msg = "Complete todo los campos";
    }

    public void getMsg(){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
