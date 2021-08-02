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
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
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
                        // btn1 동작
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
                        System.out.println("1번째");
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
                        System.out.println("2번째");
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
                        System.out.println("3번째");
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
                        System.out.println("4번째");
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
                        System.out.println("5번째");
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
                        System.out.println("6번째");
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
                        System.out.println("7번째");
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
//        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
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
        // 전화 걸기 처리
        if (handleTelShouldOverrideUrlLoading(url)) {
            return true;
        }

        // intent 처리
        if (handleIntentShouldOverrideUrlLoading(url)) {
            return true;
        }

        // Play Store url 처리
        if (storeShouldOverrideUrlLoading(url)) {
            return true;
        }

        // 기타 url 처리
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            return true;
        } catch (Exception e) {
            // 앱이 설치되어 있지 않을 경우 예외가 발생합니다. 이 부분은 업체에 맞게 구현해야 합니다.
            Toast.makeText(MainActivity.this, getString(R.string.install_application_message), Toast.LENGTH_LONG).show();
        }

        return false;
    }

    /**
     * 전화걸기 uri 처리 - ARS 인증을 위한 전화 연결 등
     * @param url     처리하고자 하는 url
     * @return        uri 처리 여부. WebViewClient.shouldOverrideUrlLoading가 반환할 값을 반환
     */
    private boolean handleTelShouldOverrideUrlLoading(String url) {
        if (url.toLowerCase().startsWith("tel:")) { //맨앞에 링크 에 tel:붙어있는지 확인
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);//webview에서 다른앱을 호출할때 intent사용
            return true;//붙어있으면 true
        }
        return false;
    }

    /**
     * intent 프로토콜로 시작하는 uri를 처리하는 메소드로 앱 설치 여부를 확인하고, 설치된 앱이 없을 경우
     * 플레이 스토어로 이동합니다.
     * @param url   처리할 uri
     * @return      url 처리 결과
     */
    private boolean handleIntentShouldOverrideUrlLoading(String url) {
        if (url.startsWith("intent")) {//맨앞링크 intent붙어있는지 확인
            try {
                Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME); //uri스키마 불러와서 intent시도

                if (intent.resolveActivity(getPackageManager()) != null) {//만약에 intent할 앱이 있을경우
                    // 처리할 수 있는 패키지(실행할 수 있는 앱)이 있을 경우
                    startActivity(intent);//실행
                    return true;
                }

                // 처리할 수 없는 패키지일 경우 플레이 스토어로 이동
                // 단 intent의 getPackage() 메소드로 패키지 정보가 가져올 수 있을 때에만 플레이 스토어로
                // 이동할 수 있습니다.
                Intent marketIntent = new Intent(Intent.ACTION_VIEW);//다른앱 호출
                marketIntent.setData(Uri.parse("market://details?id=" + intent.getPackage())); // 마켓에서 다운받으라고 데이터 세팅
                if (marketIntent.resolveActivity(getPackageManager()) != null) { //만약 앱이 있다면
                    startActivity(marketIntent);//실행
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
     * market 프로토콜로 시작하는 url의 처리. 플레이스토어 이동으로 사용하는 url을 처리합니다.
     * @param url   처리할 url
     * @return      url 처리 결과
     */
    private boolean storeShouldOverrideUrlLoading(String url) {
        if (url.startsWith("market")) { //url앞부분 market있으면
            Intent intent = new Intent(Intent.ACTION_VIEW);//새창띄우기
            intent.setData(Uri.parse(url));//market링크 새창
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //실행하는 액티비티를 새 테스크로 생성함

            //예> 앱에서 시스템 설정을 열 때,
            //
            //기존에 열어둔게 없으면 -> 새로 태스크 생성. (A1) -> 태스크[A1]
            //
            //기존에 열어둔게 있으면 (A1) -> 그 태스크로 들어감. (A2) -> 태스크[A1, A2]
            startActivity(intent);

            /*Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT

            앞으로 가져감

            : This flag is not normally set by application code, but set for you by the system as described in the launchMode documentation for the singleTask mode.

                    시스템 default Flag.

                    시스템에 의해 스택 관리되며 가장 기본 값임.

              Intent.FLAG_ACTIVITY_CLEAR_TOP

            상위 스택 제거

            : If set, and the activity being launched is already running in the current task, then instead of launching a new instance of that activity, all of the other activities on top of it will be closed and this Intent will be delivered to the (now on top) old activity as a new Intent.

                    실행하는 액티비티가 스택에 있으면 새로 시작하지 않고 상위 스택 모두 제거.

            예> [ABCDE]가 있고, E에서 C를 열면 상위 DE제거

            Intent.FLAG_ACTIVITY_SINGLE_TOP

            하나의 탑

            : If set, the activity will not be launched if it is already running at the top of the history stack.

                    액티비티가 이미 최상단에 있으면 다시 시작하지 않고 재사용.

                    예> [ABCDE]가 있고, E에서 E를 열면 onPause()->onNewIntent() -> onResume()

            (2) 나머지 Intent Flags


            Intent.FLAG_ACTIVITY_CLEAR_TASK // 실행 액티비티 외 모두 제거
            Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS // 최근 목록에서 제외.
            Intent.FLAG_ACTIVITY_FORWARD_RESULT // result를 상위로 보냄. startActivityForResult()에서 사용.

            Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP //  onActivityResult()로 결과를 전달할 때 이전 액티비티로 돌아가도록 하기 위해 추가.

            Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT // 멀티 윈도우 시 사용.



            Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY // 시스템에 의해 설정됨. 최근 목록에서 실행되면 자동으로 붙음.

            Intent.FLAG_ACTIVITY_MULTIPLE_TASK // FLAG_ACTIVITY_NEW_TASK와 함께 사용시 새 태스크 생성
            Intent.FLAG_ACTIVITY_NEW_DOCUMENT // concurrent document 동시 문서에서 사용
            Intent.FLAG_ACTIVITY_NO_ANIMATION // 액티비티 전환시 애니메이션 무시
            Intent.FLAG_ACTIVITY_NO_HISTORY // 액티비티를 스택에 존재X (로딩화면 등)



            Intent.FLAG_ACTIVITY_NO_USER_ACTION // onUserLeaveHint() 실행을 차단

            Intent.FLAG_ACTIVITY_REORDER_TO_FRONT // 실행하는 액티비티를 최상위로 올려줌
            Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED // 정리할 액티비티가 있으면 태스크를 리셋
            Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET // 리셋하면 상위 태스크 모두 삭제

            Intent.FLAG_ACTIVITY_TASK_ON_HOME // FLAG_ACTIVITY_NEW_TASK와 함께 사용. 현재 홈 태스크 위에 위치.



            Intent.FLAG_ACTIVITY_MATCH_EXTERNAL // 전체 앱이 intent를 처리할 수 없을 때

            Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS // 최근 목록에서 재실행할 수 있도록 유지.



            Intent.FLAG_DEBUG_LOG_RESOLUTION // 디버깅 정보를 로그로 출력.

            Intent.FLAG_INCLUDE_STOPPED_PACKAGES // stopped된 application도 target이 됨.

            Intent.FLAG_EXCLUDE_STOPPED_PACKAGES // stopped된 application은 target이 되지 않음.
            Intent.FLAG_FROM_BACKGROUND // 백그라운드 동작중을 알려줌.



            Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION // URI 지속 권한
            Intent.FLAG_GRANT_PREFIX_URI_PERMISSION // URI prefix match 권한.
            Intent.FLAG_GRANT_READ_URI_PERMISSION // URI 읽기 권한.
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION // URI 쓰기 권한.



            Intent.FLAG_RECEIVER_FOREGROUND // 포그라운드로  변경
            Intent.FLAG_RECEIVER_NO_ABORT // 리시버가 브로드캐스트를 중지 못함.
            Intent.FLAG_RECEIVER_REGISTERED_ONLY // 동적 리시버만 브로드캐스트 받음.
            Intent.FLAG_RECEIVER_REPLACE_PENDING // 중복된 브로드캐스트를 제거.
            Intent.FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS // 브로드캐스트가 instant app의 수신자에게 표시.
            */


            return true;
        }
        return false;
    }
}
