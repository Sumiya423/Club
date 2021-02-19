package com.example.cpclubltd;

import java.io.Serializable;

public class ApplInfo implements Serializable {

    public ApplInfo() {

    }

    private String sId;
    private String sName;
    private String evID;
    private String sEmail;
    private String sDetails;

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getEvID() {
        return evID;
    }

    public void setEvID(String evID) {
        this.evID = evID;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsDetails() {
        return sDetails;
    }

    public void setsDetails(String sDetails) {
        this.sDetails = sDetails;
    }

    public ApplInfo(String sId, String sName, String evID, String sEmail, String sDetails) {
        this.sId = sId;
        this.sName = sName;
        this.evID = evID;
        this.sEmail = sEmail;
        this.sDetails = sDetails;
    }
}
