package com.miracle.qmt.ui.contract;

import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;
import com.miracle.qmt.util.UserManager;

/**
 * Created by WJQ on 2016/11/14.
 */
public interface UpdateInfoContract {
    public interface View extends BaseView {
        public void updateSucc();
    }

    public abstract class Presenter extends BasePresenter<View> {
        public abstract void updateInfo(UserManager.User user);
    }

}
