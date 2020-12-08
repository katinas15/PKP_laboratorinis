package com.example.maps.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maps.Controllers.NetController;
import com.example.maps.R;

public class ReservationActivity extends AppCompatActivity {

    TextView tvStart;
    TextView tvEnd;
    Button button;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        
        Intent intent = getIntent();
        id = intent.getExtras().getString("id");

        tvStart = findViewById(R.id.start);
        tvEnd = findViewById(R.id.end);
        button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i("a", "clicked");
                Reserve r = new Reserve();
                r.execute(String.valueOf(tvStart.getText()), String.valueOf(tvEnd.getText()));
            }
        });
    }


    private final class Reserve extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = "/reservation";
            String send = "{" +
                    "\"userId\":" + "\"" + MapsActivity.user.getId() + "\"," +
                    "\"zoneId\":" + "\"" + id + "\"," +
                    "\"start\": \"" + params[0] + "\"," +
                    "\"end\": \"" + params[1]
                    + "\"}";
            try {
                return NetController.sendPost(url, send);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("GAUTA: " + result);
            if(result.equals("jau rezervuota")) {
                Toast.makeText(ReservationActivity.this, "jau rezervuota", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(ReservationActivity.this, "Rezervuota", Toast.LENGTH_LONG).show();
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