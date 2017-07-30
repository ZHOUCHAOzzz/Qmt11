package com.miracle.qmt.ui.presenter;

import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserver;
import com.miracle.qmt.ui.contract.RegisterContract;
import com.miracle.qmt.ui.model.NewsItem;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WJQ on 2016/11/14.
 */

public class RegisterPresenter extends RegisterContract.Presenter {
    @Override
    public void onStart() {

    }

    @Override
    public void register(RegisterContract.RegisterPostModel model) {
        mSubscription = ApiFactory.getXynSingleton().register(model.tel, model.pwd, model.nickname, model.code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserver<Object>(mView) {
                    @Override
                    public void onSuccess(Object response) {
                        super.onSuccess(response);
                        mView.registerSucc();
                    }

                    @Override
                    public void onFail(String info) {
                        super.onFail(info);
                        mView.showMsg(info);
                    }
                });
        addSubscription(mSubscription);
    }

    @Override
    public void getCode(String tel) {
        mSubscription = ApiFactory.getXynSingleton().registercode(tel)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserver<Object>(mView) {
                    @Override
                    public void onSuccess(Object response) {
                        super.onSuccess(response);
                        mView.getCodeSucc();
                    }

                    @Override
                    public void onFail(String info) {
                        super.onFail(info);
                        mView.showMsg(info);
                    }
                });
        addSubscription(mSubscription);
    }
}
