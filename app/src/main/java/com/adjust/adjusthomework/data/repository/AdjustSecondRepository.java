package com.adjust.adjusthomework.data.repository;


import android.os.Handler;
import android.util.Log;

import com.adjust.adjusthomework.data.model.SecondResponse;
import com.adjust.adjusthomework.data.network.HTTPCallback;
import com.adjust.adjusthomework.data.network.NetworkApiCall;
import com.adjust.adjusthomework.data.network.NetworkUtil;
import com.adjust.adjusthomework.data.network.SecondCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import androidx.annotation.NonNull;

public class AdjustSecondRepository {

    public static final String SECONDS = "seconds";
    public final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
    public final HashSet<Integer> set = new HashSet<>();

    /**
     * Handle one value or values
     *
     * @param values        a list of values
     * @param asyncResponse callback
     */
    public void sendSecondEvent(Integer[] values, SecondCallback asyncResponse) {
        if (values.length == 1) {
            addSecondToQueue(values[0]);
            sendEventToServer(asyncResponse);
        } else if (values.length > 1) {
            queue.addAll(Arrays.asList(values));
            sendEventToServer(asyncResponse);
        }

    }

    /**
     * check queue and send data to server
     *
     * @param asyncResponse Callback
     */
    public void sendEventToServer(SecondCallback asyncResponse) {
        if (queue.size() > 0) {
            try {
                new Handler().postDelayed(() -> {
                    Integer value = queue.remove();
                    set.remove(value);
                    AdjustSecondRepository.this.getNetworkApiCall(asyncResponse, value).doInWorkerThread();
                },5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Check and add value to queue
     *
     * @param second value
     */
    private void addSecondToQueue(Integer second) {
        if (!set.contains(second)) {
            set.add(second);
            queue.add(second);
        }
    }


    /**
     * Network Call
     *
     * @param asyncResponse CallBack
     * @param queueValue    value
     * @return Server Result
     */
    @NonNull
    private NetworkApiCall getNetworkApiCall(SecondCallback asyncResponse, int queueValue) {
        HashMap<String, String> requestData = new HashMap<>();
        requestData.put(SECONDS, String.valueOf(queueValue));
        return new NetworkApiCall(NetworkUtil.BaseUrl, requestData, new HTTPCallback() {
            @Override
            public void processFinish(String response) {
                try {
                    asyncResponse.processFinish(parseResponse(response), queueValue);
                } catch (JSONException e) {
                    asyncResponse.processFailed(e.getMessage());
                }
                if (queue.size() > 0) {
                    sendEventToServer(asyncResponse);
                }

                Log.e("Response", response);
            }

            @Override
            public void processFailed(int responseCode, String output) {
                asyncResponse.processFailed(output);
                addSecondToQueue(queueValue);
                sendEventToServer(asyncResponse);
                Log.e("Response Failed", Integer.toString(responseCode) + " - " + output);
            }
        });
    }


    /**
     * Parse Response to Data Model
     *
     * @param response String response from server
     * @return Model
     * @throws JSONException Exception
     */
    private SecondResponse parseResponse(String response) throws JSONException {
        JSONObject responseObject = new JSONObject(response);
        String id = responseObject.getString("id");
        String seconds = responseObject.getString("seconds");
        return new SecondResponse(id, seconds);
    }

}
