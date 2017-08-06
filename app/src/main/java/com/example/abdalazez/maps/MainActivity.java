package com.example.abdalazez.maps;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import java.io.IOException;

/**
 * Created by ABD ALAZEZ on 27/07/2017.
 */

public class MainActivity extends Activity {

    static final long meter = 1;
    static final long mill = 1000;
    Location lastlocationgps;
    LocationManager locationManager;
    TextView txtlog;
    TextView txtlat;
    ProgressDialog progressDoalog;
    Context context;
    boolean flag = true;
    boolean isrun = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Intent intent = new Intent(this,Main2Activity.class);
        // startActivity(intent);
        boolean flagPermission = checkLocationPermission();
        Toast.makeText(this,""+flagPermission,Toast.LENGTH_LONG).show();

        LocationManager locationManager =  (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, meter, mill, new locationlisener(this));

    }

    public void butGps(View view){


        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (statusOfGPS == true) {

            if (isrun == true) {
                isrun = false;

                context = this;

                //ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "","Loading. Please wait...", true);
                Toast.makeText(this, "1", Toast.LENGTH_LONG).show();

                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                lastlocationgps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                //if (lastlocationgps == null)
                lastlocationgps = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                txtlog = (TextView) findViewById(R.id.txt1);
                txtlat = (TextView) findViewById(R.id.txt2);

                if (lastlocationgps != null) {
                    txtlog.setText("" + Double.toString(lastlocationgps.getLongitude()));
                    txtlat.setText("" + Double.toString(lastlocationgps.getLatitude()));


                    flag = true;
                    Thread th = new Thread() {
                        Activity myA = new Activity();
                        @Override
                        public void run() {
                            //  lastLocation();

                            super.run();
                            while (flag) {


                                myA.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                                        lastlocationgps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                        lastlocationgps = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

                                        txtlog.setText("" + Double.toString(lastlocationgps.getLongitude()));
                                        txtlat.setText("" + Double.toString(lastlocationgps.getLatitude()));

                                        System.out.println("status is changed");
                                        System.out.println("" + Double.toString(lastlocationgps.getLongitude()));

                                    }
                                });

                                try {
                                    sleep(10000);
                                } catch (Exception ex) {
                                    System.out.println("status is Bad");
                                }
                            }
                        }

                        ;
                    };
                    th.start();

                } else {

                    Toast.makeText(this, "Pleas Wait ...", Toast.LENGTH_LONG).show();

                }

            }else{
                Toast.makeText(this, "Gps is Run", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "The Gps is Off", Toast.LENGTH_LONG).show();
        }

    }

    public boolean checkLocationPermission() {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    public void lastLocation(){
        txtlog.setText("" + Double.toString(lastlocationgps.getLongitude()));
        txtlat.setText("" + Double.toString(lastlocationgps.getLatitude()));
    }

    public void stopGps(View view){

        if(flag == false){
            Toast.makeText(this,"Gps is Stop",Toast.LENGTH_LONG).show();
            isrun = true;
        }else{
            flag = false;
            isrun = true;
            Toast.makeText(this,"Gps Stop",Toast.LENGTH_LONG).show();
        }
    }

    public void butMap(View view){

        txtlog = (TextView) findViewById(R.id.txt1);
        String test = txtlog.getText().toString();
        if (!test.equals("Wait")){
            double log = Double.parseDouble(txtlog.getText().toString());
            double lat = Double.parseDouble(txtlat.getText().toString());


            Intent myint = new Intent(this, MapsActivity2.class);

            Bundle b = new Bundle();
            b.putDouble("intlog", log);
            b.putDouble("intlat", lat);

            myint.putExtras(b);
            startActivity(myint);
        }else{
            Toast.makeText(this,"Enter on Location",Toast.LENGTH_LONG).show();
        }
    }

    public void butstreet(View view){

            Intent myint = new Intent(this, MapsActivity.class);

            startActivity(myint);

    }
}
