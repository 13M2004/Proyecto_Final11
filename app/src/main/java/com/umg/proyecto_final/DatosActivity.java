package com.umg.proyecto_final;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.umg.proyecto_final.BaseDatos.DbHelper;
import com.umg.proyecto_final.BaseDatos.LocationHandler;
import com.umg.proyecto_final.BaseDatos.PdfGenerator;
import com.umg.proyecto_final.BaseDatos.PhotoHandler;

public class DatosActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private EditText editTextNombre, editTextTelefono;
    private Button btnObtenerUbicacion, btnTomarFoto, btnGenerarPDF;
    private ImageView imageViewUbicacion, imageViewFoto;

    private LocationHandler locationHandler;
    private PhotoHandler photoHandler;
    private DbHelper dbHelper;
    private byte[] photoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        btnObtenerUbicacion = findViewById(R.id.btnObtenerUbicacion);
        btnTomarFoto = findViewById(R.id.btnTomarFoto);
        btnGenerarPDF = findViewById(R.id.btnGenerarPDF);
        imageViewUbicacion = findViewById(R.id.imageViewUbicacion);
        imageViewFoto = findViewById(R.id.imageViewFoto);

        dbHelper = new DbHelper(this);
        photoHandler = new PhotoHandler(this);
        locationHandler = new LocationHandler(this);

        btnObtenerUbicacion.setOnClickListener(v -> obtenerUbicacion());

        btnTomarFoto.setOnClickListener(v -> tomarFoto());

        btnGenerarPDF.setOnClickListener(v -> generarPDF());
    }

    private void obtenerUbicacion() {
        locationHandler.getLocation(location -> {
            // Actualiza la interfaz con la ubicación
            Toast.makeText(this, "Ubicación obtenida: " + location, Toast.LENGTH_SHORT).show();
            // Guardar la ubicación en la base de datos o usarla en el PDF
        });
    }

    private void tomarFoto() {
        photoHandler.takePhoto(this, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            photoData = photoHandler.handlePhotoResult(resultCode, data);
            if (photoData != null) {
                Bitmap imageBitmap = BitmapFactory.decodeByteArray(photoData, 0, photoData.length);
                imageViewFoto.setImageBitmap(imageBitmap);
            }
        }
    }

    private void generarPDF() {
        String nombre = editTextNombre.getText().toString();
        String telefono = editTextTelefono.getText().toString();
        String ubicacion = "Ubicación obtenida";  // Debes almacenar y usar la ubicación real
        String productos = "Productos seleccionados";  // Debes almacenar y usar los productos seleccionados

        // Almacena los datos en la base de datos
        dbHelper.insertarUsuario(nombre, telefono, ubicacion, photoData);

        // Genera el PDF
        PdfGenerator pdfGenerator = new PdfGenerator();
        pdfGenerator.generatePDF(this, nombre, telefono, ubicacion, photoData, productos);
    }
}
