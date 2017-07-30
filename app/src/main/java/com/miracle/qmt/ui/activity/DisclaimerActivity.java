package com.miracle.qmt.ui.activity;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;

/**
 * Created by Administrator on 2017/7/30.
 */

public class DisclaimerActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_disclaimer;
    }

    @Override
    protected void onNetErrorClick() {

    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("免责声明");
    }
}
