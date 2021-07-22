package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";

    private DrawerLayout mDrawerLayout;
    private TextView tv[] = new TextView[2];
    private LinearLayout lv[] = new LinearLayout[17];

    LinearLayout menulayout;

    private Fragment media_fragment, game_fragment, ticket_fragment, stats_fragment, login_fragment, register_fragment;
    WebView webview;
    BottomNavigationView bottomNavigationView;
    TextView logotext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        webview = (WebView) findViewById(R.id.webview);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headView = navigationView.getHeaderView(0);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menulayout = (LinearLayout) findViewById(R.id.menu_layout_id);
        logotext = (TextView) findViewById(R.id.imageview1);
        tv[0] = headView.findViewById(R.id.textview0);
        tv[1] = headView.findViewById(R.id.textview1);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기


        media_fragment = new media_fragment();
        game_fragment = new game_fragment();
        ticket_fragment = new ticket_fragment();
        stats_fragment = new stats_fragment();
        login_fragment = new login_fragment();
        register_fragment = new register_fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, media_fragment).commit();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.textview0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, login_fragment).commit();
                        logotext.setText("login");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        // btn1 동작
                        break;
                    case R.id.textview1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, register_fragment).commit();
                        logotext.setText("register");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;

                }
            }
        };
        for (int i = 0; i < 2; i++) {
            tv[i].setOnClickListener(onClickListener);
        }
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
                        return true;
                }
                return false;
            }
        };
    }