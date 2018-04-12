package com.miracle.qmt.ui.activity;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.NewsDetailContract;
import com.miracle.qmt.ui.model.CollectionBean;
import com.miracle.qmt.ui.model.NewsItem;
import com.miracle.qmt.ui.presenter.NewsDetailPresenter;
import com.miracle.qmt.util.CommonFunction;
import com.miracle.qmt.util.ConstantKey;

import butterknife.Bind;

public class NewsDetailActivity extends BaseActivity<NewsDetailPresenter> implements NewsDetailContract.View {
    @Bind(R.id.tv_title)
    TextView tv_title;
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
        tv_title.setText("资讯详情");

       // Log.d("99999999",getIntent().getStringExtra(ConstantKey.STRING_ITEM));
        if (getIntent().getStringExtra(ConstantKey.STRING_ITEM)==null){
            mPresenter.getNewsDetail("0");
        }
        mPresenter.getNewsDetail(getIntent().getStringExtra(ConstantKey.STRING_ITEM));

    }

    @Override
    public void getDetailSucc(final NewsItem item) {
        Log.d("111111111",item.getNick_name());
        mTvTitle.setText(item.getDetail_name());
        mTvDate.setText(item.getAdd_time());
        mTvName.setText("发帖人："+item.getNick_name());
        mTvDetail.setText(item.getContent());
        mTvCollect.setText(item.getCollection()+"人收藏");
        CommonFunction.showImage(mContext,item.getFirst_pic(),mIv);

        mTvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.conllection(item.getDetail_id()+"");

            }
        });

    }

    @Override
    public void conllection(CollectionBean bean) {
        Toast.makeText(mContext, "收藏"+bean.getMsg(), Toast.LENGTH_SHORT).show();
        initView();
    }
}
