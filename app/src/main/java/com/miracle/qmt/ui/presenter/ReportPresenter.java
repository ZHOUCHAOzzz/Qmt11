package com.miracle.qmt.ui.presenter;

import com.miracle.qmt.AppController;
import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserverNew;
import com.miracle.qmt.ui.contract.ReportContract;
import com.miracle.qmt.ui.model.ResultBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WJQ on 2016/11/14.
 */

public class ReportPresenter extends ReportContract.Presenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getData(String content) {
        String userid= AppController.getInstance().getUser().getUser_id()+"";
        mSubscription = ApiFactory.getXynSingleton().report(userid,content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<ResultBean>(mView) {
                    @Override
                    public void onError(Throwable e) {
                        mView.showMsg(e.toString());

                    }

                    @Override
                    public void onNext(ResultBean jsonObject) {
                        mView.getData(jsonObject);

                    }


                });


    }
}
