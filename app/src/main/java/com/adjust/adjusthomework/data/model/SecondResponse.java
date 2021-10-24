package com.adjust.adjusthomework.data.model;

public class SecondResponse {

    String id;
    String seconds;

    public SecondResponse(String id, String seconds) {
        this.id = id;
        this.seconds = seconds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }
}
