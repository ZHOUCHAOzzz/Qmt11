package com.miracle.qmt.ui.presenter;

import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;
import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserver;
import com.miracle.qmt.ui.model.Carousel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/30.
 */

public class UpLoadImgPresenter<O extends BaseView> extends BasePresenter<O>{
    @Override
    public void onStart() {

    }
    public void uploadImg(ArrayList<String> imgList){
        Map<String, RequestBody> imgs = new HashMap<>();

        for (int i = 0; i < imgList.size(); i++) {
            File file = new File(imgList.get(i));
            imgs.put("img\";filename=\""+file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        }
        mSubscription = ApiFactory.getXynSingleton().tutu(imgs)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserver<Object>(mView) {
                    @Override
                    public void onSuccess(Object response) {
                        super.onSuccess(response);
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
