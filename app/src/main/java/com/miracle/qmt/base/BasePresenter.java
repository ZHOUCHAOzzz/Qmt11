package com.miracle.qmt.base;

import android.content.Context;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 处理基类
 * Created by WJQ on 2016/11/10.
 * T View的泛型
 */

public abstract class BasePresenter<T extends BaseView> {
    private CompositeSubscription mCompositeSubscription;
    public Subscription mSubscription;
    public Context context;
    public T mView;

    public String version = "1.0";
    public String ticket = "A0F1490A20D0211C997B44BC357E1972DEAB8AE3";

    public void setView(T v,Context context) {
        this.mView = v;
        this.context = context;
        this.onStart();
    }

    public abstract void onStart();

    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        return this.mCompositeSubscription;
    }

    public void addSubscription(Subscription s) {
        getCompositeSubscription().add(s);
    }

    public void onDestroy() {
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }

//    public void checkOrderStatus() {
//        String userId = PreferencesUtils.getPreferences(context , PreferencesUtils.USER_ID);
//        mSubscription = ApiFactory.getXynSingleton().unpaid(userId)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new MyObserver<OrderStatus>(mView) {
//
//                    @Override
//                    public void onSuccess(OrderStatus model) {
//                        super.onSuccess(model);
//                        mView.onStatusSucc(false);
//                    }
//
//                    @Override
//                    public void onFail(String info) {
//                        super.onFail(info);
////                        mView.showMsg(info);
//                        mView.onStatusSucc(false);
//                    }
//                });
//        addSubscription(mSubscription);
//    }
}
