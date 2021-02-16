package com.example.cpclubltd;

import java.io.Serializable;

public class UpcomingInfo implements Serializable {
    private String up_id;
    private String up_name;
    private String up_wing;
    private String up_date;
    private String up_details;

    public UpcomingInfo(){

    }

   public UpcomingInfo(String up_name, String up_wing, String up_date, String up_details) {

        this.up_name = up_name;
        this.up_wing = up_wing;
        this.up_date = up_date;
        this.up_details = up_details;
    }

    public String getUp_id() {
        return up_id;
    }

    public void setUp_id(String up_id) {
        this.up_id = up_id;
    }

    public String getUp_name() {
        return up_name;
    }

    public void setUp_name(String up_name) {
        this.up_name = up_name;
    }

    public String getUp_wing() {
        return up_wing;
    }

    public void setUp_wing(String up_wing) {
        this.up_wing = up_wing;
    }

    public String getUp_date() {
        return up_date;
    }

    public void setUp_date(String up_date) {
        this.up_date = up_date;
    }

    public String getUp_details() {
        return up_details;
    }

    public void setUp_details(String up_details) {
        this.up_details = up_details;
    }

    public UpcomingInfo(String up_id, String up_name, String up_wing, String up_date, String up_details) {
        this.up_id = up_id;
        this.up_name = up_name;
        this.up_wing = up_wing;
        this.up_date = up_date;
        this.up_details = up_details;
    }

}
