package com.mvp_0726.common.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.mvp_0726.common.base.MvpApplication;
import com.mvp_0726.common.utils.Constans;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created  on 2018-05-26.
 */

public class AddCookieInterceptor implements Interceptor {
    public static String PREFERENCE_NAME = "Config";

    /*  private Context mContext;

      public AddCookieInterceptor(Context context) {
          this.mContext = context;
      }
  */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        String cookie = getCookie(request.url().toString(), request.url().host());
        if (!TextUtils.isEmpty(cookie)) {
            builder.addHeader("cookie", cookie);
        }
        return chain.proceed(builder.build());
    }

    private String getCookie(String url, String host) {
        SharedPreferences sp = MvpApplication.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        /*if (!TextUtils.isEmpty(url) && sp.contains(url) && !TextUtils.isEmpty(sp.getString(url, ""))) {
            return sp.getString(url, "");
        }
        if (!TextUtils.isEmpty(host) && sp.contains(host) && !TextUtils.isEmpty(sp.getString(host, ""))) {
            return sp.getString(host, "");
        }*/
        return sp.getString(Constans.COOKIE_PREF, null);
    }
}
