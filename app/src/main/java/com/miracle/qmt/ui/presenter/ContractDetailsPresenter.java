package com.miracle.qmt.ui.presenter;


import android.util.Log;

import com.miracle.qmt.AppController;
import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserverNew;
import com.miracle.qmt.ui.contract.ContractDetailContract;
import com.miracle.qmt.ui.model.CollectionBean;
import com.miracle.qmt.ui.model.ConDetailsBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/22.
 */

public class ContractDetailsPresenter extends ContractDetailContract.Presenter {

    @Override
    public void getData(String  userid) {
        mSubscription = ApiFactory.getXynSingleton().condetails(userid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<ConDetailsBean>(mView) {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ConDetailsBean bean) {
                        mView.getContractSucc(bean);

                    }
                });

        addSubscription(mSubscription);

    }

    @Override
    public void collection(String tx_id) {
        Log.d("userid",AppController.getInstance().getUser().getUser_id()+"");
        mSubscription = ApiFactory.getXynSingleton().txl_collection(AppController.getInstance().getUser().getUser_id()+"",tx_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<CollectionBean>(mView) {
                    @Override
                    public void onError(Throwable e) {
                        mView.showMsg(e.toString());

                    }

                    @Override
                    public void onNext(CollectionBean bean) {
                        mView.collection(bean);

                    }
                });
        addSubscription(mSubscription);
    }

    @Override
    public void onStart() {


    }
}
