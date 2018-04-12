package com.miracle.qmt.ui.activity;

import android.content.Intent;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.util.ConstantKey;

/**
 * Created by ZhouChao on 2017/9/29.
 */

public class DetailsActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("详细说明");
        Intent intent=getIntent();
        String title = intent.getStringExtra(ConstantKey.STRING_ITEM);
        String txt = intent.getStringExtra(ConstantKey.STRING_ITEM2);

    }

    @Override
    protected void onNetErrorClick() {

    }
}
