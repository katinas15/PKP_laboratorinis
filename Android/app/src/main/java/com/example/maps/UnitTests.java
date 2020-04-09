package com.example.maps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class UnitTests {
    @Test
    public void Login(){
        String login = "t@t.lt";
        String pass = "testtest";
        String send = "{\"email\":\"" + login + "\", \"password\":\"" + pass + "\"}";
        String expectedResult = "{\"_id\":\"5e8cc76a6b01af2af17fe997\"," +
                "\"password\":\"$2b$08$UDDiGd9gEKQfazZnIFdVlOC9Sn2aChzVJXFZhOxHCeRmoyZ4ka4PW\"," +
                "\"email\":\"t@t.lt\",\"__v\":0}";
        String url = "/login";
        String answer;
        try {
            answer = NetController.sendPost(url, send);
        } catch (Exception e) {
            e.printStackTrace();
            answer = "Error";
        }

        System.out.println("expected - " + expectedResult);
        System.out.println("result - " + answer);
        assertEquals(expectedResult, answer);
    }

    @Test
    public void Register(){
        int random = new Random().nextInt(10*10);
        String login = "unittest" + random + "@t.lt";
        String pass = "unittest" + random;
        String send = "{\"email\":\"" + login + "\", \"password\":\"" + pass + "\"}";
        String expectedResult = "\"unittest" + random + "@t.lt\"";
        String url = "/register";


        String answer;
        try {
            answer = NetController.sendPost(url, send);
        } catch (Exception e) {
            e.printStackTrace();
            answer = "Error";
        }

        JsonObject jobj = new Gson().fromJson(answer, JsonObject.class);
        String result = jobj.get("email").toString();


        System.out.println("expected - " + expectedResult);
        System.out.println("result - " + result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void GetMainZones(){
        String url = "/mainZone";

        String answer;
        try {
            answer = NetController.sendGet(url);
        } catch (Exception e) {
            e.printStackTrace();
            answer = "Error";
        }

        List<Zone> zones = new Gson().fromJson(answer, new TypeToken<List<Zone>>() {
        }.getType());

        System.out.println("result - " + answer);
        assertTrue(zones.size() > 0);
    }

    @Test
    public void GetUserZones(){
        String url = "/userZone";

        String answer;
        try {
            answer = NetController.sendGet(url);
        } catch (Exception e) {
            e.printStackTrace();
            answer = "Error";
        }

        List<UserMarker> markers = new Gson().fromJson(answer, new TypeToken<List<UserMarker>>() {
        }.getType());

        System.out.println("result - " + answer);
        assertTrue(markers.size() > 0);
    }

    @Test
    public void GetZoneRating(){
        String markerId = "5e8ee1fe54a93509f1dd6ce5";

        String url = "/userZone/" + markerId + "/rate";


        String answer;
        try {
            answer = NetController.sendGet(url);
        } catch (Exception e) {
            e.printStackTrace();
            answer = "Error";
        }

        List<Rating> ratings = new Gson().fromJson(answer, new TypeToken<List<Rating>>() {}.getType());

        System.out.println("result - " + answer);
        assertTrue(ratings.size() > 0);
    }


    @Test
    public void ChangeZoneRating(){
        String ratingId = "5e8ef31c54a93509f1dd6ce9";
        String url = "/rating/" + ratingId ;

        int rateValue = new Random().nextInt(5) + 1;
        String send =
            "{" + "\"rating\":" + rateValue + " }";

        String answer;
        try {
            answer = NetController.sendPut(url, send);
        } catch (Exception e) {
            e.printStackTrace();
            answer = "Error";
        }

        Rating rating = new Gson().fromJson(answer, new TypeToken<Rating>() {}.getType());

        System.out.println("result - " + answer);
        assertTrue(rating.getRating() == rateValue);
    }

//test cases
//create marker
//        rate marker
//                zone download
//                        register
//    login
//                                get directions


}
