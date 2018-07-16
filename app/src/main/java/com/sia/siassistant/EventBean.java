package com.sia.siassistant;

import android.net.Uri;

import org.litepal.crud.DataSupport;

import java.util.List;

public class EventBean extends DataSupport {

    private String userHead;
    private String userName;
    private String content;
    private String time;

    private boolean isAddImg;
    private List<Uri> uriList;

    public EventBean(String userHead, String userName, String content, String time, boolean isAddImg, List<Uri> uriList) {
        this.userHead = userHead;
        this.userName = userName;
        this.content = content;
        this.time = time;
        this.isAddImg = isAddImg;
        this.uriList = uriList;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isAddImg() {
        return isAddImg;
    }

    public void setAddImg(boolean addImg) {
        isAddImg = addImg;
    }

    public List<Uri> getUriList() {
        return uriList;
    }

    public void setUriList(List<Uri> uriList) {
        this.uriList = uriList;
    }
}
