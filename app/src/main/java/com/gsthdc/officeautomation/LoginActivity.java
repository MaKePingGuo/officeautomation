package com.gsthdc.officeautomation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.dd.processbutton.iml.ActionProcessButton;
import com.gsthdc.officeautomation.event.LoginCancel;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nispok.snackbar.SnackbarManager;
import com.rey.material.widget.EditText;

import de.greenrobot.event.EventBus;

/**
 * Created by wangzhicheng on 15/8/10.
 */
public class LoginActivity extends Activity {

    private EditText mAccount;
    private EditText mPassword;
    private ActionProcessButton mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);

        initUI();
    }

    private void initUI() {
        mAccount = (EditText) findViewById(R.id.account);
        mAccount.requestFocus();
        mAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                mLogin.setEnabled(checkLoginButtonEnabled());
            }
        });
        mPassword = (EditText) findViewById(R.id.password);
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                mLogin.setEnabled(checkLoginButtonEnabled());
            }
        });
        mLogin = (ActionProcessButton) findViewById(R.id.login);
//        mLogin.setEnabled(false);
        mLogin.setMode(ActionProcessButton.Mode.ENDLESS);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private boolean checkLoginButtonEnabled() {
        if (TextUtils.isEmpty(mAccount.getText().toString().trim())) {
            return false;
        }
        if (TextUtils.isEmpty(mPassword.getText().toString().trim())) {
            return false;
        }
        return true;
    }

    private void setPageEnabled(boolean enabled) {
        mAccount.setEnabled(enabled);
        mPassword.setEnabled(enabled);
        mLogin.setEnabled(enabled);
    }

    private void login() {
        if (TextUtils.isEmpty(mAccount.getText().toString().trim())) {
            ToastUtils.showSnackToast(LoginActivity.this, "用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(mPassword.getText().toString().trim())) {
            ToastUtils.showSnackToast(LoginActivity.this, "密码不能为空");
            return;
        }

        mLogin.setProgress(10);
        setPageEnabled(false);
        SnackbarManager.dismiss();
        OfficeAutomation.getHttpUtils().send(
                HttpRequest.HttpMethod.GET, Constants.temp_url, new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.e(Constants.TAG, arg0.getMessage());
                        mLogin.setProgress(0);
                        setPageEnabled(true);
                        ToastUtils.showSnackToast(LoginActivity.this, "网络错误");
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Log.d(Constants.TAG, arg0.result);
                        LoginActivity.this.finish();
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            EventBus.getDefault().post(new LoginCancel());
        }
        return super.onKeyDown(keyCode, event);
    }
}
