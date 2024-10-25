package com.umg.proyecto_final.BaseDatos;

import android.app.Activity;
import android.content.Context;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class LocationHandler {
    private Context context;
    private LocationManager locationManager;
    private LocationListener locationListener;

    public LocationHandler(Context context) {
        this.context = context;
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void getLocation(final LocationCallback callback) {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                String coordinates = location.getLatitude() + "," + location.getLongitude();
                callback.onLocationReceived(coordinates);
                stopLocationUpdates();  // Detener actualizaciones después de obtener la ubicación
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // Manejo de cambios en el estado del proveedor, si es necesario
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
                // Manejo de cuando el proveedor está habilitado
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                // Manejo de cuando el proveedor está deshabilitado
            }
        };

        // Verificar permisos antes de solicitar la ubicación
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            // Solicitar permisos de ubicación si no se han otorgado
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    private void stopLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.removeUpdates(locationListener);
        }
    }

    public interface LocationCallback {
        void onLocationReceived(String location);
    }
}
