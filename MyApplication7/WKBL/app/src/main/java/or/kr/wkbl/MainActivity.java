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
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import java.net.URISyntaxException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import or.kr.wkbl.Util.ClientType;
import or.kr.wkbl.Util.Log;
import or.kr.wkbl.Util.PreferenceUtil;
import or.kr.wkbl.Util.WKBLWebChromeClient;
import or.kr.wkbl.Util.WKBLWebViewClient;
import or.kr.wkbl.Util.WebAppInterface;

import static or.kr.wkbl.Activity.FlashActivity.EXTRA_URL;

public class MainActivity extends AppCompatActivity {

    private  String urlAddress = "http://115.68.54.33:8080/index.jsp";
    private static final String TAG = "Main_Activity";

    private DrawerLayout mDrawerLayout;
    private ImageView ivMenu,ivMenu2,ivMenu3;

    @BindView(R.id.webView)
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initialize();
        ivMenu=findViewById(R.id.menu);
        ivMenu2=findViewById(R.id.live);
        ivMenu3=findViewById(R.id.logo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);



        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기


        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.util.Log.d(TAG, "onClick: 클릭됨");
                mDrawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        ivMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.util.Log.d(TAG, "onClick: 클릭됨");
                webView.loadUrl("https://m.sports.naver.com/basketball/schedule/index.nhn?category=wkbl");
            }
        });
        ivMenu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.util.Log.d(TAG, "onClick: 클릭됨");
                initialize();
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
                            //티켓링크 어플 호출
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
                            //마켓 연동
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
                urlAddress = "http://115.68.54.33:8080/index.jsp";
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

        // http -> https 호출 허용.
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        // 서드파티 쿠키 허용.
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setAcceptThirdPartyCookies(webView, true);
    }
}