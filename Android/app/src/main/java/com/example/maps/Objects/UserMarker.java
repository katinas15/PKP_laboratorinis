package com.example.maps.Objects;

import com.example.maps.Objects.Coords;

public class UserMarker {
    private String _id;
    private String userId;
    private String name;
    private Coords point;
    private String description;
    private String timeStart;
    private String timeEnd;
    private String image;
    private float kaina;
    private Integer numberOfSpots;
    private Integer occupiedSpots;

    public UserMarker(String id, String name, Coords point, String description, String timeStart, String timeEnd, String image, String userId, float kaina, int numberOfSpots, int occupiedSpots) {
        this._id = id;
        this.name = name;
        this.point = point;
        this.description = description;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.image = image;
        this.userId = userId;
        this.kaina = kaina;
        this.numberOfSpots = numberOfSpots;
        this.occupiedSpots = occupiedSpots;
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

    public String get_id() {
        return _id;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getSpots() {
        if(numberOfSpots == null) {
            return 1;
        } else {
            return numberOfSpots;
        }
    }

    public Integer getOccupiedSpots() {
        if(occupiedSpots == null){
            return 0;
        } else {
            return occupiedSpots;
        }
    }
}
