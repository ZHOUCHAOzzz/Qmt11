package com.miracle.qmt.ui.presenter;

import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserver;
import com.miracle.qmt.ui.contract.HomeContract;
import com.miracle.qmt.ui.model.Carousel;
import com.miracle.qmt.ui.model.HomeNewsItem;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/29.
 */

public class HomePresenter extends HomeContract.Presenter {
    @Override
    public void getBanner() {
        mSubscription = ApiFactory.getXynSingleton().carousel()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserver<ArrayList<Carousel>>(mView) {
                    @Override
                    public void onSuccess(ArrayList<Carousel> response) {
                        super.onSuccess(response);
                        mView.onBannerSucc(response);
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
    public void getNews() {
        mSubscription = ApiFactory.getXynSingleton().news("1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserver<ArrayList<HomeNewsItem>>(mView) {
                    @Override
                    public void onSuccess(ArrayList<HomeNewsItem> response) {
                        super.onSuccess(response);
                        mView.onNewsSucc(response);
                    }

                    @Override
                    public void onFail(String info) {
                        super.onFail(info);
                       // mView.showMsg(info);
                    }
                });
        addSubscription(mSubscription);
    }

    @Override
    public void onStart() {
        getBanner();
        getNews();
    }
}
