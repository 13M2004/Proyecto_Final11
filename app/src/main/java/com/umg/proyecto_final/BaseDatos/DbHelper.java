package com.umg.proyecto_final.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tu_base_de_datos.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crea la tabla de productos
        db.execSQL("CREATE TABLE tb_productos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, precio REAL)");

        // Crea la tabla de usuarios
        db.execSQL("CREATE TABLE tb_usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, telefono TEXT, ubicacion TEXT, foto BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí puedes implementar la lógica para manejar las actualizaciones de la base de datos
        // Por ejemplo, puedes dropear las tablas existentes y recrearlas
        db.execSQL("DROP TABLE IF EXISTS tb_productos");
        db.execSQL("DROP TABLE IF EXISTS tb_usuario");
        onCreate(db); // Llama a onCreate para recrear las tablas
    }

    // Método para insertar datos del usuario
    public void insertarUsuario(String nombre, String telefono, String ubicacion, byte[] foto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("nombre", nombre);
        valores.put("telefono", telefono);
        valores.put("ubicacion", ubicacion);
        valores.put("foto", foto);

        db.insert("tb_usuario", null, valores);
        db.close();
    }
}


