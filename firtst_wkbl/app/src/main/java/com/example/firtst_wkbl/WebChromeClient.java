package com.example.myapplication8;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebChromeClient extends android.webkit.WebChromeClient {

    private Context context;
    private WebView webView;

    public WebChromeClient(Context context, WebView webView) {
        this.context = context;
        this.webView = webView;
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message,
                             final android.webkit.JsResult result) {
        new AlertDialog.Builder(context)
//                        .setTitle("WKBL")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,
                        (dialog, which) -> result.confirm())
                .setCancelable(false)
                .create()
                .show();
        return true;
    }

    @Override
    public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message
            resultMsg) {
        WebView newWebView = new WebView(context);
        newWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                webView.loadUrl(url);
            }
        });
        ((WebView.WebViewTransport) resultMsg.obj).setWebView(newWebView);
        resultMsg.sendToTarget();

        return true;
    }


    @Override
    public void onCloseWindow(WebView window) {
        super.onCloseWindow(window);
    }
}
