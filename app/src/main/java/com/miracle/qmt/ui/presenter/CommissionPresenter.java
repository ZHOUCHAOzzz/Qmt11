package com.miracle.qmt.ui.presenter;

import com.google.gson.JsonObject;
import com.miracle.qmt.AppController;
import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserverNew;
import com.miracle.qmt.ui.contract.CommissionContract;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZhouChao on 2017/9/7.
 */

public class CommissionPresenter extends CommissionContract.Presenter {
    @Override
    public void getData() {
        String userid= AppController.getInstance().getUser().getUser_id()+"";
        mSubscription = ApiFactory.getXynSingleton().commission(userid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<JsonObject>(mView) {
                    @Override
                    public void onError(Throwable e) {
                        mView.showMsg(e.toString());


                    }

                    @Override
                    public void onNext(JsonObject comBean) {
                        mView.getData(comBean);

                    }
                });
    }

    @Override
    public void tixian(String price,String openid) {

        mSubscription = ApiFactory.getXynSingleton().tixian(price,openid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<JsonObject>(mView) {
                    @Override
                    public void onError(Throwable e) {
                        mView.showMsg(e.toString());


                    }

                    @Override
                    public void onNext(JsonObject comBean) {
                        mView.tixian(comBean);

                    }
                });


    }

    @Override
    public void onStart() {
        getData();

    }
}
