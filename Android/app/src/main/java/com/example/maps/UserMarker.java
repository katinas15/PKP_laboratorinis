package com.example.maps;

import java.util.List;

public class UserMarker {
    private String id;
    private String name;
    private Coords point;
    private String description;
    private String timeStart;
    private String timeEnd;
    private String image;

    public UserMarker(String id, String name, Coords point, String description, String timeStart, String timeEnd, String image) {
        this.id = id;
        this.name = name;
        this.point = point;
        this.description = description;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coords getPoint() {
        return point;
    }

    public String getDescription() {
        return description;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getImage() {
        return image;
    }
}
