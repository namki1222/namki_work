package com.example.myapplication8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.webkit.CookieManager;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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
    private static HttpURLConnection con;

    public static String post(String st[], String link , Context context) {
        String result = null;
        StringBuilder jsonHtml = new StringBuilder();
        try {
            List<Pair> nameValuePairs = new ArrayList<Pair>();
            nameValuePairs.add(new Pair("userID", st[0]));
            nameValuePairs.add(new Pair("userPassword", st[1]));
            nameValuePairs.add(new Pair("userName", st[2]));
            nameValuePairs.add(new Pair("userBirth", st[3]));
            byte[] posteData = CreateQuery(nameValuePairs,"UTF-8");
            URL Url = new URL(link);
            con = (HttpURLConnection) Url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept","application/json");
            con.setDefaultUseCaches(false);
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            if(st[2]==null) {//로그인화면에서 로그인하는것일때
                setCookieHeader(context);
            }
            //사용자가 로그인해서 세션 쿠키를 서버로부터 발급받은적 있다면 그 다음 요청 헤더 부터는 그 세션 쿠키를 포함해서 전송해야 함.
            OutputStream os = con.getOutputStream();
            os.write(posteData);
            os.flush();
            os.close();

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                while (true) {
                    String line = br.readLine();
                    if (line == null)
                        break;
                    jsonHtml.append(line + "\n");
                }
                System.out.println(jsonHtml);
                br.close();
            }else{
                Log.d("LOG", "HTTP_OK를 받지 못했습니다.");
            }
            if(st[2]==null){//로그인화면에서 로그인하는것일때
                getCookieHeader(context);
                
            }

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
    public static byte[] CreateQuery(List<Pair> pairs, String charset ) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        try {
            for (Pair pair : pairs) {
                if (first) first = false;
                else result.append('&');
                result.append(URLEncoder.encode((String) pair.first, charset));
                result.append('=');
                result.append(URLEncoder.encode((String) pair.second, charset));
            }
        } catch( Exception e ) {
        }
        return result.toString().getBytes();
    }

    public static void get(String st) {
        StringBuilder jsonHtml = new StringBuilder();
        try {
            URL phpUrl = new URL("http://192.168.0.84:92/team_get.php?" + st);
            System.out.println(phpUrl);
            HttpURLConnection conn = (HttpURLConnection) phpUrl.openConnection();
            if (conn != null) {
                conn.setConnectTimeout(10000);
                conn.setUseCaches(false);
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    while (true) {
                        String line = br.readLine();
                        if (line == null)
                            break;
                        jsonHtml.append(line + "\n");
                    }
                    System.out.println(jsonHtml);
                    br.close();
                }
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getCookieHeader(Context context){//Set-Cookie에 배열로 돼있는 쿠키들을 스트링 한줄로 변환
        List<String> cookies = con.getHeaderFields().get("Set-Cookie");
        //cookies -> [JSESSIONID=D3F829CE262BC65853F851F6549C7F3E; Path=/smartudy; HttpOnly] -> []가 쿠키1개임.
        //Path -> 쿠키가 유효한 경로 ,/smartudy의 하위 경로에 위의 쿠키를 사용 가능.
        if (cookies != null) {
            for (String cookie : cookies) {
                String sessionid = cookie.split(";\\s*")[0];
                setSessionIdInSharedPref(sessionid,context);

            }
        }
    }
    private static void setSessionIdInSharedPref(String sessionid, Context context){
        SharedPreferences pref = context.getSharedPreferences("sessionCookie",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(pref.getString("sessionid",null) == null){ //처음 로그인하여 세션아이디를 받은 경우
            Log.d("LOG","처음 로그인하여 세션 아이디를 pref에 넣었습니다."+sessionid);
        }else if(!pref.getString("sessionid",null).equals(sessionid)){ //로그아웃하고 다시 세션아이디를 받은경우
            Log.d("LOG","기존의 세션 아이디"+pref.getString("sessionid",null)+"가 만료 되어서 "
                    +"서버의 세션 아이디 "+sessionid+" 로 교체 되었습니다.");
        }

        edit.putString("sessionid",sessionid);
        edit.apply(); //비동기 처리
    }
    private static void setCookieHeader(Context context){//공유프리퍼런스로부터 세션아이디 꺼내서 요청헤더에 cookie라는 항목으로 추가해서 전송
        SharedPreferences pref = context.getSharedPreferences("sessionCookie",Context.MODE_PRIVATE);
        String sessionid = pref.getString("sessionid",null);
        if(sessionid!=null) {
            Log.d("LOG","세션 아이디"+sessionid+"가 요청 헤더에 포함 되었습니다.");
            con.setRequestProperty("Cookie", sessionid);
        }
    }

}






