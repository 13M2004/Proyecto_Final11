package com.umg.proyecto_final.BaseDatos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.ByteArrayOutputStream;

import android.provider.MediaStore;

public class PhotoHandler {
    private Context context;

    public PhotoHandler(Context context) {
        this.context = context;
    }

    // Método para iniciar la captura de la foto
    public void takePhoto(Activity activity, int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(takePictureIntent, requestCode);
        }
    }

    // Método para manejar el resultado de la captura
    public byte[] handlePhotoResult(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();  // Convertir a byte[]
        }
        return null;
    }
}

