package com.miracle.qmt.ui.presenter;

import android.util.Log;

import com.miracle.qmt.AppController;
import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserverNew;
import com.miracle.qmt.ui.contract.TxlCollectionContract;
import com.miracle.qmt.ui.model.TXLscBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZhouChao on 2017/9/7.
 */

public class TxlCollectionPresenter extends TxlCollectionContract.Presenter {
    @Override
    public void getData() {

        String userid= AppController.getInstance().getUser().getUser_id()+"";
        Log.d("onerror",userid);
        mSubscription = ApiFactory.getXynSingleton().txl_collectionList(userid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<TXLscBean>(mView) {
                    @Override
                    public void onError(Throwable e) {
                        mView.showMsg(e.toString());
                        Log.d("onerror",e.toString());


                    }

                    @Override
                    public void onNext(TXLscBean comBean) {
                        mView.getData(comBean);

                    }
                });
    }



    @Override
    public void onStart() {
        getData();

    }
}
