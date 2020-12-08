package com.example.maps.Activities;

import android.content.DialogInterface;
import android.os.Bundle;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.maps.Controllers.NetController;
import com.example.maps.R;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Map;

public class CreateZoneActivity extends AppCompatActivity {
    double x;
    double y;
    boolean isNew;
    String zoneId;

    EditText pav;
    EditText apr;
    EditText nuo;
    EditText iki;
    EditText kaina;
    EditText vietos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_zone);
        Intent dabar = this.getIntent();
        x = (double)dabar.getSerializableExtra("x");
        y = (double)dabar.getSerializableExtra("y");
        isNew = (boolean)dabar.getSerializableExtra("isNew");

        pav = findViewById(R.id.pavadinimasOver);
        apr = findViewById(R.id.aprasymasOver);
        nuo = findViewById(R.id.nuoInput);
        iki = findViewById(R.id.ikiInput);
        kaina = findViewById(R.id.kainaOver);
        vietos = findViewById(R.id.vietosOver);

        if(!isNew){
            pav.setText(MapsActivity.currentUserMarker.getName());
            apr.setText(MapsActivity.currentUserMarker.getDescription());
            nuo.setText(MapsActivity.currentUserMarker.getTimeStart());
            iki.setText(MapsActivity.currentUserMarker.getTimeEnd());
            findViewById(R.id.buttonDelete).setVisibility(View.VISIBLE);

            if(MapsActivity.currentUserMarker.getSpots() != null){
                vietos.setText(Integer.toString(MapsActivity.currentUserMarker.getSpots()));
            }


            if((int)MapsActivity.currentUserMarker.getKaina() == 0){
                kaina.setFocusable(false);
                kaina.setFocusableInTouchMode(false);
                kaina.setClickable(false);
                kaina.setText("0");
                CheckBox checkBox = findViewById(R.id.nemokama);
                checkBox.setChecked(true);
            }
            else{
                kaina.setText(Float.toString(MapsActivity.currentUserMarker.getKaina()));
            }
            if(MapsActivity.currentUserMarker.getTimeStart().equals("00:00") && MapsActivity.currentUserMarker.getTimeEnd().equals("23:59")){
                iki.setFocusable(false);
                iki.setFocusableInTouchMode(false);
                iki.setClickable(false);
                nuo.setFocusable(false);
                nuo.setFocusableInTouchMode(false);
                nuo.setClickable(false);
                CheckBox checkBox2 = findViewById(R.id.visaPara);
                checkBox2.setChecked(true);
            }
        }
    }

    public void visaParaChange(View v){
        EditText iki = findViewById(R.id.ikiInput);
        EditText nuo = findViewById(R.id.nuoInput);
        CheckBox checkBox = findViewById(R.id.visaPara);
        if(checkBox.isChecked()){
            iki.setFocusable(false);
            iki.setFocusableInTouchMode(false);
            iki.setClickable(false);
            nuo.setFocusable(false);
            nuo.setFocusableInTouchMode(false);
            nuo.setClickable(false);
            nuo.setText("00:00");
            iki.setText("23:59");
        }
        else{
            iki.setFocusable(true);
            iki.setFocusableInTouchMode(true);
            iki.setClickable(true);
            nuo.setFocusable(true);
            nuo.setFocusableInTouchMode(true);
            nuo.setClickable(true);
            nuo.setText("");
            iki.setText("");
        }
    }

    public void nemokamaChange(View v){
        EditText kaina = findViewById(R.id.kainaOver);
        CheckBox checkBox = findViewById(R.id.nemokama);
        if(checkBox.isChecked()){
            kaina.setFocusable(false);
            kaina.setFocusableInTouchMode(false);
            kaina.setClickable(false);
            kaina.setText("0");
        }
        else{
            kaina.setFocusable(true);
            kaina.setFocusableInTouchMode(true);
            kaina.setClickable(true);
            kaina.setText("");
        }
    }

    public void issaugotiZona(View v){

        if(pav.getText().toString().length() < 3 || nuo.getText().toString().length() < 5 || iki.getText().toString().length() < 5 || nuo.getText().toString().length() < 1){
            Toast.makeText(CreateZoneActivity.this, "Pilnai užpildykite visus laukus", Toast.LENGTH_LONG).show();
        }
        else{
            String send;

            send = "{\"name\": \"" + pav.getText().toString() + "\", " +
                    "\"point\": {" +
                    "\"x\":" + x +
                    ",\"y\":" + y +
                    "}," +
                    " \"description\":\"" + apr.getText().toString() + "\", \"timeStart\": \"" + nuo.getText().toString()
                        + "\", \"timeEnd\":\"" + iki.getText().toString() + "\", \"image\": \"empty\"," +
                    "\"userId\":\"" + MapsActivity.user.getId() + "\"," +
                    "\"kaina\":\"" + kaina.getText().toString() + "\"," +
                    "\"numberOfSpots\":\"" + vietos.getText().toString() + "\"" +
                    "}" ;


            if(isNew) {
                UserZoneRegister reg = new UserZoneRegister();
                reg.execute(send);
            }
            else{
                UserZoneUpdate upd = new UserZoneUpdate();
                upd.execute(MapsActivity.currentUserMarker.get_id(), send);
            }
        }
    }

    public void deleteZone(View v){
        final String send = "{\"userId\": \"" + MapsActivity.user.getId() + "\"}";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Zona bus ištrinta!");
        builder.setMessage("Ar tikrai norite ištrinti zoną?");
        builder.setPositiveButton("Taip",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserZoneDelete uzd = new UserZoneDelete();
                        uzd.execute(MapsActivity.currentUserMarker.get_id(), send);
                    }
                });
        builder.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private final class UserZoneRegister extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(CreateZoneActivity.this, "Creating zone...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = "/userZone";
            String postDataParams = params[0];
            System.out.println("ISSIUSTA: " + postDataParams);
            try {
                return NetController.sendPost(url, postDataParams);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error! Unable to retrieve data from database!";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("GAUTA: " + result);
            finish();
        }
    }

    private final class UserZoneUpdate extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(CreateZoneActivity.this, "Updating zone...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = "/userZone/" + params[0];
            String postDataParams = params[1];
            System.out.println("ISSIUSTA: " + postDataParams);
            try {
                return NetController.sendPut(url, postDataParams);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error! Unable to retrieve data from database!";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("GAUTA: " + result);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    MapsActivity.map.markerClick(MapsActivity.currentUserMarker.getPoint().getX(), MapsActivity.currentUserMarker.getPoint().getY());
                                }
                            });
                        }
                    },
                    1000
            );
            finish();
        }
    }

    private final class UserZoneDelete extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(CreateZoneActivity.this, "Deleting zone...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = "/userZone/" + params[0];
//            System.out.println(url);
            String postDataParams = params[1];
//            System.out.println("ISSIUSTA: " + postDataParams);
            try {
                return NetController.sendDelete(url, postDataParams);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error! Unable to retrieve data from database!";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("GAUTA: " + result);
            if(!result.equals("this marker doesnt belong to you")) {
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                MapsActivity.map.refreshMarkers();
                            }
                        },
                        1000
                );
            }
            finish();
        }
    }

}
