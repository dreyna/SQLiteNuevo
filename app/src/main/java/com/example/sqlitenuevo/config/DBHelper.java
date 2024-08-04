package com.example.sqlitenuevo.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DReyna on 18/12/2020.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME  = "DB2020";
    private static final int DB_VERSION = 1;
    public DBHelper(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //creamos la tabla de Usuario
        db.execSQL("create table usuarios(_id integer primary key autoincrement, "
                  +"nombre text not null, login not null, clave text not null)");
            db.execSQL("insert into usuarios(nombre, login, clave) values('David Reyna', 'dreyna', '123')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static class Usuarios{
        public static final String TABLE = "usuarios";
        public static final String _ID = "_id";
        public static final String NOMBRE = "nombre";
        public static final String LOGIN = "login";
        public static final String CLAVE = "clave";
        public static final String[] COLUMNAS = new String[]{_ID, NOMBRE, LOGIN, CLAVE};
    }

}
