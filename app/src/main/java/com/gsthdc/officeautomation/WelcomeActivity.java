package com.gsthdc.officeautomation;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by wangzhicheng on 15/8/10.
 */
public class WelcomeActivity extends Activity {


    public class WelcomeTimeout {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        EventBus.getDefault().register(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3 * 1000);
                } catch (Exception e) {

                }
                EventBus.getDefault().post(new WelcomeTimeout());
            }
        }).start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void onEventMainThread(WelcomeTimeout event) {
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}
