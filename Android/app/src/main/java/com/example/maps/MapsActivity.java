package com.example.maps;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;

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


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Instantiates a new Polygon object and adds points to define a rectangle
        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(54.687087, 25.317370),
                        new LatLng(54.688750, 25.315077),
                        new LatLng(54.688857, 25.313411),
                        new LatLng(54.688505, 25.308184),
                        new LatLng(54.688502, 25.306205),
                        new LatLng(54.688645, 25.305128),
                        new LatLng(54.688472, 25.304627),
                        new LatLng(54.689260, 25.304360),
                        new LatLng(54.690330, 25.304492),
                        new LatLng(54.691382, 25.305139),
                        new LatLng(54.692258, 25.305376),
                        new LatLng(54.692977, 25.305054),
                        new LatLng(54.693320, 25.305845),
                        new LatLng(54.693811, 25.305327),
                        new LatLng(54.693836, 25.304170),
                        new LatLng(54.693979, 25.303548),
                        new LatLng(54.694380, 25.302390),
                        new LatLng(54.694486, 25.302553),
                        new LatLng(54.698640, 25.306104),
                        new LatLng(54.694486, 25.302553),
                        new LatLng(54.698640, 25.306104),
                        new LatLng(54.700364, 25.306662),
                        new LatLng(54.703303, 25.306673),
                        new LatLng(54.704985, 25.310557),
                        new LatLng(54.704588, 25.312671),
                        new LatLng(54.704985, 25.313038),
                        new LatLng(54.703910, 25.317674),
                        new LatLng(54.703867, 25.319037),
                        new LatLng(54.703185, 25.322223),
                        new LatLng(54.701939, 25.326107),
                        new LatLng(54.700228, 25.329950),
                        new LatLng(54.699247, 25.336858),
                        new LatLng(54.699988, 25.341837),
                        new LatLng(54.699941, 25.348915),
                        new LatLng(54.696619, 25.345713),
                        new LatLng(54.693764, 25.341773),
                        new LatLng(54.694539, 25.330000),
                        new LatLng(54.695331, 25.326142),
                        new LatLng(54.694512, 25.324581),
                        new LatLng(54.691536, 25.323390),
                        new LatLng(54.689527, 25.320268),
                        new LatLng(54.688566, 25.318723),
                        new LatLng(54.687208, 25.317532)

                        )
                .strokeColor(Color.argb(150,100,100,100))
                .fillColor(Color.argb(150,100,100,100));

// Get back the mutable Polygon
        Polygon polygon = mMap.addPolygon(rectOptions);
    }
}
