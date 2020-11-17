package com.example.maps.Objects;

import com.example.maps.Objects.Coords;

import java.util.List;

public class Zone {
    private String id;
    private String name;
    private String color;
    private List<Coords> bounds;

    public Zone(String id, String name, String color, List<Coords> bounds) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bounds = bounds;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<Coords> getBounds() {
        return bounds;
    }
}
