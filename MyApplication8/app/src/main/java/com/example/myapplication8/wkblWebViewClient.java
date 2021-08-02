package com.example.myapplication8;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.URISyntaxException;

public class PaycoPaymentWebViewClient extends WebViewClient {
    private Context context;
    private WebView webView;

    public PaycoPaymentWebViewClient(Context context, WebView webView) {
        this.context = context;
        this.webView = webView;
    }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            ClientType clientType = ClientType.getClientType(url);

            // about:blank, javascript: 로 시작하는 url을 처리
            if (clientType.equals(ClientType.BLANK) || clientType.equals(ClientType.JAVASCRIPT)) {
                return true;
            }

            // http, https 프로토콜로 시작하는 일반적인 웹 주소를 처리
            if (clientType.equals(ClientType.WEB)) {
                return false;
            }
            // url에 대한 처리
            return handleAppUrl(url);
    }
    private boolean handleAppUrl(String url) {
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
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            return true;
        } catch (Exception e) {
            // 앱이 설치되어 있지 않을 경우 예외가 발생합니다. 이 부분은 업체에 맞게 구현해야 합니다.
            Toast.makeText(context, context.getString(R.string.install_application_message), Toast.LENGTH_LONG).show();
        }

        return false;
    }
    private boolean handleTelShouldOverrideUrlLoading(String url) {
        if (url.toLowerCase().startsWith("tel:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    /**
     * intent 프로토콜로 시작하는 uri를 처리하는 메소드로 앱 설치 여부를 확인하고, 설치된 앱이 없을 경우
     * 플레이 스토어로 이동합니다.
     *
     * @param url 처리할 uri
     * @return url 처리 결과
     */
    private boolean handleIntentShouldOverrideUrlLoading(String url) {
        if (url.startsWith("intent")) {
            try {
                Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                System.out.println(intent);

                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    // 처리할 수 있는 패키지(실행할 수 있는 앱)이 있을 경우
                    context.startActivity(intent);
                    return true;
                }

                // 처리할 수 없는 패키지일 경우 플레이 스토어로 이동
                // 단 intent의 getPackage() 메소드로 패키지 정보가 가져올 수 있을 때에만 플레이 스토어로
                // 이동할 수 있습니다.
                Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                marketIntent.setData(Uri.parse("market://details?id=" + intent.getPackage()));
                if (marketIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(marketIntent);
                    return true;
                }

            } catch (URISyntaxException uriEx) {
                System.out.println("URISyntaxException=[" + uriEx.getMessage() + "]");
                return false;
            }
        }
        return false;
    }

    /**
     * market 프로토콜로 시작하는 url의 처리. 플레이스토어 이동으로 사용하는 url을 처리합니다.
     *
     * @param url 처리할 url
     * @return url 처리 결과
     */
    private boolean storeShouldOverrideUrlLoading(String url) {
        if (url.startsWith("market")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

            return true;
        }
        return false;
    }
}
