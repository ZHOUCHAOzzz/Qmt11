package com.miracle.qmt.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.MessageDetailContract;
import com.miracle.qmt.ui.model.MessageBean;
import com.miracle.qmt.ui.presenter.MessageDetailPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZhouChao on 2017/9/18.
 */

public class AboutActivity extends BaseActivity<MessageDetailPresenter> implements MessageDetailContract.View {
    @Bind(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @Bind(R.id.title_txt)
    TextView titleTxt;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void onNetErrorClick() {

    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("关于我们");
    }

    @Override
    public void getData(MessageBean model) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
