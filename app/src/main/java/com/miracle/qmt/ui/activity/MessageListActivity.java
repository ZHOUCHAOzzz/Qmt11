package com.miracle.qmt.ui.activity;

import android.content.Intent;
import android.view.View;

import com.miracle.qmt.base.BaseListActivity;
import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseViewHolder;
import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyResult;
import com.miracle.qmt.ui.model.MessageItem;
import com.miracle.qmt.ui.view.viewHolder.MessageVH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Administrator on 2017/7/31.
 */
public class MessageListActivity extends BaseListActivity<BasePresenter, MessageItem, ArrayList<MessageItem>> {
    @Override
    public int getLayoutType() {
        return BaseListActivity.LAYOUT_TYPE_LINEAR;
    }

    @Override
    public Class<? extends BaseViewHolder<MessageItem>> getViewHolder() {
        return MessageVH.class;
    }

    @Override
    public Observable<MyResult<ArrayList<MessageItem>>> getObservble() {
        return ApiFactory.getXynSingleton().messages("");
    }

    @Override
    public void onReloadSuccess(ArrayList<MessageItem> response) {
        dealData(response);
    }

    @Override
    public void onNextPageSuccess(ArrayList<MessageItem> response) {

    }

    @Override
    public void onItemClickEvent(View view, MessageItem item, int position) {
        super.onItemClickEvent(view, item, position);
        Intent intent = new Intent(mContext, MessageDetailActivity.class);
        showActivity(intent);
    }
}
