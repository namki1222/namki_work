package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;

import cz.msebera.android.httpclient.cookie.Cookie;

class webViewActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Intent intent = getIntent();

        String myUrlAddress = intent.getStringExtra("myurl");
        webView = findViewById(R.id.wv);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        List<Cookie> cookies = SessionControl.cookies;
        cookieManager.removeAllCookie();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieString = cookie.getName() + "=" + cookie.getValue() + "; Domain=" + cookie.getDomain();
                cookieManager.setCookie(cookie.getDomain(), cookieString);

            }
        }

        webView.loadUrl(myUrlAddress);
    }

    public void onResume() {
        super.onResume();

    }

    public void onPause() {
        super.onPause();

    }
}
