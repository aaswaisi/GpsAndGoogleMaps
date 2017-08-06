package com.example.abdalazez.maps;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by ABD ALAZEZ on 27/07/2017.
 */

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double login;
    Double latin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        Bundle b = getIntent().getExtras();
        login = b.getDouble("intlog");
        latin = b.getDouble("intlat");


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng center = new LatLng(latin, login);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center,15));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Add a marker in Sydney and move the camera
        LatLng myPosition = new LatLng(latin,login );
        Marker mark = mMap.addMarker(new MarkerOptions().position(myPosition).title("My Location Work").snippet("I am Working here ♥"));
        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.common_google_signin_btn_icon_dark_focused))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));
        mark.showInfoWindow();
        mMap.setBuildingsEnabled(true);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        /////////// for put mutiple point in map and get log and lat line in map

        final Context c = this;
        final GoogleMap gm = mMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                gm.addMarker(new MarkerOptions().position(latLng).title("New Location").snippet("your put here"));
                Toast.makeText(c , latLng.longitude + "<|>" + latLng.latitude , Toast.LENGTH_LONG).show();
            }
        });
        /// for drwe line
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.518173, 30.435641),new LatLng(32.518173, 35.435641))
                .width(25)
                .color(Color.GREEN)
                .geodesic(true));

        // for make grafic

        /*
        Polyline polyline = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(31.518173, 34.435641),
                        new LatLng(35.518173, 38.435641),
                        new LatLng(40.518173, 45.435641),
                        new LatLng(45.518173, 48.435641)));
*/
        CameraPosition cp = CameraPosition.builder()
                .target(center)
                .zoom(13)
                .bearing(100)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp),2000,null);

        mMap.addCircle(new CircleOptions()
                .center(center)
                .radius(100)
                .strokeColor(Color.RED));
                //.fillColor(Color.BLUE);


    }
}
