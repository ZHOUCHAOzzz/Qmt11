package com.miracle.qmt.ui.contract;

import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;
import com.miracle.qmt.ui.model.Carousel;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/29.
 */

public interface HomeContract {
    public interface View extends BaseView {
        public void onBannerSucc(ArrayList<Carousel> response);//轮播返回
    }

    public abstract class Presenter extends BasePresenter<View> {
        public abstract void getBanner();//获取轮播
    }
}
