package com.example.maps;

public class Coords {
    private String id;
    private float x;
    private float y;

    public Coords(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
