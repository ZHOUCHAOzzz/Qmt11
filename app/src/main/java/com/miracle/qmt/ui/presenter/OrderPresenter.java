package com.miracle.qmt.ui.presenter;

import com.google.gson.JsonObject;
import com.miracle.qmt.AppController;
import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserverNew;
import com.miracle.qmt.ui.contract.OrderPayContract;
import com.miracle.qmt.ui.model.WepayBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZhouChao on 2017/9/5.
 */

public class OrderPresenter extends OrderPayContract.Presenter {

    @Override
    public void pay() {
        mSubscription = ApiFactory.getXynSingleton().wechatpay("1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<WepayBean>(mView) {
                    @Override
                    public void onError(Throwable e) {



                    }

                    @Override
                    public void onNext(WepayBean wechatBean) {
                        mView.pay(wechatBean);

                    }
                });
        addSubscription(mSubscription);



    }

    @Override
    public void checkphone(String phone) {
        String userid= AppController.getInstance().getUser().getUser_id()+"";
        mSubscription = ApiFactory.getXynSingleton().checkphone(userid,phone)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<JsonObject>(mView) {
                    @Override
                    public void onError(Throwable e) {



                    }

                    @Override
                    public void onNext(JsonObject object) {
                       // mView.pay(wechatBean);
                        mView.checkphone(object);

                    }
                });
        addSubscription(mSubscription);


    }

    @Override
    public void onStart() {



    }
}
