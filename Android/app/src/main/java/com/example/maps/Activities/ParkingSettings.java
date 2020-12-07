package com.example.maps.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.example.maps.Controllers.NetController;
import com.example.maps.Objects.Coords;
import com.example.maps.Objects.PolyUtil;
import com.example.maps.Objects.User;
import com.example.maps.Objects.Zone;
import com.example.maps.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.maps.Activities.MapsActivity.MY_PERMISSIONS_REQUEST_LOCATION;

public class ParkingSettings extends AppCompatActivity {

    GoogleMap mMap = MapsActivity.mMap;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parking_settings);
        et = findViewById(R.id.editTextCarNum);

        if(MapsActivity.user.getCarNumber() != null)
            et.setText(MapsActivity.user.getCarNumber());
    }

    public void saveNumber(View v){
        EditText et = findViewById(R.id.editTextCarNum);
        PutCarNumber put = new PutCarNumber();
        put.execute( MapsActivity.user.getId(), et.getText().toString());

    }

    public void pressMaps(View v){
        finish();
    }

    public void manageParking(View v){
        if (MapsActivity.user.getCarNumber() == null){
            return;
        }

        Switch switchP = findViewById(R.id.switchParkavimas);
//        LatLng userLoc = new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude());

        String zoneColor = null;

//        for (Zone z : MapsActivity.zones){
//            List<LatLng> points = new ArrayList<>();
//            for (Coords c : z.getBounds()){
//                points.add(new LatLng(c.getX(), c.getY()));
//            }
//            if(PolyUtil.containsLocation(MapsActivity.userLocation, points, true)){
//                zoneColor = z.getColor();
//                break;
//            }
//        }
        zoneColor = MapsActivity.currentZone.getColor();

        if (zoneColor == null) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Įspėjimas");
            alert.setMessage("Zona nerasta - parkavimas nepradėtas");
            alert.create().show();
            return;
        }

        //https://stackoverflow.com/questions/31642449/find-out-if-a-location-is-within-a-shape-drawn-with-polygon-on-google-maps-v2/31642731

        SmsManager smsManager = SmsManager.getDefault();
        if (switchP.isChecked()){
//            smsManager.sendTextMessage("1332", null, "Start " + currentZone + " " + MapsActivity.user.getCarNumber(), null, null);
            try{
                smsManager.sendTextMessage("+1-555-521-5554", null, "Start " + zoneColor.toUpperCase() + " " + MapsActivity.user.getCarNumber(), null, null);
            }
            catch (Exception e){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
        else{
//            smsManager.sendTextMessage("1332", null, "STOP", null, null);
            smsManager.sendTextMessage("+1-555-521-5554", null, "STOP", null, null);
        }
    }

    private final class PutCarNumber extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = "/settings";
            String send = "{" +
                    "\"id\": \"" + params[0]
                    + "\", \"carNumber\": \"" + params[1] +"\"}";
            try {
                return NetController.sendPut(url, send);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("GAUTA: " + result);
            if(result == "Error") return;
            Gson gson = new Gson();
            User u = gson.fromJson(result, new TypeToken<User>() {}.getType());
            MapsActivity.user.setCarNumber(u.getCarNumber());
        }
    }
}