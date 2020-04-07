package com.example.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maps.R;
import com.example.maps.NetController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void prisijungi(View v) {
        EditText log = findViewById(R.id.l_login);
        EditText pas = findViewById(R.id.l_pass);
        String login = log.getText().toString();
        String pass = pas.getText().toString();
        String siuntimui = "{\"email\":\"" + login + "\", \"password\":\"" + pass + "\"}";
        UserLogin prisijungti = new UserLogin();
        prisijungti.execute(siuntimui);
    }

    public void registerUser(View v){
        Intent register_window = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(register_window);
    }

    private final class UserLogin extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(LoginActivity.this, "Logging in...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = "/login";
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
            if (result != null && result.contains("{")) {
                Gson gson = new Gson();
                User u = gson.fromJson(result, new TypeToken<User>() {}.getType());
                Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                intent.putExtra("userId", result);
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "Error! Bad login or password", Toast.LENGTH_LONG).show();
            }
        }
    }
}




