package com.miracle.qmt.ui.activity;


import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.MessageDetailContract;
import com.miracle.qmt.ui.model.MessageBean;
import com.miracle.qmt.ui.presenter.MessageDetailPresenter;

/**
 * 消息
 */
public class MessageDetailActivity extends BaseActivity<MessageDetailPresenter> implements MessageDetailContract.View {


    @Override
    public int getLayoutId() {
        return R.layout.activity_message_detail;
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("消息");

    }

    @Override
    public void onNetErrorClick() {

    }

    @Override
    public void getData(MessageBean model) {

    }
}
