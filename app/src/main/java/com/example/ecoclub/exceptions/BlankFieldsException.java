package com.example.ecoclub.exceptions;

import android.content.Context;
import android.widget.Toast;

public class BlankFieldsException extends Exception{
    public BlankFieldsException(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
