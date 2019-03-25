package com.example.mtwastewater.Models;

public class Viewer {
    private int waste_id;
    private String waste_date,waste_uid,waste_status,name;

    public int getWaste_id() {
        return waste_id;
    }

    public void setWaste_id(int waste_id) {
        this.waste_id = waste_id;
    }

    public String getWaste_date() {
        return waste_date;
    }

    public void setWaste_date(String waste_date) {
        this.waste_date = waste_date;
    }

    public String getWaste_uid() {
        return waste_uid;
    }

    public void setWaste_uid(String waste_uid) {
        this.waste_uid = waste_uid;
    }

    public String getWaste_status() {
        return waste_status;
    }

    public void setWaste_status(String waste_status) {
        this.waste_status = waste_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
