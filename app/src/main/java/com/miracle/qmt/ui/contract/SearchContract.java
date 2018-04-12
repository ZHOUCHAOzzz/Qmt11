package com.miracle.qmt.ui.contract;

import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;
import com.miracle.qmt.ui.model.SearchBean;

/**
 * Created by WJQ on 2016/11/14.
 */
public interface SearchContract {
    public interface View extends BaseView {
        public void getData(SearchBean s);

    }

    public abstract class Presenter extends BasePresenter<View> {
        public abstract void getData(String content);

    }
}
