package com.miracle.qmt.network;


import com.miracle.qmt.base.BaseView;
import com.orhanobut.logger.Logger;

import rx.Observer;

/**
 * Created by Administrator on 2016/2/24 0024.
 */
public abstract class MyObserverNew<T> implements Observer<T> {

    private BaseView mView;

    public MyObserverNew(BaseView view) {
        mView = view;
    }



    @Override
    public void onCompleted() {
        Logger.d("onCompleted");
        if (mView != null)
            mView.onComplete();
    }

  /*  @Override
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
    }*/




}
