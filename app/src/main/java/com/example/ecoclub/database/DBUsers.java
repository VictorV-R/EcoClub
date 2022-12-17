package com.example.ecoclub.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ecoclub.exceptions.PasswordException;

import java.util.ArrayList;

public class DBUsers extends DBHelper{
    Context context;

    public DBUsers(Context context) {
        super(context);
        this.context = context;
    }


    public void insertUser(ArrayList<String> data) throws PasswordException {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", data.get(0));
        contentValues.put("lastName", data.get(1));
        contentValues.put("email", data.get(2));
        contentValues.put("phone", data.get(3));
        contentValues.put("password", data.get(4));

        long result = sqLiteDatabase.insert(TABLE_USERS, null, contentValues);

        if(result == -1);

    }

    public void checkUserEmail(String email) throws PasswordException {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where email = ?", new String[]{email});

        if(cursor.getCount() > 0);
        cursor.close();

    }

    public void checkUserEmailPassword(ArrayList<String> data) throws PasswordException {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where email =? and password =?", new String[]{data.get(0), data.get(1)});

        if(cursor.getCount() <= 0);
        cursor.close();

    }
}
