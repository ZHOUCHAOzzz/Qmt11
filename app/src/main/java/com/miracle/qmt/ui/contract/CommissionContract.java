package com.miracle.qmt.ui.contract;

import com.google.gson.JsonObject;
import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;

/**
 * Created by ZhouChao on 2017/9/7.
 */

public interface CommissionContract {
    public interface View extends BaseView {
        public void getData(JsonObject comBean);
        public void tixian(JsonObject comBean);



    }

    public abstract class Presenter extends BasePresenter<View> {
        public abstract void getData();
        public abstract void tixian(String price,String openid);

    }
}
