package com.miracle.qmt.ui.activity;


import android.widget.ImageView;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.NewsDetailContract;
import com.miracle.qmt.ui.model.NewsItem;
import com.miracle.qmt.ui.presenter.NewsDetailPresenter;
import com.miracle.qmt.util.CommonFunction;
import com.miracle.qmt.util.ConstantKey;

import butterknife.Bind;

public class NewsDetailActivity extends BaseActivity<NewsDetailPresenter> implements NewsDetailContract.View {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_date)
    TextView mTvDate;
    @Bind(R.id.iv)
    ImageView mIv;
    @Bind(R.id.tv_detail)
    TextView mTvDetail;
    @Bind(R.id.tv_collect)
    TextView mTvCollect;

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void onNetErrorClick() {

    }

    @Override
    public void initView() {
        super.initView();

        mTvTitle.setText("资讯详情");
        mPresenter.getNewsDetail(getIntent().getStringExtra(ConstantKey.STRING_ITEM));
    }

    @Override
    public void getDetailSucc(NewsItem item) {
        mTvTitle.setText(item.getContent());
        mTvDate.setText(item.getAdd_time());
        mTvName.setText("发帖人："+item.getNick_name());
        mTvDetail.setText(item.getDetail_name());
        mTvCollect.setText(item.getCollection()+"人收藏");
        CommonFunction.showImage(mContext,item.getFirst_pic(),mIv);
    }
}
