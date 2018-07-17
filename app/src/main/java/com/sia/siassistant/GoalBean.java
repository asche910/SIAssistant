package com.sia.siassistant;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class GoalBean extends DataSupport implements Serializable{
    private String name;
    private String days;
    private String start;
    private String note;
    private String uri;
    private String clock;
    private int curDay;
    private boolean isClick;

    public GoalBean(String name, String days, String start, String note, String uri, String clock, int curDay, boolean isClick) {
        this.name = name;
        this.days = days;
        this.start = start;
        this.note = note;
        this.uri = uri;
        this.clock = clock;
        this.curDay = curDay;
        this.isClick = isClick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }


    public int getCurDay() {
        return curDay;
    }

    public void setCurDay(int curDay) {
        this.curDay = curDay;
    }


    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

}
