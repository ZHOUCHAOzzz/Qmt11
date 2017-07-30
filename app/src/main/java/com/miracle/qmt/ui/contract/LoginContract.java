package com.miracle.qmt.ui.contract;

import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;
import com.miracle.qmt.ui.model.LoginSuccModel;

/**
 * Created by Administrator on 2017/7/29.
 * 登录
 */
public interface LoginContract {
    public interface View extends BaseView{
        public void loginSucc(LoginSuccModel model);
    }

    public abstract class Presenter extends BasePresenter<View>{
        public abstract void login(String tel,String pwd);
    }
}
