package or.kr.wkbl.Util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.util.List;

import or.kr.wkbl.Dialog.CommonDialog;
import or.kr.wkbl.R;

public class WebAppInterface {

    Context context;

    public WebAppInterface(Context context) {
        this.context = context;
    }

    /**
     * Show a toast from the web page
     */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void checkTL() {
        startTLApp();
    }

    @JavascriptInterface
    public String getToken() {
        return PreferenceUtil.getInstance(context).get(PreferenceUtil.PreferenceKey.FCMToken, "");
    }


    /**
     * 티켓링크 앱호출
     */
    public void startTLApp() {
        if (getPackageList()) {        //티켓링크 어플 존재시
            showDialog(context.getString(R.string.go_ticketlink_app));
        } else {                        //티켓링크 어플 미존재시
            showDialog(context.getString(R.string.go_market));
        }
    }

    /**
     * 폰에 설치된 패키지 리스트 검색
     *
     * @return 티켓링크 패키지 존재 유무
     */
    public boolean getPackageList() {
        boolean isExist = false;

        PackageManager pkgMgr = context.getPackageManager();
        List<ResolveInfo> mApps;
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = pkgMgr.queryIntentActivities(mainIntent, 0);

        try {
            for (int i = 0; i < mApps.size(); i++) {
                if (mApps.get(i).activityInfo.packageName.startsWith("kr.co.ticketlink.sports")) {
                    isExist = true;
                    break;
                }
            }
        } catch (Exception e) {
            isExist = false;
        }
        return isExist;
    }

    private void showDialog(String message) {
        CommonDialog commonDialog = new CommonDialog(context);
        commonDialog.setMessage(message);
        commonDialog.show();
    }
}
