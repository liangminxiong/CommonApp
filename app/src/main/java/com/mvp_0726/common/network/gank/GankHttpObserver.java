package com.mvp_0726.common.network.gank;

import com.mvp_0726.common.network.RxLifeManager;

import java.net.UnknownServiceException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created  on 2018-03-20.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class GankHttpObserver<T> implements Observer<T> {
    private boolean flag = false;//是否切断上下游的事件传递
    public static final int UN_KNOWN_ERROR = 1000;//未知错误

    public GankHttpObserver() {
    }

    public GankHttpObserver(boolean isLink) {
        this.flag = isLink;
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.onStart(d);
        if (flag) {
            RxLifeManager.getRxLifeManager().setCompositeDisposableOnStop(d);
        } else {
            RxLifeManager.getRxLifeManager().setCompositeDisposableOnDestroy(d);
        }
    }

    protected abstract void onStart(Disposable d);

    protected abstract void _onNext(T t);

    //    protected abstract void _onError(ApiException e);
    protected abstract void _onError(Exception e);

    /*
    * t==response
    * */
    @Override
    public void onNext(T t) {
        if (t != null) {
            this._onNext(t);
        }

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof Exception || e instanceof UnknownServiceException) {
            _onError((Exception) e);
        } else {
//            this._onError(new Exception(e, UN_KNOWN_ERROR));
        }
//        this._onError(e);
    }

    @Override
    public void onComplete() {

    }
}
