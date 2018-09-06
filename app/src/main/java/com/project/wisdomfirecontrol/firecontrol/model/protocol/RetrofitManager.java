package com.project.wisdomfirecontrol.firecontrol.model.protocol;

import com.project.wisdomfirecontrol.R;
import com.project.wisdomfirecontrol.common.base.Global;
import com.project.wisdomfirecontrol.firecontrol.base.MyApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author LMX
 */
public class RetrofitManager {

    private static RetrofitManager instance;

    private RetrofitManager() {
    }

    static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                    initRetrofit();
                }
            }
        }
        return instance;
    }

    private static Retrofit mRetrofit;
    private static IHttpService mHttpService;

    public IHttpService getHttpService() {
        return mHttpService;
    }

    private static void initRetrofit() {
        boolean networkConnected = MyApplication.getInstance().isNetworkConnected();
        if (networkConnected) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(IHttpService.HOST_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mHttpService = mRetrofit.create(IHttpService.class);
        } else {
            Global.showToast(MyApplication.instance.getResources().getString(R.string.no_net));
        }
    }


    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)//失败重连
            .build();
}
