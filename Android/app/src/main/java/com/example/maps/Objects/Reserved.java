package com.example.maps.Objects;

public class Reserved {
    private String userID;
    private String unofZoneID;
    private String timeStart;
    private String timeEnd;

    public Reserved(String userID, String unofZoneID, String timeStart, String timeEnd){
        this.userID = userID;
        this.unofZoneID = unofZoneID;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
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

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
