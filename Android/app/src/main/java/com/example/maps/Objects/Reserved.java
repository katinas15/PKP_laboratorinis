package com.example.maps.Objects;

public class Reserved {
    private String userID;
    private String unofZoneID;
    private String timestamp;
    private String timeElapsed;

    public Reserved(String userID, String unofZoneID, String timestamp, String timeElapsed){
        this.userID = userID;
        this.unofZoneID = unofZoneID;
        this.timestamp = timestamp;
        this.timeElapsed = timeElapsed;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUnofZoneID() {
        return unofZoneID;
    }

    public void setUnofZoneID(String unofZoneID) {
        this.unofZoneID = unofZoneID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimeStampt(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(String timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}
