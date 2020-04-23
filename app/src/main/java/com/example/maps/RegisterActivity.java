package com.example.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maps.R;
import com.example.maps.NetController;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    public void register(View v){
        EditText log = findViewById(R.id.twRegLogin);
        EditText pass = findViewById(R.id.twRegPass);
        CheckBox chkBox = findViewById(R.id.checkBox);
        if(!chkBox.isChecked()){
            Toast.makeText(RegisterActivity.this, "Sutikite su naudotojo salygomis", Toast.LENGTH_LONG).show();
            return;
        }

        if(log.getText().toString().length() < 3 || pass.getText().toString().length() < 8){
            Toast.makeText(RegisterActivity.this, "Login and password have to be at least 3 characters", Toast.LENGTH_LONG).show();
        }
        else{
            String send;
            send = "{\"email\": \"" + log.getText().toString() + "\", \"password\":\"" + pass.getText().toString() + "\"}";

            UserRegister uReg = new UserRegister();
            uReg.execute(send);
        }
    }

    public void goBack(View v){
        Intent main_window = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(main_window);
    }

    public void taisykles(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/End-user_license_agreement"));
        startActivity(browserIntent);
    }

    private final class UserRegister extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(RegisterActivity.this, "Creating user...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = "/register";
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
            if (result != null) {

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(RegisterActivity.this, "Error! Unable to retrieve data from database!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
