package com.gsthdc.officeautomation;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.enums.SnackbarType;
import com.nispok.snackbar.listeners.ActionClickListener;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.Toast;

public class ToastUtils {

    private static Toast mToast = null;

    public static void show(Context context, int content) {
        if (mToast == null) {
            mToast = Toast.makeText(context, context.getString(content), Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }
        mToast.show();
    }

    public static void show(Context context, String content) {
        if (mToast == null) {
            mToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }
        mToast.show();
    }

    public static void showSnackToast(Activity activity, int stringId) {
        SnackbarManager.show(
                Snackbar.with(activity)
                        .type(SnackbarType.MULTI_LINE)
                        .duration(Snackbar.SnackbarDuration.LENGTH_SHORT)
                        .textColor(Color.WHITE)
                        .backgroundDrawable(R.color.blue_light_text)
                        .text(stringId));
    }

    public static void showSnackToast(Activity activity, String string) {
        SnackbarManager.show(
                Snackbar.with(activity)
                        .type(SnackbarType.MULTI_LINE)
                        .duration(Snackbar.SnackbarDuration.LENGTH_SHORT)
                        .textColor(Color.WHITE)
                        .backgroundDrawable(R.color.blue_light_text)
                        .text(string));
    }

    public static void showDeleteConfirm(Activity activity, ActionClickListener listener) {
        SnackbarManager.show(
                Snackbar.with(activity)
                        .type(SnackbarType.MULTI_LINE)
                        .duration(Snackbar.SnackbarDuration.LENGTH_SHORT)
                        .textColor(Color.WHITE)
                        .backgroundDrawable(R.color.blue_light_text)
                        .text("确认删除？")
                        .actionLabel("删除")
                        .actionListener(listener));
    }

    public static void showInsideDeleteConfirm(Activity activity,
            ActionClickListener listener, ViewGroup parent) {
        SnackbarManager.show(
                Snackbar.with(activity)
                        .type(SnackbarType.MULTI_LINE)
                        .duration(Snackbar.SnackbarDuration.LENGTH_SHORT)
                        .textColor(Color.WHITE)
                        .backgroundDrawable(R.color.blue_light_text)
                        .text("确认删除？")
                        .actionLabel("删除")
                        .actionListener(listener),
                parent);
    }
}