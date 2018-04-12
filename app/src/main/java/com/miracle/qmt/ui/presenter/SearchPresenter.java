package com.miracle.qmt.ui.presenter;

import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserverNew;
import com.miracle.qmt.ui.contract.SearchContract;
import com.miracle.qmt.ui.model.SearchBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WJQ on 2016/11/14.
 */

public class SearchPresenter extends SearchContract.Presenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getData(String content) {

        mSubscription = ApiFactory.getXynSingleton().search(content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<SearchBean>(mView) {
                    @Override
                    public void onError(Throwable e) {
                        mView.showMsg(e.toString());

                    }

                    @Override
                    public void onNext(SearchBean jsonObject) {
                        mView.getData(jsonObject);

                    }


                });


    }
}
