package com.example.post;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import cz.msebera.android.httpclient.client.ClientProtocolException;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class register extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);  // layout xml 과 자바파일을 연결+

        Button nt3;
        EditText[] ed = new EditText[5];
        WebView webView1;

        nt3 = (Button) findViewById(R.id.join_button);

        ed[0] = (EditText) findViewById(R.id.reg_id);
        ed[1] = (EditText) findViewById(R.id.reg_pwd);
        ed[2] = (EditText) findViewById(R.id.reg_name);
        ed[3] = (EditText) findViewById(R.id.reg_birth);
        webView1 = (WebView) findViewById(R.id.web);

        nt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String[] st = new String[4];

                st[0] = ed[0].getText().toString();
                st[1] = ed[1].getText().toString();
                st[2] = ed[2].getText().toString();
                st[3] = ed[3].getText().toString();
                new Thread() {
                    public void run() {
                        ServerOperation.post(st,"http://192.168.0.84:92/index.php");
                    }

                }.start();
                finish();
            }
        });
    }

}
