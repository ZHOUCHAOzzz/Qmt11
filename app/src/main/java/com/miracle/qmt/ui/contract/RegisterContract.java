package com.miracle.qmt.ui.contract;

import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseView;

/**
 * Created by WJQ on 2016/11/14.
 */
public interface RegisterContract {
    public interface View extends BaseView {
        public void registerSucc();
        public void getCodeSucc();
    }

    public abstract class Presenter extends BasePresenter<View> {
        public abstract void register(RegisterPostModel model);
        public abstract void getCode(String tel);
    }

    public class RegisterPostModel{
        public String tel;
        public String pwd;
        public String code;
        public String nickname;
    }
}
