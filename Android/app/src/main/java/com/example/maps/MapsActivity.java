package com.example.maps;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Zone> zones;
    List<UserMarker> markers;
    Map<String, Integer> colors = new HashMap<String, Integer>();

    LocationManager locationManager;
    LocationListener locationListener;
    static LatLng userLocation;
    LatLng clickLocation;
    String provider;
    RelativeLayout parkavimoZona;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        provider = locationManager.getBestProvider(new Criteria(), false);

        checkLocationPermission();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        parkavimoZona = (RelativeLayout)findViewById(R.id.parkavimoZona);
        System.out.println(parkavimoZona);
        parkavimoZona.animate().translationY(parkavimoZona.getHeight()).alpha(0.0f)
                .setDuration(300);

        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location location) {
                        userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    }
                });
                Toast.makeText(MapsActivity.this, "rodomas location", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MapsActivity.this, "returned", Toast.LENGTH_LONG).show();
            return;
        }

        colors.put("raudona", Color.argb(100,252, 40, 3));
        colors.put("zalia", Color.argb(100,15, 219, 66));
        colors.put("geltona", Color.argb(100,252, 219, 3));
        colors.put("melyna", Color.argb(100,3, 119, 252));

        GetMainZones getMain = new GetMainZones();
        getMain.execute();

        GetUserZones getUser = new GetUserZones();
        getUser.execute();

        mMap.setMinZoomPreference(10.0f);

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Intent intent = new Intent(MapsActivity.this, create_zone.class);
                intent.putExtra("x", latLng.latitude);
                intent.putExtra("y", latLng.longitude);
                startActivity(intent);
            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                parkavimoZona.animate().translationY(parkavimoZona.getHeight()).alpha(0.0f)
                        .setDuration(300);
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                parkavimoZona.animate().translationY(0f).alpha(1.0f)
                        .setDuration(300);
                return false;
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(markers != null) markers.clear();
        GetUserZones getUser = new GetUserZones();
        getUser.execute();
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
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("GAUTA: " + result);
            Gson gson = new Gson();

            if (result == "Error") return;

            zones = gson.fromJson(result, new TypeToken<List<Zone>>() {
            }.getType());

            for (Zone zone : zones) {
                PolygonOptions rectangle = new PolygonOptions();
                for (Coords coords : zone.getBounds()) {
                    rectangle.add(new LatLng(coords.getX(), coords.getY()));
                }
                rectangle.strokeColor(colors.get(zone.getColor()));
                rectangle.fillColor(colors.get(zone.getColor()));

                mMap.addPolygon(rectangle);
            }
        }
    }



        private final class GetUserZones extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Toast.makeText(MapsActivity.this, "Siunčiami marker duomenys", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(String... strings) {
                String url = "/userZone";
                try {
                    return NetController.sendGet(url);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Error";
                }
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                System.out.println("GAUTA: " + result);
                Gson gson = new Gson();

                if(result == "Error") return;

                markers = gson.fromJson(result, new TypeToken<List<UserMarker>>() {}.getType());

                for(UserMarker marker : markers){
                        LatLng location = new LatLng(marker.getPoint().getX(), marker.getPoint().getY());
                        mMap.addMarker(new MarkerOptions()
                        .position(location));
                }
            }
    }



    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("hello")
                        .setMessage("give permission ok ?")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MapsActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.requestLocationUpdates(provider, 400, 1, (LocationListener) this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }
}
