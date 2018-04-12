package com.miracle.qmt.ui.contract;

import android.content.Context;

import com.google.gson.JsonObject;
import com.miracle.qmt.base.BaseView;
import com.miracle.qmt.ui.model.UpUserBean;
import com.miracle.qmt.ui.presenter.UpLoadImgPresenter;

/**
 * Created by Administrator on 2017/7/30.
 */
public interface UpLoadContract {
    public interface View extends BaseView{

        public void checkVipSucc(int result);
        public  void updateSucc(UpUserBean upUserBean);
        public  void  join(JsonObject jsonObject);
    }
    public abstract class Presenter extends UpLoadImgPresenter<View> {
        public abstract void getInfo();
        public abstract void checkVip();
        public abstract void uploadAll(Context context);
        public abstract void join();

    }
}
