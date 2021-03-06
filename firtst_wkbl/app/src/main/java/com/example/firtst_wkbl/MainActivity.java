package com.example.myapplication8;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";

    private DrawerLayout mDrawerLayout;
    private TextView tv[] = new TextView[2];
    private ImageView icon[] = new ImageView[7];
    private ImageView icon_first;
    private LinearLayout menulayout;
    private Fragment media_fragment, game_fragment, ticket_fragment, stats_fragment, login_fragment, register_fragment,webview_fragment;
    private TextView logotext;
    private View ll ,loginpage;
    private int pointY,on_off=0;
    private String post_string;
    private EditText login[] = new EditText[2];
    private Button nt1,nt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initNavigation();
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headView = navigationView.getHeaderView(0);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
        navigationView.setNavigationItemSelectedListener(click);
        RelativeLayout team_select = (RelativeLayout)findViewById(R.id.team_select);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menulayout = (LinearLayout) findViewById(R.id.menu_layout_id);
        logotext = (TextView) findViewById(R.id.imageview1);
        tv[0] = headView.findViewById(R.id.textview0);
        tv[1] = headView.findViewById(R.id.textview1);

        loginpage = (View)inflater.inflate(R.layout.login,null);
        nt1 = loginpage.findViewById(R.id.button);
        nt2 = loginpage.findViewById(R.id.register_button);
        login[0] = (EditText) findViewById(R.id.id);
        login[1] = (EditText) findViewById(R.id.pwd);


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // ?????? title ?????????
        ll = (View)inflater.inflate(R.layout.team_select,null);
        icon_first = (ImageView)findViewById(R.id.imageview0);


        media_fragment = new media_fragment();
        game_fragment = new game_fragment();
        ticket_fragment = new ticket_fragment();
        Bundle bundle = new Bundle();
        stats_fragment = new stats_fragment();
        login_fragment = new login_fragment();
        register_fragment = new register_fragment();
        webview_fragment = new webview_fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, media_fragment).commit();
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        icon_first.setImageResource(R.drawable.logo_sm);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.textview0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, login_fragment).commit();
                        logotext.setText("login");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        // btn1 ??????
                        break;
                    case R.id.textview1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, register_fragment).commit();
                        logotext.setText("register");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.team_select:
                        if(on_off!=0){
                            ((ViewManager)ll.getParent()).removeView(ll);
                            on_off=0;
                        }else{
                            LinearLayout.LayoutParams paramll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                            paramll.setMargins(0,pointY,0,0);
                            addContentView(ll, paramll);
                            on_off++;
                        }
                        break;
                    case R.id.logo0:
                        System.out.println("1??????");
                        icon_first.setImageResource(R.drawable.logo_sm);
                        post_string = "userID=100";
                        new Thread() {
                            public void run() {
                                ServerOperation.get(post_string);
                            }

                        }.start();
                        ((ViewManager)ll.getParent()).removeView(ll);
                        on_off=0;
                        break;
                    case R.id.logo1:
                        System.out.println("2??????");
                        icon_first.setImageResource(R.drawable.logo03);
                        post_string = "userID=200";
                        new Thread() {
                            public void run() {
                                ServerOperation.get(post_string);
                            }

                        }.start();
                        ((ViewManager)ll.getParent()).removeView(ll);
                        on_off=0;
                        break;
                    case R.id.logo2:
                        System.out.println("3??????");
                        icon_first.setImageResource(R.drawable.logo07);
                        post_string = "userID=300";
                        new Thread() {
                            public void run() {
                                ServerOperation.get(post_string);
                            }

                        }.start();
                        ((ViewManager)ll.getParent()).removeView(ll);
                        on_off=0;
                        break;
                    case R.id.logo3:
                        System.out.println("4??????");
                        icon_first.setImageResource(R.drawable.logo05);
                        post_string = "userID=400";
                        new Thread() {
                            public void run() {
                                ServerOperation.get(post_string);
                            }

                        }.start();
                        ((ViewManager)ll.getParent()).removeView(ll);
                        on_off=0;
                        break;
                    case R.id.logo4:
                        System.out.println("5??????");
                        icon_first.setImageResource(R.drawable.logo09);
                        post_string = "userID=500";
                        new Thread() {
                            public void run() {
                                ServerOperation.get(post_string);
                            }

                        }.start();
                        ((ViewManager)ll.getParent()).removeView(ll);
                        on_off=0;
                        break;
                    case R.id.logo5:
                        System.out.println("6??????");
                        icon_first.setImageResource(R.drawable.logo11);
                        post_string = "userID=600";
                        new Thread() {
                            public void run() {
                                ServerOperation.get(post_string);
                            }

                        }.start();
                        ((ViewManager)ll.getParent()).removeView(ll);
                        on_off=0;
                        break;
                    case R.id.logo6:
                        System.out.println("7??????");
                        icon_first.setImageResource(R.drawable.logo01);
                        post_string = "userID=700";
                        new Thread() {
                            public void run() {
                                ServerOperation.get(post_string);
                            }

                        }.start();
                        ((ViewManager)ll.getParent()).removeView(ll);
                        on_off=0;
                        break;
                }
            }
        };
        for (int i = 0; i < 2; i++) {
            tv[i].setOnClickListener(onClickListener);
        }
        team_select.setOnClickListener(onClickListener);

        for(int i=0; i<7; i++){
            int resID = getResources().getIdentifier("logo" + i, "id",
                    "com.example.myapplication8");

            icon[i] = ll.findViewById(resID);
            icon[i].setOnClickListener(onClickListener);
        }
    }

    private void initNavigation() {
        BottomNavigationView navController = (BottomNavigationView)findViewById(R.id.bottomNavi);
        navController.setItemIconTintList(null);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        pointY = toolbar.getHeight();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_MEDIA:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, media_fragment).commit();
                        logotext.setText("MEDIA");
                        return true;
                    case R.id.action_GAME:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, game_fragment).commit();
                        logotext.setText("GAME");
                        return true;
                    case R.id.action_TICKET:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, ticket_fragment).commit();
                        logotext.setText("TICKET");
                        return true;
                    case R.id.action_STATS:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, stats_fragment).commit();
                        logotext.setText("STATS");
                        return true;
                    case R.id.action_MENU:
                        mDrawerLayout.openDrawer(Gravity.RIGHT);
                        if(on_off!=0) {
                            ((ViewManager) ll.getParent()).removeView(ll);
                            on_off = 0;
                        }
                        return true;
                }
                return false;
            }
        };
//    public void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();      // Fragment??? ????????? MainActivity?????? layout????????? ???????????????.
//    }
    private NavigationView.OnNavigationItemSelectedListener click = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.imageview2:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, webview_fragment).commit();
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                    return true;
            }
            return false;
        }
    };

    public boolean handleAppUrl(String url) {
        // ?????? ?????? ??????
        if (handleTelShouldOverrideUrlLoading(url)) {
            return true;
        }

        // intent ??????
        if (handleIntentShouldOverrideUrlLoading(url)) {
            return true;
        }

        // Play Store url ??????
        if (storeShouldOverrideUrlLoading(url)) {
            return true;
        }

        // ?????? url ??????
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            return true;
        } catch (Exception e) {
            // ?????? ???????????? ?????? ?????? ?????? ????????? ???????????????. ??? ????????? ????????? ?????? ???????????? ?????????.
            Toast.makeText(MainActivity.this, getString(R.string.install_application_message), Toast.LENGTH_LONG).show();
        }

        return false;
    }

    /**
     * ???????????? uri ?????? - ARS ????????? ?????? ?????? ?????? ???
     * @param url     ??????????????? ?????? url
     * @return        uri ?????? ??????. WebViewClient.shouldOverrideUrlLoading??? ????????? ?????? ??????
     */
    private boolean handleTelShouldOverrideUrlLoading(String url) {
        if (url.toLowerCase().startsWith("tel:")) { //????????? ?????? ??? tel:??????????????? ??????
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);//webview?????? ???????????? ???????????? intent??????
            return true;//??????????????? true
        }
        return false;
    }

    /**
     * intent ??????????????? ???????????? uri??? ???????????? ???????????? ??? ?????? ????????? ????????????, ????????? ?????? ?????? ??????
     * ????????? ???????????? ???????????????.
     * @param url   ????????? uri
     * @return      url ?????? ??????
     */
    private boolean handleIntentShouldOverrideUrlLoading(String url) {
        if (url.startsWith("intent")) {//???????????? intent??????????????? ??????
            try {
                Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME); //uri????????? ???????????? intent??????

                if (intent.resolveActivity(getPackageManager()) != null) {//????????? intent??? ?????? ????????????
                    // ????????? ??? ?????? ?????????(????????? ??? ?????? ???)??? ?????? ??????
                    startActivity(intent);//??????
                    return true;
                }

                // ????????? ??? ?????? ???????????? ?????? ????????? ???????????? ??????
                // ??? intent??? getPackage() ???????????? ????????? ????????? ????????? ??? ?????? ????????? ????????? ????????????
                // ????????? ??? ????????????.
                Intent marketIntent = new Intent(Intent.ACTION_VIEW);//????????? ??????
                marketIntent.setData(Uri.parse("market://details?id=" + intent.getPackage())); // ???????????? ?????????????????? ????????? ??????
                if (marketIntent.resolveActivity(getPackageManager()) != null) { //?????? ?????? ?????????
                    startActivity(marketIntent);//??????
                    return true;
                }

            } catch (URISyntaxException uriEx) {
                Log.e(TAG, "URISyntaxException=[" + uriEx.getMessage() + "]");
                return false;
            }
        }

        return false;
    }

    /**
     * market ??????????????? ???????????? url??? ??????. ?????????????????? ???????????? ???????????? url??? ???????????????.
     * @param url   ????????? url
     * @return      url ?????? ??????
     */
    private boolean storeShouldOverrideUrlLoading(String url) {
        if (url.startsWith("market")) { //url????????? market?????????
            Intent intent = new Intent(Intent.ACTION_VIEW);//???????????????
            intent.setData(Uri.parse(url));//market?????? ??????
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //???????????? ??????????????? ??? ???????????? ?????????

            //???> ????????? ????????? ????????? ??? ???,
            //
            //????????? ???????????? ????????? -> ?????? ????????? ??????. (A1) -> ?????????[A1]
            //
            //????????? ???????????? ????????? (A1) -> ??? ???????????? ?????????. (A2) -> ?????????[A1, A2]
            startActivity(intent);

            /*Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT

            ????????? ?????????

            : This flag is not normally set by application code, but set for you by the system as described in the launchMode documentation for the singleTask mode.

                    ????????? default Flag.

                    ???????????? ?????? ?????? ???????????? ?????? ?????? ??????.

              Intent.FLAG_ACTIVITY_CLEAR_TOP

            ?????? ?????? ??????

            : If set, and the activity being launched is already running in the current task, then instead of launching a new instance of that activity, all of the other activities on top of it will be closed and this Intent will be delivered to the (now on top) old activity as a new Intent.

                    ???????????? ??????????????? ????????? ????????? ?????? ???????????? ?????? ?????? ?????? ?????? ??????.

            ???> [ABCDE]??? ??????, E?????? C??? ?????? ?????? DE??????

            Intent.FLAG_ACTIVITY_SINGLE_TOP

            ????????? ???

            : If set, the activity will not be launched if it is already running at the top of the history stack.

                    ??????????????? ?????? ???????????? ????????? ?????? ???????????? ?????? ?????????.

                    ???> [ABCDE]??? ??????, E?????? E??? ?????? onPause()->onNewIntent() -> onResume()

            (2) ????????? Intent Flags


            Intent.FLAG_ACTIVITY_CLEAR_TASK // ?????? ???????????? ??? ?????? ??????
            Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS // ?????? ???????????? ??????.
            Intent.FLAG_ACTIVITY_FORWARD_RESULT // result??? ????????? ??????. startActivityForResult()?????? ??????.

            Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP //  onActivityResult()??? ????????? ????????? ??? ?????? ??????????????? ??????????????? ?????? ?????? ??????.

            Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT // ?????? ????????? ??? ??????.



            Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY // ???????????? ?????? ?????????. ?????? ???????????? ???????????? ???????????? ??????.

            Intent.FLAG_ACTIVITY_MULTIPLE_TASK // FLAG_ACTIVITY_NEW_TASK??? ?????? ????????? ??? ????????? ??????
            Intent.FLAG_ACTIVITY_NEW_DOCUMENT // concurrent document ?????? ???????????? ??????
            Intent.FLAG_ACTIVITY_NO_ANIMATION // ???????????? ????????? ??????????????? ??????
            Intent.FLAG_ACTIVITY_NO_HISTORY // ??????????????? ????????? ??????X (???????????? ???)



            Intent.FLAG_ACTIVITY_NO_USER_ACTION // onUserLeaveHint() ????????? ??????

            Intent.FLAG_ACTIVITY_REORDER_TO_FRONT // ???????????? ??????????????? ???????????? ?????????
            Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED // ????????? ??????????????? ????????? ???????????? ??????
            Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET // ???????????? ?????? ????????? ?????? ??????

            Intent.FLAG_ACTIVITY_TASK_ON_HOME // FLAG_ACTIVITY_NEW_TASK??? ?????? ??????. ?????? ??? ????????? ?????? ??????.



            Intent.FLAG_ACTIVITY_MATCH_EXTERNAL // ?????? ?????? intent??? ????????? ??? ?????? ???

            Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS // ?????? ???????????? ???????????? ??? ????????? ??????.



            Intent.FLAG_DEBUG_LOG_RESOLUTION // ????????? ????????? ????????? ??????.

            Intent.FLAG_INCLUDE_STOPPED_PACKAGES // stopped??? application??? target??? ???.

            Intent.FLAG_EXCLUDE_STOPPED_PACKAGES // stopped??? application??? target??? ?????? ??????.
            Intent.FLAG_FROM_BACKGROUND // ??????????????? ???????????? ?????????.



            Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION // URI ?????? ??????
            Intent.FLAG_GRANT_PREFIX_URI_PERMISSION // URI prefix match ??????.
            Intent.FLAG_GRANT_READ_URI_PERMISSION // URI ?????? ??????.
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION // URI ?????? ??????.



            Intent.FLAG_RECEIVER_FOREGROUND // ??????????????????  ??????
            Intent.FLAG_RECEIVER_NO_ABORT // ???????????? ????????????????????? ?????? ??????.
            Intent.FLAG_RECEIVER_REGISTERED_ONLY // ?????? ???????????? ?????????????????? ??????.
            Intent.FLAG_RECEIVER_REPLACE_PENDING // ????????? ????????????????????? ??????.
            Intent.FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS // ????????????????????? instant app??? ??????????????? ??????.
            */


            return true;
        }
        return false;
    }
}
