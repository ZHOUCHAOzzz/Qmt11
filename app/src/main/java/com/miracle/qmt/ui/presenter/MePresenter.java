package com.miracle.qmt.ui.presenter;

import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserver;
import com.miracle.qmt.ui.contract.MeContract;
import com.miracle.qmt.ui.model.NewsItem;
import com.miracle.qmt.util.PreferencesUtils;
import com.miracle.qmt.util.UserManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/30.
 */

public class MePresenter extends MeContract.Presenter {
    @Override
    public void getInfo() {
//        String id = PreferencesUtils.getPreferences(context,PreferencesUtils.USER_ID);
//        mSubscription = ApiFactory.getXynSingleton().geren(id)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new MyObserver<UserManager.User>(mView) {
//                    @Override
//                    public void onSuccess(UserManager.User response) {
//                        super.onSuccess(response);
//                        mView.getInfoSucc(response);
//                    }
//
//                    @Override
//                    public void onFail(String info) {
//                        super.onFail(info);
//                        mView.showMsg(info);
//                    }
//                });
//        addSubscription(mSubscription);
    }

    @Override
    public void onStart() {
    }
}
