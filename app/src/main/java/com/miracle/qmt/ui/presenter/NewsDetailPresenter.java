package com.miracle.qmt.ui.presenter;

import com.miracle.qmt.AppController;
import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserver;
import com.miracle.qmt.network.MyObserverNew;
import com.miracle.qmt.ui.contract.NewsDetailContract;
import com.miracle.qmt.ui.model.CollectionBean;
import com.miracle.qmt.ui.model.NewsItem;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WJQ on 2016/11/14.
 */

public class NewsDetailPresenter extends NewsDetailContract.Presenter {
    @Override
    public void onStart() {

    }

    @Override
    public void getNewsDetail(String id) {
        mSubscription = ApiFactory.getXynSingleton().informationdetail(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserver<NewsItem>(mView) {
                    @Override
                    public void onSuccess(NewsItem response) {
                        super.onSuccess(response);
                        mView.getDetailSucc(response);
                    }

                    @Override
                    public void onFail(String info) {
                        super.onFail(info);
                        mView.showMsg(info);
                    }
                });

    }

    @Override
    public void conllection(String id) {
        mSubscription = ApiFactory.getXynSingleton().collection(id, AppController.getInstance().getUser().getUser_id()+"")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<CollectionBean>(mView) {
                    @Override
                    public void onError(Throwable e) {
                        mView.showMsg(e.toString());

                    }

                    @Override
                    public void onNext(CollectionBean bean) {
                        mView.conllection(bean);

                    }
                });
        addSubscription(mSubscription);

    }
}
