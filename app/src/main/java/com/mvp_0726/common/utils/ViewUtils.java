package com.mvp_0726.common.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/8/6.
 */

public class ViewUtils {

    public static void setTextViewFl(View view, int left, int top, int right, int bottom) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(left, top, right, bottom);//4个参数按顺序分别是左上右下
        view.setLayoutParams(layoutParams);
    }

    public static void setTextViewRl(View view, int left, int top, int right, int bottom) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(left, top, right, bottom);//4个参数按顺序分别是左上右下
        view.setLayoutParams(layoutParams);
    }

    public static void setTextViewLl(View view, int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(left, top, right, bottom);//4个参数按顺序分别是左上右下
        view.setLayoutParams(layoutParams);
    }
}
