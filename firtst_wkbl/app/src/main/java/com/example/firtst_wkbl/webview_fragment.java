package com.example.myapplication8;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.URISyntaxException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link webview_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class webview_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public webview_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment webview_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static webview_fragment newInstance(String param1, String param2) {
        webview_fragment fragment = new webview_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webview, container, false);
        WebView webView = view.findViewById(R.id.webview);
        setupWebView(webView);
        webView.loadUrl("http://192.168.0.196/news/notice_list.php");
        return view;
    }
    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView(WebView webView) { //웹뷰 설정
        webView.clearCache(true);//캐시삭제
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//webview스크롤 항상 보이게 하기
        webView.getSettings().setJavaScriptEnabled(true); //자바 스크립트 허용
        webView.getSettings().setUserAgentString(webView.getSettings().getUserAgentString() + " ticketlink-facility-inapp");
        webView.getSettings().setDomStorageEnabled(true);//로컬스토리지 사용
        webView.getSettings().setLoadWithOverviewMode(true);//컨텐츠가 웹뷰보다 클때 스크린 크기 맞추기
        webView.getSettings().setUseWideViewPort(true);//뷰사이즈 맞추기
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); //자바스크립트가 창을 자동으로 열 수 있게할지 여부
        webView.getSettings().setBuiltInZoomControls(true);//줌아이콘
        webView.getSettings().setSupportZoom(true);//확대 축소기능

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //빌드버전이 롤리팝 이상이면
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);// http -> https 호출 허용.

            CookieManager cookieManager = CookieManager.getInstance(); // 서드파티 쿠키 허용.
            cookieManager.setAcceptCookie(true);
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
        webView.setWebViewClient(new PaycoPaymentWebViewClient(webView.getContext(), webView)); //url가로채기 하려고 사용하는듯
    }

}