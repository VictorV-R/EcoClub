package com.example.ecoclub.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "database-nuna.db";

    public static final String TABLE_USUARIOS = "Usuarios";
    public static final String TABLE_COMUNIDADES = "Comunidades";
    public static final String TABLE_LOGROS = "Logros";
    public static final String TABLE_RANGOS = "Rangos";
    public static final String TABLE_USUARIOS_COMUNIDADES = "Usuarios_comunidades";
    public static final String TABLE_PARTICIPANTES_ACTIVIDADES = "Participantes_actividades";
    public static final String TABLE_ACTIVIDADES = "Actividades";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("" );
        onCreate(sqLiteDatabase);
    }
}