package com.miracle.qmt.ui.presenter;

import android.util.Log;

import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserver;
import com.miracle.qmt.ui.contract.UploadPicturesContract;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WJQ on 2016/11/14.
 */

public class UploadPicturesPresenter extends UploadPicturesContract.Presenter {
    @Override
    public void onStart() {

    }



    @Override
    public void getInfoImgs(List<String> imgList) {

        Map<String, RequestBody>  map = new HashMap<>();
        for (int i = 0; i < imgList.size(); i++) {

            File file = new File(imgList.get(i));
            RequestBody body=RequestBody.create(MediaType.parse("image/*"), file);
            map.put("img[]\";filename=\""+file.getName(), body);

        }
            mSubscription = ApiFactory.getXynSingleton().tutu(map)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new MyObserver<String>(mView) {
                        @Override
                        public void onSuccess(String response) {
                            super.onSuccess(response);
                            Log.d("2222222",response);

                            mView.getInfoImgsSucc(response);

                        }

                        @Override
                        public void onFail(String info) {
                            super.onFail(info);
                            mView.showMsg(info);
                        }

                        @Override
                        public void onCompleted() {
                            super.onCompleted();

                        }
                    });
            addSubscription(mSubscription);




    }

}
