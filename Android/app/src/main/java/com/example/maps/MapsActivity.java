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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Zone> zones;
    Map<String, Integer> colors = new HashMap<String, Integer>();


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
        colors.put("raudona", Color.argb(100,252, 40, 3));
        colors.put("zalia", Color.argb(100,15, 219, 66));
        colors.put("geltona", Color.argb(100,252, 219, 3));
        colors.put("melyna", Color.argb(100,3, 119, 252));

        mMap = googleMap;

        GetMainZones get = new GetMainZones();
        get.execute();

        mMap.setMinZoomPreference(14.0f);
        LatLng vilnius = new LatLng(54.694380, 25.302390);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vilnius));


    }

    private final class GetMainZones extends AsyncTask<String, String, String> {
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

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("GAUTA: " + result);
            Gson gson = new Gson();
            zones = gson.fromJson(result, new TypeToken<List<Zone>>() {}.getType());

            for(Zone zone : zones){
                PolygonOptions rectangle = new PolygonOptions();
                for(Coords coords : zone.getBounds()){
                    rectangle.add(new LatLng(coords.getX(), coords.getY()));
                }
                rectangle.strokeColor(colors.get(zone.getColor()));
                rectangle.fillColor(colors.get(zone.getColor()));

                mMap.addPolygon(rectangle);
            }


        }
    }
}
