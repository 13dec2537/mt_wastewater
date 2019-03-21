package com.example.mtwastewater.Models;

public class Control {
    private String flag;
    private String hourly[];

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String[] getHourly() {
        return hourly;
    }

    public void setHourly(String[] hourly) {
        this.hourly = hourly;
    }
}
