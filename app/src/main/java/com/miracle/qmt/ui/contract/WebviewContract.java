package com.miracle.qmt.ui.contract;

import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;

/**
 * Created by ZhouChao on 2017/9/6.
 */

public interface WebviewContract {
    public interface View extends BaseView {
        public void getQrcode(String url);
    }

    public abstract class Presenter extends BasePresenter<View> {
        public abstract void getQrcode();

    }
}
