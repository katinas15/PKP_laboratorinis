package com.example.maps.Objects;

public class User {
    private String _id;
    private String email;
    private String carNumber;

    public User(String id, String email, String carNumber) {
        this._id = id;
        this.email = email;
        this.carNumber = carNumber;
    }

    public String getId() {
        return _id;
    }

    public String getEmail() {
        return email;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
