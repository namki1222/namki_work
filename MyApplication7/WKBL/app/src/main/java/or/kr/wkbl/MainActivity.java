package or.kr.wkbl;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.net.URISyntaxException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import or.kr.wkbl.Util.ClientType;
import or.kr.wkbl.Util.FragmentB;
import or.kr.wkbl.Util.FragmentC;
import or.kr.wkbl.Util.Log;
import or.kr.wkbl.Util.PreferenceUtil;
import or.kr.wkbl.Util.WKBLWebChromeClient;
import or.kr.wkbl.Util.WKBLWebViewClient;
import or.kr.wkbl.Util.WebAppInterface;

import static or.kr.wkbl.Activity.FlashActivity.EXTRA_URL;

public class MainActivity extends AppCompatActivity {

    private  String urlAddress = "http://115.68.54.33:8080/";
    private static final String TAG = "Main_Activity";

    private boolean isFragmentB = true ;
    private DrawerLayout mDrawerLayout;
    private ImageView iv[] = new ImageView[3];
    private TextView tv[] = new TextView[5];
    private LinearLayout lv[] = new LinearLayout[17];
    LinearLayout menulayout;

    @BindView(R.id.webView)
    WebView webView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initialize();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menulayout = (LinearLayout) findViewById(R.id.menu_layout_id);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout);
        FragmentC fragmentC = new FragmentC();
        FragmentB fragmentB = new FragmentB();





        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // ?????? title ?????????

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imageview0:
                        android.util.Log.d(TAG, "live: ?????????");
                        webView.loadUrl("https://m.sports.naver.com/basketball/schedule/index.nhn?category=wkbl");
                        // btn1 ??????
                        break;
                    case R.id.imageview1:
                        android.util.Log.d(TAG, "logo: ?????????");
                        webView.loadUrl(urlAddress);
                        // btn2 ??????
                        break;
                    case R.id.imageview2:
                        android.util.Log.d(TAG, "menu: ?????????");
                        mDrawerLayout.openDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview3:
                        android.util.Log.d(TAG, "register: ?????????");
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout,fragmentB);
                        transaction.commit();
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview4:
                        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                        android.util.Log.d(TAG, "login: ?????????");
                        transaction1.replace(R.id.frameLayout,fragmentC);
                        transaction1.commit();
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview5:
                        webView.loadUrl(urlAddress);
                        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                        android.util.Log.d(TAG, "home: ?????????");
                        transaction2.remove(fragmentC);
                        transaction2.remove(fragmentB);
                        transaction2.commit();
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview6:
                        android.util.Log.d(TAG, "media: ?????????");
                        webView.loadUrl(urlAddress+"teamstory/media_list.jsp");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview7:
                        android.util.Log.d(TAG, "game: ?????????");
                        webView.loadUrl(urlAddress+"game/game_schedule.jsp");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview8:
                        android.util.Log.d(TAG, "team: ?????????");
                        webView.loadUrl(urlAddress+"team/team_list.jsp");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview9:
                        android.util.Log.d(TAG, "player: ?????????");
                        webView.loadUrl(urlAddress+"team/player_list.jsp");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview10:
                        android.util.Log.d(TAG, "award: ?????????");
                        webView.loadUrl(urlAddress+"wkbl/award_league.jsp");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview11:
                        android.util.Log.d(TAG, "wkbl: ?????????");
                        webView.loadUrl(urlAddress+"wkbl/wkbl_greeting.jsp");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview12:
                        android.util.Log.d(TAG, "????????????: ?????????");
                        webView.loadUrl("https://www.facebook.com/f.WKBL");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview13:
                        android.util.Log.d(TAG, "?????????: ?????????");
                        webView.loadUrl("https://www.youtube.com/c/%EC%97%AC%EB%86%8D%ED%8B%B0%EB%B9%84/featured");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview14:
                        android.util.Log.d(TAG, "???????????????: ?????????");
                        webView.loadUrl("https://www.instagram.com/wkbl_official/");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview15:
                        android.util.Log.d(TAG, "????????? ??????: ?????????");
                        webView.loadUrl("https://tv.naver.com/wkbl");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.imageview16:
                        android.util.Log.d(TAG, "????????? ?????????: ?????????");
                        webView.loadUrl("https://sports.news.naver.com/basketball/news/index.nhn?isphoto=N");
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                }
            }

        };
        for(int i=0; i<16; i++) {
            int resID = getResources().getIdentifier("imageview" + i, "id",
                    "or.kr.wkbl");
            if(i == 0 || i == 2 || i ==1) {
                iv[i] = (ImageView) findViewById(resID);
                iv[i].setOnClickListener(onClickListener);
            }else if( i == 3|| i == 4){
                tv[i] = (TextView)findViewById(resID);
                tv[i].setOnClickListener(onClickListener);
            }else{
                lv[i] = (LinearLayout)findViewById(resID);
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
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case R.string.go_ticketlink_app:
                return new AlertDialog.Builder(this)
                        .setMessage(id)
                        .setPositiveButton(R.string.ok, (dialog, which) -> {
                            Log.d("BasicLayout", "onCreateDialog tiketlink launch");
                            //???????????? ?????? ??????
                            Intent intent = getPackageManager().getLaunchIntentForPackage("kr.co.ticketlink.sports");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        })
                        .setNegativeButton(R.string.cancel, (dialog, which) -> {
                        })
                        .create();
            case R.string.go_market:
                return new AlertDialog.Builder(this)
                        .setMessage(id)
                        .setPositiveButton(R.string.ok, (dialog, which) -> {
                            //?????? ??????
                            String url = "market://details?id=" + "kr.co.ticketlink.sports";
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(i);
                        })
                        .setNegativeButton(R.string.cancel, (dialog, which) -> {
                        })


                        .create();
            default:
                return null;
        }
    }

    private void initialize() {
        if (getIntent() != null) {
            urlAddress = getIntent().getStringExtra(EXTRA_URL);
            if (TextUtils.isEmpty(urlAddress))
                urlAddress = "http://115.68.54.33:8080/";
        }
        webViewSetting(webView);
        webView.setWebViewClient(new WKBLWebViewClient(MainActivity.this, webView));
        webView.setWebChromeClient(new WKBLWebChromeClient(MainActivity.this, webView));
        webView.addJavascriptInterface(new WebAppInterface(MainActivity.this), "Android");
//        webView.clearCache(true);
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(urlAddress);
    }

    private void webViewSetting(WebView webView){
        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportMultipleWindows(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setUserAgentString(webSettings.getUserAgentString() + " ticketlink-facility-inapp");

        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/cache");
        webSettings.setDatabaseEnabled(true);

        // http -> https ?????? ??????.
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        // ???????????? ?????? ??????.
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setAcceptThirdPartyCookies(webView, true);
    }
}