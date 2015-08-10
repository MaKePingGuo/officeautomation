package com.gsthdc.officeautomation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SPUtils {
    private static final String ACCOUNT = "Account";
    private static final String PASSWORD = "Password";
    private static final String AUTO_LOGIN = "AutoLogin";

    public static String getAccount(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constants.TAG, Context.MODE_PRIVATE);
        return sp.getString(ACCOUNT, "").trim();
    }

    public static boolean setAccount(Context context, String account) {
        SharedPreferences sp = context.getSharedPreferences(Constants.TAG, Context.MODE_PRIVATE);
        Editor et = sp.edit();
        et.putString(ACCOUNT, account.trim());
        return et.commit();
    }

    public static String getPassowrd(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constants.TAG, Context.MODE_PRIVATE);
        return sp.getString(PASSWORD, "").trim();
    }

    public static boolean setPassword(Context context, String password) {
        SharedPreferences sp = context.getSharedPreferences(Constants.TAG, Context.MODE_PRIVATE);
        Editor et = sp.edit();
        et.putString(PASSWORD, password.trim());
        return et.commit();
    }

    public static boolean getAutoLogin(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constants.TAG, Context.MODE_PRIVATE);
        return sp.getBoolean(AUTO_LOGIN, false);
    }

    public static boolean setAutoLogin(Context context, boolean autoLogin) {
        SharedPreferences sp = context.getSharedPreferences(Constants.TAG, Context.MODE_PRIVATE);
        Editor et = sp.edit();
        et.putBoolean(AUTO_LOGIN, autoLogin);
        return et.commit();
    }
}
