package com.example.abdalazez.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by ABD ALAZEZ on 27/07/2017.
 */

public class MapsActivity extends FragmentActivity implements OnStreetViewPanoramaReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        StreetViewPanoramaFragment streetsiewfragment = (StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id.mapstreet);
        streetsiewfragment.getStreetViewPanoramaAsync(this);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {

        streetViewPanorama.setPosition(new LatLng(31.778615, 35.235270));
        streetViewPanorama.setStreetNamesEnabled(false);
        streetViewPanorama.setZoomGesturesEnabled(true);
        streetViewPanorama.setPanningGesturesEnabled(true);


    }
}
