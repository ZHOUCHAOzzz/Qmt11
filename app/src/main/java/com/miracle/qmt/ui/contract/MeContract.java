package com.miracle.qmt.ui.contract;

import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;
import com.miracle.qmt.ui.presenter.UpLoadImgPresenter;
import com.miracle.qmt.util.UserManager;

/**
 * Created by Administrator on 2017/7/30.
 */
public interface MeContract {
    public interface View extends BaseView{
        public void getInfoSucc(UserManager.User user);
    }
    public abstract class Presenter extends UpLoadImgPresenter<View> {
        public abstract void getInfo();
    }
}
