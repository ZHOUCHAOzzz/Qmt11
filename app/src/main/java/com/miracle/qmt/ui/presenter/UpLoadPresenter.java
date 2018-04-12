package com.miracle.qmt.ui.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.miracle.qmt.AppController;
import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyObserver;
import com.miracle.qmt.network.MyObserverNew;
import com.miracle.qmt.ui.contract.UpLoadContract;
import com.miracle.qmt.ui.model.UpUserBean;
import com.miracle.qmt.ui.model.UserBean;
import com.miracle.qmt.ui.model.VipBean;
import com.miracle.qmt.util.PreferencesUtils;
import com.miracle.qmt.util.UserManager;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/30.
 */

public class UpLoadPresenter extends UpLoadContract.Presenter {
    @Override
    public void getInfo() {
        String id = AppController.getInstance().getUser().getUser_id()+"";
        mSubscription = ApiFactory.getXynSingleton().geren(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserver<UserManager.User>(mView) {
                    @Override
                    public void onSuccess(UserManager.User response) {
                        super.onSuccess(response);
                       // mView.getInfoSucc(response);
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
    public void checkVip() {

        String phone = AppController.getInstance().getUser().getPhone();
        Log.d("111111111111","phone"+phone);
        mSubscription=ApiFactory.getXynSingleton().checkvip(phone)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<VipBean>(mView) {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(VipBean vipBean) {
                        mView.checkVipSucc(vipBean.getResult());

                    }
                });
        addSubscription(mSubscription);


    }

    /*@Field("user_id") String user_id,
            @Field("nick_name") String nick_name,
            @Field("address") String address,
            @Field("management") String management,
            @Field("buy") String buy,
            @Field("company_name") String company_name,
            @Field("name") String name,
            @Field("head_pic") String head_pic,
            @Field("phone") String phone,
            @Field("pic") String pic*/

    @Override
    public void uploadAll(Context context) {

        UserBean user=AppController.getInstance().getUser();
        Map<String,String> map=new HashMap<>();
        if (PreferencesUtils.getPreferences(context,PreferencesUtils.USER_NAME)!=null){



        map.put("user_id",user.getUser_id()+"");
        map.put("nick_name",PreferencesUtils.getPreferences(context,PreferencesUtils.USER_NAME));
        map.put("address",PreferencesUtils.getPreferences(context,PreferencesUtils.USER_ADDRESS));
        map.put("buy",PreferencesUtils.getPreferences(context,PreferencesUtils.USER_BUY));
        map.put("company_name",PreferencesUtils.getPreferences(context,PreferencesUtils.USER_COMPANY_NAME));
        map.put("phone",PreferencesUtils.getPreferences(context,PreferencesUtils.USER_TEL));
        map.put("management",PreferencesUtils.getPreferences(context,PreferencesUtils.USER_MANAGEMENT));
        map.put("head_pic",PreferencesUtils.getPreferences(context,PreferencesUtils.USER_HEAD_PIC));
        map.put("pic",PreferencesUtils.getPreferences(context,PreferencesUtils.USER_PIC));


        mSubscription=ApiFactory.getXynSingleton().gerengai(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<UpUserBean>(mView) {
                    @Override
                    public void onError(Throwable throwable) {
                        mView.showMsg("修改失败");

                    }

                    @Override
                    public void onNext(UpUserBean upUserBean) {
                        mView.updateSucc(upUserBean);

                    }
                    /*@Override
                    public void onSuccess(Object response) {
                        super.onSuccess(response);

                    }

                    @Override
                    public void onFail(String info) {
                        super.onFail(info);

                    }*/

                });
        }else {
            Toast.makeText(context, "请填写真实姓名", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void join() {

        mSubscription=ApiFactory.getXynSingleton().joinpartner(AppController.getInstance().getUser().getUser_id()+"","30000")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserverNew<JsonObject>(mView) {
                    @Override
                    public void onError(Throwable throwable) {
                        mView.showMsg(throwable.toString());

                    }

                    @Override
                    public void onNext(JsonObject obj) {
                        mView.join(obj);

                    }



                });

    }


    @Override
    public void onStart() {
        getInfo();
        //checkVip();
    }
}
