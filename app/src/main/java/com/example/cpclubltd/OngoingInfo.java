package com.example.cpclubltd;

import java.io.Serializable;

public class OngoingInfo implements Serializable {
    private String id;
    private String name;
    private String wing;
    private String date;
    private String details;

    public OngoingInfo(){

    }

   public OngoingInfo( String name, String wing, String date, String details) {

        this.name = name;
        this.wing = wing;
        this.date = date;
        this.details = details;
    }
    public OngoingInfo(String id, String name, String wing, String date, String details) {
        this.id = id;
        this.name = name;
        this.wing = wing;
        this.date = date;
        this.details = details;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWing() {
        return wing;
    }

    public void setWing(String wing) {
        this.wing = wing;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
