package com.mvp_0726.common.view.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.mvp_0726.common.network.NetworkUrl;
import com.mvp_0726.common.utils.Constans;
import com.mvp_0726.common.utils.GsonTools;
import com.mvp_0726.common.utils.PreferencesUtils;

public class H5JSInterface {
    private Context mContext;
    private H5Control control;

    public H5JSInterface(Context context) {
        this.mContext = context;
    }

    public void registerListener(H5Control control) {
        this.control = control;
    }

    public void unRegisterListener() {
        this.control = null;
    }

    /*
     * android发送数据到h5
     * */
    @JavascriptInterface
    public String getDataFromAndroid(String datas) {
        final H5ToAndroidData h5ToAndroidData = GsonTools.changeGsonToBean(datas, H5ToAndroidData.class);
        String result = "Android:";
        switch (h5ToAndroidData.type) {
            case 1:
                /*比如和h5协商type=1是传cookie,前面拼的字段为了区分android端和iOS端，字段需要明确告诉h5 他好切割数据*/
                result += PreferencesUtils.getString(mContext, Constans.COOKIE_PREF);
                break;
        }
        return result;
    }

    /*
     * android发送数据到h5
     * */
    @JavascriptInterface
    public void androidUpload() {
        if (control != null) {
            control.H5ControlAndroidEvent();
        }
    }

    /*
     * android发送数据到h5
     * */
    @JavascriptInterface
    public void androidUploadByEl(String el) {
        if (control != null) {
            control.H5ControlAndroidEvent(el);
        }
    }

    /*
     * h5发送数据到android
     * */
    @JavascriptInterface
    public void sendInfoToAndroid(String datas) {
        final H5ToAndroidData h5ToAndroidData = GsonTools.changeGsonToBean(datas, H5ToAndroidData.class);
        switch (h5ToAndroidData.type) {
            case 1:
                if (control != null) {
                    /*比如type = 1 是跳转h5*/
                    H5ToH5Info h5ToH5Info = GsonTools.changeGsonToBean(GsonTools.createGsonString(h5ToAndroidData.data), H5ToH5Info.class);
                    control.H5ControlAndroidEvent(NetworkUrl.ANDROID_TEST_SERVICE + h5ToH5Info.url, null);
                }
                break;
        }
    }

    /*
     * 打开webview里面的图片
     * */
    @JavascriptInterface
    public void openImage(String img) {
        if (control != null) {
            control.H5ControlAndroidEvent(img, null);
        }
    }

    /*
     * 这个数据结构是和h5商量好的
     * */
    private class H5ToH5Info {
        public String url;
    }
}
