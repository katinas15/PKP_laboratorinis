package com.example.maps.Objects;

import com.example.maps.Objects.Coords;

public class UserMarker {
    private String _id;
    private String name;
    private Coords point;
    private String description;
    private String timeStart;
    private String timeEnd;
    private String image;
    private float kaina;

    public UserMarker(String id, String name, Coords point, String description, String timeStart, String timeEnd, String image, float kaina) {
        this._id = id;
        this.name = name;
        this.point = point;
        this.description = description;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.image = image;
        this.kaina = kaina;
    }

    public float getKaina() {
        return kaina;
    }

    public String getId() {
        return _id;
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
