package or.kr.wkbl;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.parse.Parse;
import com.parse.ParseInstallation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import or.kr.wkbl.Util.Log;
import or.kr.wkbl.Util.PreferenceUtil;

public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Context context = getApplicationContext();
        SharedPreferences settings = context.getSharedPreferences("WKBL", 0);

//        SharedPreferences.Editor editor2 = settings.edit();
//        editor2.remove("uuid");
//        editor2.commit();

        String uuid = settings.getString("uuid", "");

        if (uuid.equals("")) {
            uuid = UUID.randomUUID().toString();
            Log.d("MainApp", "uuid = " + uuid);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("uuid", uuid);
            editor.putString("push", "Y");
            editor.putString("push_live", "Y");
            editor.putString("push_movie", "Y");
            editor.commit();
        }

        String channel = "wkbl" + uuid;

        Parse.initialize(this, "dtqDlB68a9qeEp7ZK8D3c219IPBnVjpdthrykDlH", "TYZCWzxQNH2ntKPPlgmDSWoUVemixyKF7RREVO36");
        ParseInstallation pi = ParseInstallation.getCurrentInstallation();
        String iid = pi.getInstallationId();
        Log.d("MainApp", "parse installation ID=" + iid);
        pi.saveInBackground();
//
//        try {
//            ParsePush.subscribeInBackground(channel, new SaveCallback() {
//                @Override
//                public void done(ParseException e) {
//                    if (e == null) {
//                        Log.d("MainApp", "successfully subscribed to channel.");
//                    } else {
//                        Log.e("MainApp", "failed to subscribe for push " + e.getMessage());
//                    }
//                }
//            });
//        } catch (Exception e) {
//            Log.d("Push Exception", e.getMessage());
//        }

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        String token = task.getResult();
                        notificationRegister(token);
                        Log.d(token);
                    }
                });

    }

    private void notificationRegister(String token) {
        final Map param = new HashMap();
        PreferenceUtil.getInstance(getApplicationContext()).put(PreferenceUtil.PreferenceKey.FCMToken, token);
        param.put("token", token);
        Log.d("token = " + token);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(param);

            new HttpUtil().execute(token);

        } catch (Exception e) {
            Log.d(e.getMessage());
            e.printStackTrace();
        }
        Log.d("onCreate");
    }
}
