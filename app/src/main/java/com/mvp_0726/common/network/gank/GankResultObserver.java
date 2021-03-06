package com.mvp_0726.common.network.gank;


import io.reactivex.disposables.Disposable;

/**
 * Created  on 2018-03-20.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class GankResultObserver<T extends GankIoCustom> extends GankHttpObserver<T> {

    protected abstract void onLoading(Disposable d);

    protected abstract void onSuccess(T o);

    protected abstract void onFail(Exception e);

    @Override
    protected void onStart(Disposable d) {
        onLoading(d);
    }

    @Override
    protected void _onNext(T responseCustom) {
        onSuccess(responseCustom);
    }

    @Override
    protected void _onError(Exception error) {
        onFail(error);
//        Log.e("网络处理异常", error.getMessage());
    }
}
