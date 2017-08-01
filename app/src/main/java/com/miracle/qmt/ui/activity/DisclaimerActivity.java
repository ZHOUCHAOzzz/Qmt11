package com.miracle.qmt.ui.activity;

import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.util.ConstantKey;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/7/30.
 */
public class DisclaimerActivity extends BaseActivity {
    @Bind(R.id.tv)
    TextView mTv;
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
        String title = getIntent().getStringExtra(ConstantKey.STRING_ITEM);
        String txt = getIntent().getStringExtra(ConstantKey.STRING_ITEM2);
        mTvTitle.setText(title);
        mTv.setText(txt);
    }
}
