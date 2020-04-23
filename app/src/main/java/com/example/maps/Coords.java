package com.example.maps;

public class Coords {
    private String id;
    private double x;
    private double y;

    public Coords(String id, int x, int y) {
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
