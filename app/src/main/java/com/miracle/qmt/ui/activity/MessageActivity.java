package com.miracle.qmt.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.MessageDetailContract;
import com.miracle.qmt.ui.model.MessageBean;
import com.miracle.qmt.ui.model.adapter.MessageAdapter;
import com.miracle.qmt.ui.presenter.MessageDetailPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/13.
 */

public class MessageActivity extends BaseActivity<MessageDetailPresenter> implements MessageDetailContract.View {
    @Bind(R.id.rv_mess)
    RecyclerView rvMess;
    private MessageAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_message;

    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("我的消息");
        mPresenter.getData();
    }

    @Override
    protected void onNetErrorClick() {

    }

    @Override
    public void getData(MessageBean model) {
        adapter=new MessageAdapter(mContext,model.getData());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        rvMess.setLayoutManager(linearLayoutManager);
        rvMess.setAdapter(adapter);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
