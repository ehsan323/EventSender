package com.adjust.adjusthomework.data.network;

public interface HTTPCallback {

    void processFinish(String output);
    void processFailed(int responseCode, String output);
}
