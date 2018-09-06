package com.mvp_0726.common.utils;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.mvp_0726.common.view.webview.H5Control;

public class JavaInterfaceA {

    private Context mContext;

    public JavaInterfaceA(Context context) {
        this.mContext = context;
    }

    private H5Control control;


    public void registerListener(H5Control control) {
        this.control = control;
    }

    public void unRegisterListener() {
        this.control = null;
    }

    @JavascriptInterface
    public void androidUpload() {
        Global.showToast("AAAAAAAAAA");
//        if (control != null) {
//            control.H5ControlAndroidEvent(msg, null);
//        }
    }
}
