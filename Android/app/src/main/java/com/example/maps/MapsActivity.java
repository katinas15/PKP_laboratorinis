package com.example.maps;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MainZones get = new MainZones();
        get.execute();
        System.out.println("testing");
        System.out.println(get);

        LatLng sydney = new LatLng(54.694380, 25.302390);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMaxZoomPreference(1f);

        PolygonOptions rectOptionsA = new PolygonOptions()
                .add(new LatLng(54.694380, 25.302390),
                        new LatLng(54.693979, 25.303548),
                        new LatLng(54.693836, 25.304170),
                        new LatLng(54.693811, 25.305327),
                        new LatLng(54.693320, 25.305845),
                        new LatLng(54.692977, 25.305054),
                        new LatLng(54.692258, 25.305376),
                        new LatLng(54.691382, 25.305139),
                        new LatLng(54.690330, 25.304492),
                        new LatLng(54.689260, 25.304360),
                        new LatLng(54.688472, 25.304627),
                        new LatLng(54.688645, 25.305128),
                        new LatLng(54.688502, 25.306205),
                        new LatLng(54.688505, 25.308184),
                        new LatLng(54.688857, 25.313411),
                        new LatLng(54.688750, 25.315077),
                        new LatLng(54.687087, 25.317370),
                        new LatLng(54.684482, 25.320734),
                        new LatLng(54.683587, 25.318989),
                        new LatLng(54.682413, 25.314827),
                        new LatLng(54.685649, 25.307743),
                        new LatLng(54.688100, 25.304962),
                        new LatLng(54.686376, 25.302551),
                        new LatLng(54.684692, 25.301862),
                        new LatLng(54.682041, 25.298590),
                        new LatLng(54.683179, 25.299196),
                        new LatLng(54.683641, 25.299055),
                        new LatLng(54.684590, 25.298030),
                        new LatLng(54.685083, 25.296312),
                        new LatLng(54.685196, 25.296195),
                        new LatLng(54.685876, 25.295915),
                        new LatLng(54.686083, 25.295751),
                        new LatLng(54.686515, 25.295132),
                        new LatLng(54.686904, 25.294180),
                        new LatLng(54.688635, 25.292866),
                        new LatLng(54.688958, 25.292771),
                        new LatLng(54.689307, 25.293664),
                        new LatLng(54.689724, 25.294385),
                        new LatLng(54.691051, 25.296194),
                        new LatLng(54.691428, 25.296795),
                        new LatLng(54.692583, 25.299706)
                )
                .strokeColor(Color.argb(150,50,200,200))
                .fillColor(Color.argb(150,50,200,200));

        Polygon polygonA = mMap.addPolygon(rectOptionsA);
    }

    private final class MainZones extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MapsActivity.this, "Siunčiami zonų duomenys", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = "/mainZone";
            try {
                return NetController.sendGet(url);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error! Unable to retrieve data from database!";
            }
        }
    }
}
