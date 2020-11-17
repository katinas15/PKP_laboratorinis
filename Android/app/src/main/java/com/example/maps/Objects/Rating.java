package com.example.maps.Objects;

public class Rating {
    private String _id;
    private String userId;
    private String userZoneId;
    private float rating;


    public Rating(String _id, String userId, String userZoneId, float rating) {
        this._id = _id;
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

    public String get_id() {
        return _id;
    }
}
