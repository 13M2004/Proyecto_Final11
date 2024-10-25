package com.umg.proyecto_final.BaseDatos;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.Toast;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class PdfGenerator {

    public void generatePDF(Context context, String name, String phone, String location, byte[] photo, String products) {
        try {
            // Crear el documento PDF
            String filePath = Environment.getExternalStorageDirectory() + "/UserData.pdf";
            PdfWriter writer = new PdfWriter(new FileOutputStream(filePath));
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Añadir nombre y teléfono
            document.add(new Paragraph("Nombre: " + name));
            document.add(new Paragraph("Teléfono: " + phone));

            // Añadir ubicación
            document.add(new Paragraph("Ubicación: " + location));

            // Añadir productos seleccionados
            document.add(new Paragraph("Productos seleccionados: " + products));

            // Añadir foto si está disponible
            if (photo != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                Image img = new Image(ImageDataFactory.create(stream.toByteArray()));
                document.add(img);
            }

            // Cerrar el documento
            document.close();

            Toast.makeText(context, "PDF generado en: " + filePath, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al generar PDF", Toast.LENGTH_SHORT).show();
        }
    }
}
