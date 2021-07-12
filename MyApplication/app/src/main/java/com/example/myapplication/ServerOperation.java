package com.example.post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import cz.msebera.android.httpclient.client.ClientProtocolException;

public class ServerOperation {
    private static final String TAG = "phptest";
    private static final String TAG_JSON = "webnautes";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADDRESS = "address";
    String mJsonString;

    public static void post(String st[], String link) {
        try {
            URL url = new URL(link); // 호출할 url
            Map<String, Object> params = new LinkedHashMap<>(); // 파라미터 세팅
            params.put("userID", st[0]);
            params.put("userPassword", st[1]);
            params.put("userName", st[2]);
            params.put("userBirth", st[3]);
            System.out.println(params);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes); // POST 호출

            //php에서 안드로이드로 가져오기
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder jsonHtml = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) { // response 출력
                jsonHtml.append(inputLine);
                System.out.println(inputLine);
            }
            in.close();



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }
    }

    //    public final static Cookie getcookie(){
//        List<Cookie> cookies = ((DefaultHttpClient) httpClient).getCookieStore().getCookies();
//
//        return cookies.get(0);
//    }
//    public static void updateCookie() {
/*        CookieStore cookieStore = ((DefaultHttpClient) httpClient).getCookieStore();
        List<Cookie> cookieList = cookieStore.getCookies();

        SharedPreferences pref = new SharedPreferences(context,"cookie");

        if(cookieList.size() == 0 && !napsCookie.isEmpty())
        {
            String name = namki.getValue("name");
            String value = namki.getValue("value");
            String domain = namki.getValue("domain");
            String path = namki.getValue("path");

            BasicClientCookie cookie = new BasicClientCookie(name,value);

            cookie.setDomain(domain);
            cookie.setPath(path);
            cookieStore.addCookie(cookie);*/
    }

//    private void setSessionIdInSharedPref(String sessionid) {
//        SharedPreferences pref = context.getSharedPreferences("sessionCookie", Context.MODE_PRIVATE);
//        SharedPreferences.Editor edit = pref.edit();
//        if (pref.getString("sessionid", null) == null) { //처음 로그인하여 세션아이디를 받은 경우
//            Log.d("LOG", "처음 로그인하여 세션 아이디를 pref에 넣었습니다." + sessionid);
//        } else if (!pref.getString("sessionid", null).equals(sessionid)) { //서버의 세션 아이디 만료 후 갱신된 아이디가 수신된경우
//            Log.d("LOG", "기존의 세션 아이디" + pref.getString("sessionid", null) + "가 만료 되어서 "
//                    + "서버의 세션 아이디 " + sessionid + " 로 교체 되었습니다.");
//        }
//        edit.putString("sessionid", sessionid);
//        edit.apply(); //비동기 처리
//    }






