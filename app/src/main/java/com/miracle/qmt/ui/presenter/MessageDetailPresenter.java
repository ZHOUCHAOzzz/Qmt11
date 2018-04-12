package com.miracle.qmt.ui.presenter;

import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserverNew;
import com.miracle.qmt.ui.contract.MessageDetailContract;
import com.miracle.qmt.ui.model.MessageBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WJQ on 2016/11/14.
 */

public class MessageDetailPresenter extends MessageDetailContract.Presenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getData() {
        mSubscription = ApiFactory.getXynSingleton().messages("1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<MessageBean>(mView) {
                    @Override
                    public void onError(Throwable e) {
                        mView.showMsg(e.toString());

                    }

                    @Override
                    public void onNext(MessageBean bean) {
                        mView.getData(bean);

                    }
                });
        addSubscription(mSubscription);

    }
}
