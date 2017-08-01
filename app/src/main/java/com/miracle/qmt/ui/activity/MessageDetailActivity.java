package com.miracle.qmt.ui.activity;


import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.MessageDetailContract;
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
    public void onNetErrorClick() {

    }

}
