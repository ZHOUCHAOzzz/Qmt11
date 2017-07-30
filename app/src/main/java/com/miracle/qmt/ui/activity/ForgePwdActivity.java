package com.miracle.qmt.ui.activity;


import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.ForgePwdContract;
import com.miracle.qmt.ui.presenter.ForgePwdPresenter;

/**
 * 忘记密码
 */
public class ForgePwdActivity extends BaseActivity<ForgePwdPresenter> implements ForgePwdContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_forge_pwd;
    }

    @Override
    public void onNetErrorClick() {

    }

}
