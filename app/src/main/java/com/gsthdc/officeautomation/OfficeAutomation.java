package com.gsthdc.officeautomation;

import android.app.Application;

import com.lidroid.xutils.HttpUtils;
import com.rey.material.app.ThemeManager;

/**
 * Created by wangzhicheng on 15/8/10.
 */
public class OfficeAutomation extends Application {

    private static HttpUtils mHttpUtils = new HttpUtils(Constants.network_timeout);

    @Override
    public void onCreate() {
        super.onCreate();
        mHttpUtils.configCurrentHttpCacheExpiry(10000);
        mHttpUtils.configDefaultHttpCacheExpiry(10000);
        ThemeManager.init(this, 2, 0, null);
    }

    public static HttpUtils getHttpUtils() {
        return mHttpUtils;
    }
}
