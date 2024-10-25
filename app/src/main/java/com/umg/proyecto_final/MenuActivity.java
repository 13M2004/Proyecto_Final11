package com.umg.proyecto_final;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.umg.proyecto_final.BaseDatos.DbHelper;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private ArrayList<String> productosSeleccionados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        // Recuperar la lista de productos seleccionados
        productosSeleccionados = getIntent().getStringArrayListExtra("productos");

        // Almacenar productos en la base de datos
        almacenarProductosEnBaseDeDatos(productosSeleccionados);

        // Aquí puedes agregar tu lógica para mostrar la tabla o generar el PDF
    }

    private void almacenarProductosEnBaseDeDatos(ArrayList<String> productos) {
        SQLiteDatabase db = new DbHelper(this).getWritableDatabase();
        ContentValues valores = new ContentValues();

        for (String producto : productos) {
            String[] partes = producto.split(" - ");  // Separar nombre y precio
            String nombre = partes[0];
            double precio = Double.parseDouble(partes[1]);

            valores.put("producto", nombre);
            valores.put("precio", precio);

            long resultado = db.insert("tb_resumen_pedido", null, valores);
            if (resultado != -1) {
                Toast.makeText(this, nombre + " guardado en resumen de pedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al guardar en resumen de pedido", Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }
}

