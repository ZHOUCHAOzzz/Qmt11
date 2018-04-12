package com.miracle.qmt.ui.presenter;

import android.util.Log;

import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;
import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserver;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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
    public void uploadImg(List<String> imgList){
        Map<String, RequestBody> imgs = new HashMap<>();

        for (int i = 0; i < imgList.size(); i++) {
            File file = new File(imgList.get(i));
            imgs.put("img\";filename=\""+file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
            Log.d("222222","img\";filename=\""+file.getName());
        }
        mSubscription = ApiFactory.getXynSingleton().tutu(imgs)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserver<String>(mView) {
                    @Override
                    public void onSuccess(String response) {
                        super.onSuccess(response);
                        Log.d("2222222",response);
                        mView.onUpLoadSucc(response);
                    }

                    @Override
                    public void onFail(String info) {
                        super.onFail(info);
                        Log.d("2222222",info);
                        mView.showMsg(info);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        Log.d("2222222","onCompleted");
                    }

                });
        addSubscription(mSubscription);
    }

}
