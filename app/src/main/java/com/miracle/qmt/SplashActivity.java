package com.miracle.qmt;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.TextView;

import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.activity.LoginActivity;
import com.miracle.qmt.ui.activity.RegisterActivity;
import com.miracle.qmt.ui.contract.LoginContract;
import com.miracle.qmt.ui.model.LoginSuccModel;
import com.miracle.qmt.ui.presenter.LoginPresenter;
import com.miracle.qmt.util.PreferencesUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/29.
 */
public class SplashActivity extends BaseActivity<LoginPresenter> implements LoginContract.View{

    @Bind(R.id.tv_login)
    TextView mTvLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onNetErrorClick() {

    }
    @OnClick(R.id.tv_reigister)
    public void mRegitsterClick(){
        showActivity(new Intent(mContext, RegisterActivity.class));
    }

    @OnClick(R.id.tv_login)
    public void mLoginClick(){
        String tel = PreferencesUtils.getPreferences(mContext,PreferencesUtils.USER_TEL);
        String pwd = PreferencesUtils.getPreferences(mContext,PreferencesUtils.USER_PWD);

        if(!TextUtils.isEmpty(tel) && !TextUtils.isEmpty(pwd)){//若存在账户和密码，那么直接登录
            showProgressDialog("登录中");
            mPresenter.login(tel,pwd);
        }else{
            showActivity(new Intent(mContext, LoginActivity.class));
            finish();
        }

    }

    @Override
    public void loginSucc(LoginSuccModel model) {
        showActivity(new Intent(mContext, MainActivity.class));
        finish();
    }

    @Override
    public void showMsg(String msg) {
        super.showMsg(msg);
        showActivity(new Intent(mContext, LoginActivity.class));
        finish();
    }

    @Override
    public void onNetError() {
        super.onNetError();
        showActivity(new Intent(mContext, LoginActivity.class));
        finish();
    }
}
