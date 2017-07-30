package com.miracle.qmt.ui.activity;

import android.content.Intent;
import android.view.View;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseListActivity;
import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseViewHolder;
import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyResult;
import com.miracle.qmt.ui.model.NewsItem;
import com.miracle.qmt.ui.view.viewHolder.CommonNewsVH;
import com.miracle.qmt.util.ConstantKey;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Administrator on 2017/7/26.
 */
public class CommNewsListActivity extends BaseListActivity<BasePresenter, NewsItem, ArrayList<NewsItem>> {
    private String mType;

    @Override
    public void initView() {
        super.initView();
        mType = getIntent().getStringExtra(ConstantKey.STRING_ITEM);
        mTvTitle.setText(getIntent().getStringExtra(ConstantKey.STRING_ITEM2));
        mIVNoData.setImageResource(R.drawable.icon_no_news);
    }

    @Override
    public int getLayoutType() {
        return BaseListActivity.LAYOUT_TYPE_LINEAR;
    }

    @Override
    public Class<? extends BaseViewHolder<NewsItem>> getViewHolder() {
        return CommonNewsVH.class;
    }

    @Override
    public Observable<MyResult<ArrayList<NewsItem>>> getObservble() {
        return ApiFactory.getXynSingleton().Informationlist(mType,mPage);
    }

    @Override
    public void onReloadSuccess(ArrayList<NewsItem> response) {
        dealData(response);
    }

    @Override
    public void onNextPageSuccess(ArrayList<NewsItem> response) {
        dealNextPage(response);
    }

    @Override
    public void onItemClickEvent(View view, NewsItem item, int position) {
        super.onItemClickEvent(view, item, position);
        Intent intent = new Intent(mContext,NewsDetailActivity.class);
        intent.putExtra(ConstantKey.STRING_ITEM,item.getDetail_id());
        showActivity(intent);
    }
}
