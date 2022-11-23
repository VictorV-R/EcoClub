package com.example.ecoclub.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "database.db";
    public static final String TABLE_USERS = "users";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+TABLE_USERS+"( user_id integer PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "name text NOT NULL,"+
                "lastName text NOT NULL,"+
                "email text NOT NULL UNIQUE,"+
                "phone text NOT NULL,"+
                "password text NOT NULL)";

        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if exists " + TABLE_USERS);
        onCreate(sqLiteDatabase);
    }
}