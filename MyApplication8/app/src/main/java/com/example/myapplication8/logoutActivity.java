package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.util.List;

import cz.msebera.android.httpclient.cookie.Cookie;

public class logoutActivity extends Activity {

    private WebView webView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);
        Intent intent = getIntent();
        String myUrlAddress = intent.getStringExtra("myurl");
        webView = findViewById(R.id.wv);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        Button logout;
        logout = (Button) findViewById(R.id.logout_button);
        webView.loadUrl(myUrlAddress);
        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.setAcceptCookie(true);
                List<Cookie> cookies = SessionControl.cookies;
                cookieManager.removeAllCookie();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        String cookieString = cookie.getName() + "=" + cookie.getValue() + "; Domain=" + cookie.getDomain();
                        System.out.println(cookieString);
                        cookieManager.setCookie(cookie.getDomain(), cookieString);
                    }
                }
                String[] st = new String[4];
                new Thread() {
                    public void run() {
                        String result= ServerOperation.post(st, "http://192.168.0.84:92/logout.php");
                    }
                }.start();
                finish();
            }

        });

    }
}
