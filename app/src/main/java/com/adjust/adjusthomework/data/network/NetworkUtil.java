package com.adjust.adjusthomework.data.network;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class NetworkUtil {

    public static final String BaseUrl = "https://jsonplaceholder.typicode.com/posts";

    public static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            Log.e("POST KEY VAL", entry.getKey() + "," + entry.getValue());
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        Log.e("Request", result.toString());
        return result.toString();
    }

}
