package com.example.ecoclub.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ecoclub.exceptions.DataBasesException;

public class DBUsers extends DBHelper{
    Context context;

    public DBUsers(Context context) {
        super(context);
        this.context = context;
    }


    public void insertUser(String name, String lastName,String email, String phone, String pass) throws DataBasesException {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("lastName", lastName);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("password", pass);

        long result = sqLiteDatabase.insert(TABLE_USERS, null, contentValues);

        if(result == -1) throw new DataBasesException(context, "Error al registrarse");

    }

    public void checkUserEmail(String email) throws  DataBasesException{
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where email = ?", new String[]{email});

        if(cursor.getCount() > 0) throw new DataBasesException(context, "El correo ya esta registrado, ingrese otro");

    }

    public void checkUserEmailPassword(String email, String password) throws  DataBasesException{
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where email =? and passsword =?", new String[]{email, password});

        if(cursor.getCount() < 1) throw new DataBasesException(context, "Error en el correo o contraseña");

    }
}
