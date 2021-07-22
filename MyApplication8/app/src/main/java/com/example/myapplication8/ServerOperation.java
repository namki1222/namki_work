package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class ServerOperation {

    public static String post(String st[], String link) {
        String result = null;
        try {
            HttpClient httpClient = SessionControl.getHttpclient();
            InputStream is = null;
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("userID", st[0]));
            nameValuePairs.add(new BasicNameValuePair("userPassword", st[1]));
            nameValuePairs.add(new BasicNameValuePair("userName", st[2]));
            nameValuePairs.add(new BasicNameValuePair("userBirth", st[3]));


            HttpPost httpPost = new HttpPost(link);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            is = entity.getContent();
            //php에서 안드로이드로 가져오기
            BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder jsonHtml = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) { // response 출력
                jsonHtml.append(inputLine);
            }
            result = jsonHtml.toString();
            System.out.println(result);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }
        return result;
    }
    public static void login_session(String result,Context context,String link){
        String s = result.trim();
        System.out.println(s + "nsadnkalskndaks");
        if(s!=null) {
            SessionControl.cookies = SessionControl.httpclient.getCookieStore().getCookies();
            if (!SessionControl.cookies.isEmpty()) {
                Intent i = new Intent(context.getApplicationContext(), logoutActivity.class);
                i.putExtra("myurl", link);
                context.startActivity(i);
            }
        } else {
        Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }
}






