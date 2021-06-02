package or.kr.wkbl;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import or.kr.wkbl.Util.Log;

public class HttpUtil extends AsyncTask<String, Void, Void> {
    @Override
    public Void doInBackground(String... params) {
        try {
            String url = "http://115.68.54.33:8080/fcm/set_token.jsp?token=" + params[0] +"&device=android";
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            Log.d("params = " + params[0]);
            OutputStream os = conn.getOutputStream();
            os.write(params[0].getBytes("UTF-8"));
            os.close();

            int retCode = conn.getResponseCode();
            Log.d("ret = " + retCode);

            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = br.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            br.close();

            String res = response.toString();

            Log.d(res);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}