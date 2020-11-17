package com.example.maps.Objects;

public class Coords {
    private String id;
    private double x;
    private double y;

    public Coords(String id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
