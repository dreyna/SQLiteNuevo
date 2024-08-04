package com.example.sqlitenuevo.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.sqlitenuevo.config.DBHelper;
import com.example.sqlitenuevo.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private DBHelper helper;
    private SQLiteDatabase database;
    public UsuarioDao(Context context){
        helper = new DBHelper(context);
    }
    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = helper.getWritableDatabase();
        }
        return database;
    }
    @SuppressLint("Range")
    private Usuario crearUsuario(Cursor cursor){
         Usuario usuario = new Usuario(
                cursor.getInt(cursor.getColumnIndex(DBHelper.Usuarios._ID)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Usuarios.NOMBRE)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Usuarios.LOGIN)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Usuarios.CLAVE))
        );
        return usuario;
    }
    public List<Usuario> listarUsuarios(){
        Cursor cursor = getDatabase().query(DBHelper.Usuarios.TABLE,DBHelper.Usuarios.COLUMNAS, null, null, null, null, null);
        List<Usuario> lista = new ArrayList<Usuario>();
        while(cursor.moveToNext()){
            Usuario modelo = crearUsuario(cursor);
            lista.add(modelo);
        }
        return lista;
    }
    public long modificarUsuario(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put(DBHelper.Usuarios.NOMBRE, usuario.getNombres());
        values.put(DBHelper.Usuarios.LOGIN, usuario.getUser());
        values.put(DBHelper.Usuarios.CLAVE, usuario.getClave());
        if(usuario.get_id() != null){
            return database.update(DBHelper.Usuarios.TABLE, values,
                    "_id = ?", new String[]{usuario.get_id().toString()});
        }
        return getDatabase().insert(DBHelper.Usuarios.TABLE,null,values);
    }
    public boolean eliminarUsuario(int id){
        return getDatabase().delete(DBHelper.Usuarios.TABLE,"_id = ?", new String[]{Integer.toString(id)}) > 0;
    }
    public Usuario buscarUsuarioPorID(int id){
        Cursor cursor = getDatabase().query(DBHelper.Usuarios.TABLE,
                DBHelper.Usuarios.COLUMNAS, "_id = ?", new String[]{ Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Usuario model = crearUsuario(cursor);
            cursor.close();
            return model;
        }
        return null;
    }
    public boolean logueoUser(String user, String pass){
        Cursor cursor = getDatabase().query(DBHelper.Usuarios.TABLE,null,
                "LOGIN = ? AND CLAVE = ?", new String[]{user, pass}, null, null,null);
        if(cursor.moveToNext()){
            return true;
        }
        return false;
    }
    public void cerrarDB(){
        helper.close();
        database = null;
    }
}
