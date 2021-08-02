package com.example.myapplication8;

import java.util.List;

import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class SessionControl {
    static public DefaultHttpClient httpclient = null;
    static public List cookies = null;

    public static HttpClient getHttpclient() {
        if (httpclient == null) {
            SessionControl.setHttpclient(new DefaultHttpClient());
        }
        return httpclient;
    }

    public static void setHttpclient(DefaultHttpClient httpclient) {
        SessionControl.httpclient = httpclient;
    }
}
