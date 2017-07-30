package com.miracle.qmt.ui.activity;


import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.UpdateInfoContract;
import com.miracle.qmt.ui.presenter.UpdateInfoPresenter;

public class UpdateInfoActivity extends BaseActivity<UpdateInfoPresenter> implements UpdateInfoContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_info;
    }

    @Override
    public void onNetErrorClick() {

    }

    @Override
    public void updateSucc() {

    }
}
