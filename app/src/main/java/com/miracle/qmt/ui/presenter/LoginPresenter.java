package com.miracle.qmt.ui.presenter;

import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserverNew;
import com.miracle.qmt.ui.contract.LoginContract;
import com.miracle.qmt.ui.model.LoginBean;

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
                .subscribe(new MyObserverNew<LoginBean>(mView) {
                    @Override
                    public void onError(Throwable e) {
                        mView.showMsg("账号密码错误");


                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.loginSucc(loginBean);

                    }
                });
                /*.subscribe(new MyObserver<LoginSuccModel>(mView) {
                    @Override
                    public void onSuccess(LoginSuccModel response) {
                        super.onSuccess(response);
                        mView.loginSucc(response);
                    }

                    @Override
                    public void onFail(String info) {
                        super.onFail(info);

                    }
                });*/
        addSubscription(mSubscription);
    }
}
