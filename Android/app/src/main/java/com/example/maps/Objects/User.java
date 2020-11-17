package com.example.maps.Objects;

public class User {
    private String _id;
    private String email;

    public User(String id, String email) {
        this._id = id;
        this.email = email;
    }

    public String getId() {
        return _id;
    }

    public String getEmail() {
        return email;
    }
}
