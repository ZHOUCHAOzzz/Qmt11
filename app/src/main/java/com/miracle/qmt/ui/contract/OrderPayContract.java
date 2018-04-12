package com.miracle.qmt.ui.contract;

import com.google.gson.JsonObject;
import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;
import com.miracle.qmt.ui.model.WepayBean;

/**
 * Created by ZhouChao on 2017/9/5.
 */

public interface OrderPayContract {
    public interface View extends BaseView {
        public void pay(WepayBean bean);
        public void checkphone(JsonObject obj);
    }

    public abstract class Presenter extends BasePresenter<View> {
        public abstract void pay();
        public abstract void checkphone(String phone);
    }
}
