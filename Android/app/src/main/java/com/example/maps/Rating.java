package com.example.maps;

public class Rating {
    private String userId;
    private String userZoneId;
    private float rating;

    public Rating(String userId, String userZoneId, float rating) {
        this.userId = userId;
        this.userZoneId = userZoneId;
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserZoneId() {
        return userZoneId;
    }

    public float getRating() {
        return rating;
    }
}
