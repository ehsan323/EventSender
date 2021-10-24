package com.adjust.adjusthomework.data.network;

import com.adjust.adjusthomework.data.model.SecondResponse;

public interface SecondCallback {
    void processFinish(SecondResponse output, int queue);
    void processFailed(String error);
}
