package com.sia.siassistant;

import org.litepal.crud.DataSupport;

public class AlarmBean extends DataSupport {

    private int id;
    private String time;
    private String days;
    private boolean isOn;

    public AlarmBean() {
    }

    public AlarmBean(int id, String time, String days, boolean isOn) {
        this.id = id;
        this.time = time;
        this.days = days;
        this.isOn = isOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}
