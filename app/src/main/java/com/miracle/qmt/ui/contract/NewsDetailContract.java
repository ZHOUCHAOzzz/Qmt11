package com.miracle.qmt.ui.contract;

import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;
import com.miracle.qmt.ui.model.NewsItem;

/**
 * Created by WJQ on 2016/11/14.
 */
public interface NewsDetailContract {
    public interface View extends BaseView {
        public void getDetailSucc(NewsItem item);
    }

    public abstract class Presenter extends BasePresenter<View> {
        public abstract void getNewsDetail(String id);
    }
}
