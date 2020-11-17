package com.example.maps.Activities;

import android.os.Bundle;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maps.Controllers.NetController;
import com.example.maps.R;

public class CreateZoneActivity extends AppCompatActivity {
    double x;
    double y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_zone);
        Intent dabar = this.getIntent();
        x = (double)dabar.getSerializableExtra("x");
        y = (double)dabar.getSerializableExtra("y");
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
        String userId = "1";

        EditText pav = findViewById(R.id.pavadinimasOver);
        EditText apr = findViewById(R.id.aprasymasOver);
        EditText nuo = findViewById(R.id.nuoInput);
        EditText iki = findViewById(R.id.ikiInput);
        EditText kaina = findViewById(R.id.kainaOver);
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
                    "\"userId\":\"" + userId + "\"," +
                    "\"kaina\":\"" + kaina.getText().toString() + "\"" +
                    "}" ;


            UserZoneRegister reg = new UserZoneRegister();
            reg.execute(send);
        }
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

}
