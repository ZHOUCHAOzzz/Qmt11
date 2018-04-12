package com.miracle.qmt.ui.contract;

import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;
import com.miracle.qmt.ui.model.TXLscBean;

/**
 * Created by ZhouChao on 2017/9/7.
 */

public interface TxlCollectionContract {
    public interface View extends BaseView {
        public void getData(TXLscBean comBean);




    }

    public abstract class Presenter extends BasePresenter<View> {
        public abstract void getData();


    }
}
