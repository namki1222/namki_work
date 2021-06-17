package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";

    private DrawerLayout mDrawerLayout;
    private ImageView iv[] = new ImageView[3];
    private TextView tv[] = new TextView[5];
    private LinearLayout lv[] = new LinearLayout[17];

    LinearLayout menulayout;
    private boolean isFragmentB = true ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menulayout = (LinearLayout) findViewById(R.id.menu_layout_id);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);



        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imageview0:
                        Log.d(TAG, "live: 클릭됨");
                        // btn1 동작
                        break;
                    case R.id.imageview1:
                        Log.d(TAG, "logo: 클릭됨");
                        // btn2 동작
                        break;
                    case R.id.imageview2:
                        Log.d(TAG, "menu: 클릭됨");
                        mDrawerLayout.openDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview3:
                        Log.d(TAG, "register: 클릭됨");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        FragmentB fragmentB = new FragmentB();
                        transaction.replace(R.id.frameLayout,fragmentB);
                        transaction.commit();
                        break;
                    case R.id.imageview4:
                        Log.d(TAG, "login: 클릭됨");
                        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                        FragmentC fragmentC = new FragmentC();
                        transaction1.replace(R.id.frameLayout,fragmentC);
                        transaction1.commit();
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview5:
                        Log.d(TAG, "home: 클릭됨");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview6:
                        Log.d(TAG, "media: 클릭됨");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview7:
                        Log.d(TAG, "game: 클릭됨");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview8:
                        Log.d(TAG, "team: 클릭됨");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview9:
                        Log.d(TAG, "player: 클릭됨");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview10:
                        Log.d(TAG, "award: 클릭됨");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview11:
                        Log.d(TAG, "wkbl: 클릭됨");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview12:
                        Log.d(TAG, "페이스북: 클릭됨");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview13:
                        Log.d(TAG, "유튜브: 클릭됨");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview14:
                        Log.d(TAG, "인스타그램: 클릭됨");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview15:
                        Log.d(TAG, "네이버 티비: 클릭됨");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview16:
                        Log.d(TAG, "네이버 스포츠: 클릭됨");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                }
            }

        };
        for (int i = 0; i < 16; i++) {
            int resID = getResources().getIdentifier("imageview" + i, "id",
                    "com.example.myapplication");
            if (i == 0 || i == 2 || i == 1) {
                iv[i] = (ImageView) findViewById(resID);
                iv[i].setOnClickListener(onClickListener);
            } else if (i == 3 || i == 4){
                tv[i] = (TextView) findViewById(resID);
                tv[i].setOnClickListener(onClickListener);
            } else {
                lv[i] = (LinearLayout) findViewById(resID);
                lv[i].setOnClickListener(onClickListener);
            }
        }
        menulayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }
}

