package com.miracle.qmt.network;



import com.miracle.qmt.base.BaseView;
import com.orhanobut.logger.Logger;


import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;

/**
 * Created by Administrator on 2016/2/24 0024.
 */
public abstract class MyObserver<T> implements Observer<MyResult<T>> {

    private BaseView mView;

    public MyObserver(BaseView view) {
        mView = view;
    }



    @Override
    public void onCompleted() {
        Logger.d("onCompleted");
        if (mView != null)
            mView.onComplete();
    }

    @Override
    public void onError(Throwable e) {
        Logger.d("onError");
        if (mView != null)
            mView.onNetError();
        if (e instanceof HttpException) {
            HttpException response = (HttpException) e;
            int code = response.code();
            Logger.d("HttpException error code is  " + code);
        }
        if (e instanceof SocketTimeoutException) {

        }

        if (e instanceof UnknownHostException) {
            UnknownHostException response = (UnknownHostException) e;
            Logger.d("UnknownHostException error is " + response.getMessage());
        } else {
            e.printStackTrace();
        }
    }

    @Override
    public void onNext(MyResult<T> t) {
        if (t.getState()!= null && t.getState().equals("1")) {
            onSuccess(t.getData());
        } else {
            onFail(t.getMsg());
        }
    }

    public void onSuccess(T response) {
    }

    public void onFail(String info) {

    }
}
