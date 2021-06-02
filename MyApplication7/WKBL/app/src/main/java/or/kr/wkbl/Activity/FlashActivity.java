package or.kr.wkbl.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import or.kr.wkbl.MainActivity;
import or.kr.wkbl.R;
import or.kr.wkbl.Util.Log;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class FlashActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------------------
    // static final fields
    //----------------------------------------------------------------------------------------------

    public static final String EXTRA_URL = "EXTRA_URL";

    //----------------------------------------------------------------------------------------------
    // fields
    //----------------------------------------------------------------------------------------------

    private Subscription timer;

    //----------------------------------------------------------------------------------------------
    // constructor
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    // life cycle
    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);


        if (Intent.ACTION_VIEW.equals(getIntent().getAction())) {

            timer = Observable.interval(2, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aVoid -> {
                        Intent intent1 = new Intent(FlashActivity.this, MainActivity.class);
                        intent1.putExtra(EXTRA_URL, "http://115.68.54.33:8080/ticket/reserve.jsp");
                        startActivity(intent1);
                        finish();
                        timer.unsubscribe();
                    });
        } else {
            timer = Observable.interval(2, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aVoid -> {
                        Intent intent = new Intent(FlashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        timer.unsubscribe();
                    });
        }
    }


    //----------------------------------------------------------------------------------------------
    // override
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    // public
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    // protected
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    // private
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    // inner class
    //----------------------------------------------------------------------------------------------
}
