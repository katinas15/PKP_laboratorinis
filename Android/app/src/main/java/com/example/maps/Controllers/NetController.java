package com.example.maps.Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetController {
    public static String serverIp = "https://kompleksinis-projektas.herokuapp.com";
//    public static String serverIp = "http://192.168.0.105:3000";

    public static String sendPost(String r_url , String postDataParams) throws Exception {

        System.out.println("POST PARAMS - " + postDataParams);
        URL url = new URL(serverIp + r_url);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(20000);
        conn.setConnectTimeout(20000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        int responseCode;
        try {
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(os, "UTF-8"));
            writer.write(postDataParams);
            writer.flush();
            writer.close();
            os.close();
            responseCode = conn.getResponseCode();
        }
        catch (Exception e){
            return "Server error";
        }

        if (responseCode == HttpsURLConnection.HTTP_OK)
        {
            BufferedReader in=new BufferedReader( new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer("");
            String line="";
            while((line = in.readLine()) != null) {
                sb.append(line);
                break;
            }
            in.close();
            return sb.toString();
        }
        return null;
    }
    public static String sendGet(String url) throws IOException {
        URL obj = new URL(serverIp + url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setReadTimeout(20000);
        conn.setConnectTimeout(20000);
        conn.setRequestMethod("GET");
        int responseCode;
        try {
            responseCode = conn.getResponseCode();
        }
        catch (Exception e){
            return "Server error";
        }
        System.out.println("Response Code :" + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // connection ok
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            return "";
        }
    }

    public static String sendPut(String r_url , String postDataParams) throws Exception {
        URL url = new URL(serverIp + r_url);

        System.out.println("PUT - " + postDataParams);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(20000);
        conn.setConnectTimeout(20000);
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        int responseCode;
        try {
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(os, "UTF-8"));
            writer.write(postDataParams);
            writer.flush();
            writer.close();
            os.close();
            responseCode = conn.getResponseCode();
        }
        catch (Exception e){
            return "Server error";
        }

        if (responseCode == HttpsURLConnection.HTTP_OK)
        {
            BufferedReader in=new BufferedReader( new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer("");
            String line="";
            while((line = in.readLine()) != null) {
                sb.append(line);
                break;
            }
            in.close();
            return sb.toString();
        }
        return null;
    }

    public static String sendDelete(String r_url , String postDataParams) throws Exception {
        URL url = new URL(serverIp + r_url);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(20000);
        conn.setConnectTimeout(20000);
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        int responseCode;
        try {
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(os, "UTF-8"));
            writer.write(postDataParams);
            writer.flush();
            writer.close();
            os.close();
            responseCode = conn.getResponseCode();
        }
        catch (Exception e){
            return "Server error";
        }
        if (responseCode == HttpsURLConnection.HTTP_OK)
        {
            BufferedReader in=new BufferedReader( new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer("");
            String line="";
            while((line = in.readLine()) != null) {
                sb.append(line);
                break;
            }
            in.close();
            return sb.toString();
        }
        return null;
    }

}