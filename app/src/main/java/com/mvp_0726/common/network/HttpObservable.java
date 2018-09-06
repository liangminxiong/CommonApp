package com.mvp_0726.common.network;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by seven
 * on 2018/5/17
 * email:seven2016s@163.com
 */

public class HttpObservable {
//    public static <T> Observable getObservable(Observable<ResponseCustom<T>> customObservable) {
    public static <T> Observable getObservable(Observable<T> customObservable) {
        Observable observable = customObservable
                .map(new ServerResultFunction())
                .onErrorResumeNext(new HttpResultFunction())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
}
