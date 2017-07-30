package com.miracle.qmt.ui.presenter;

import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserver;
import com.miracle.qmt.ui.contract.LoginContract;
import com.miracle.qmt.ui.model.LoginSuccModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/29.
 */
public class LoginPresenter extends LoginContract.Presenter {
    @Override
    public void onStart() {

    }

    @Override
    public void login(String tel, String pwd) {
        mSubscription = ApiFactory.getXynSingleton().login(tel,pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserver<LoginSuccModel>(mView) {
                    @Override
                    public void onSuccess(LoginSuccModel response) {
                        super.onSuccess(response);
                        mView.loginSucc(response);
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
