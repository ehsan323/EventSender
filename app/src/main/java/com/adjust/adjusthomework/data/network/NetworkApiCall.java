package com.adjust.adjusthomework.data.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NetworkApiCall {

    public String requestURL = "";
    public HashMap<String, String> postDataParams;
    public HTTPCallback delegate = null;
    public int res_code = 0;

    public NetworkApiCall(String requestURL, HashMap<String, String> postDataParams, HTTPCallback asyncResponse) {
        this.delegate = asyncResponse;
        this.postDataParams = postDataParams;
        this.requestURL = requestURL;
    }


    public void doInWorkerThread() {
        final Handler handler = new Handler(Looper.getMainLooper());
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            String response = NetworkApiCall.this.performPostCall(requestURL, postDataParams);
            handler.post(() -> NetworkApiCall.this.onPostExecute(response));
        });
    }


    public void onPostExecute(String result) {
        if (res_code >= 200 && res_code < 300) {
            delegate.processFinish(result);
        } else {
            delegate.processFailed(res_code, result);
        }
    }

    public String performPostCall(String requestURL, HashMap<String, String> postDataParams) {
        Log.e("HTTP Request URL", requestURL);
        URL url;
        StringBuilder response = new StringBuilder();
        try {
            url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(60000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(NetworkUtil.getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            Log.e("HTTP Response Code", Integer.toString(responseCode));
            res_code = responseCode;
            if (responseCode >= 200 && responseCode < 300) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            } else if (responseCode >= 400 && responseCode < 500) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            } else if (responseCode >= 500 && responseCode < 600) {
                response.append("Internal Server Error");
            } else {
                response = new StringBuilder();
            }
        } catch (Exception e) {
            response.append(e.getMessage());
        }
        return response.toString();
    }


}
