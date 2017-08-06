package com.example.abdalazez.maps;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by ABD ALAZEZ on 27/07/2017.
 */

public class locationlisener implements LocationListener {

    Context context;

    public locationlisener(Context context) {
        this.context = context;
    }


    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(context,"Log:"+Double.toString(location.getLongitude())+"<|>Lat:"+Double.toString(location.getLatitude()),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(context,"Gps status is changed",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(context,"Gps is oN",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(context,"Gps is off",Toast.LENGTH_LONG).show();

    }
}

