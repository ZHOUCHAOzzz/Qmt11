package com.miracle.qmt.ui.presenter;

import android.util.Log;

import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserverNew;
import com.miracle.qmt.ui.contract.WebviewContract;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/30.
 */

public class WebviewPresenter extends WebviewContract.Presenter {

    @Override
    public void getQrcode() {
        mSubscription = ApiFactory.getXynSingleton().getqrcode(1)
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeOn(Schedulers.io())
               .subscribe(new MyObserverNew<String>(mView) {
                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onNext(String s) {
                       Log.d("url",s.substring(7,s.length()));
                       mView.getQrcode(s.substring(7,s.length()));

                   }
               });

    }

    @Override
    public void onStart() {
        getQrcode();

    }
}
