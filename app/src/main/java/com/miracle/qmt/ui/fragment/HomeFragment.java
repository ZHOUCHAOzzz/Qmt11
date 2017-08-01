package com.miracle.qmt.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseFragment;
import com.miracle.qmt.base.BaseWebActivity;
import com.miracle.qmt.ui.activity.CommNewsListActivity;
import com.miracle.qmt.ui.activity.DisclaimerActivity;
import com.miracle.qmt.ui.activity.NewsDetailActivity;
import com.miracle.qmt.ui.activity.SearchListActivity;
import com.miracle.qmt.ui.activity.ShareActivity;
import com.miracle.qmt.ui.contract.HomeContract;
import com.miracle.qmt.ui.model.Carousel;
import com.miracle.qmt.ui.model.HomeNewsItem;
import com.miracle.qmt.ui.presenter.HomePresenter;
import com.miracle.qmt.util.ConstantKey;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/7/25.
 * 首页
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @Bind(R.id.roll_view_pager)
    RollPagerView mRollViewPager;
    @Bind(R.id.tv_1)
    TextView mTv1;
    @Bind(R.id.tv_2)
    TextView mTv2;
    @Bind(R.id.tv_3)
    TextView mTv3;
    @Bind(R.id.tv_4)
    TextView mTv4;
    @Bind(R.id.tv_5)
    TextView mTv5;
    @Bind(R.id.tv_6)
    TextView mTv6;
    @Bind(R.id.tv_7)
    TextView mTv7;
    @Bind(R.id.tv_8)
    TextView mTv8;
    @Bind(R.id.tv_9)
    TextView mTv9;
    @Bind(R.id.tv_10)
    TextView mTv10;
    @Bind(R.id.tv_11)
    TextView mTv11;
    @Bind(R.id.tv_12)
    TextView mTv12;
    @Bind(R.id.viewflipper)
    ViewFlipper mViewFlipper;
    @Bind(R.id.tv_title_left)
    TextView mTvLeft;
    @Bind(R.id.tv_title_right)
    TextView mTvRight;
    @Bind(R.id.ll_city)
    LinearLayout mLLCity;
    @Bind(R.id.ll_search)
    LinearLayout mLLSearch;

    public static HomeFragment mFragment;
    public static HomeFragment newInstance(){
        mFragment = new HomeFragment();
        return  mFragment;
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("全苗通");
        mTv1.setOnClickListener(this);
        mTv2.setOnClickListener(this);
        mTv3.setOnClickListener(this);
        mTv4.setOnClickListener(this);
        mTv5.setOnClickListener(this);
        mTv6.setOnClickListener(this);
        mTv7.setOnClickListener(this);
        mTv8.setOnClickListener(this);
        mTv9.setOnClickListener(this);
        mTv10.setOnClickListener(this);
        mTv11.setOnClickListener(this);
        mTv12.setOnClickListener(this);
        mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
        mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right));
        mLLCity.setOnClickListener(this);
//        onNewsSucc(null);
        mViewFlipper.setFlipInterval(5000);
        mTvLeft.setText("城市");
        mTvRight.setText("搜索");
        mTvRight.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onNetErrorClick() {

    }

    @Override
    public void onBannerSucc(ArrayList<Carousel> response) {
        setBanner(response);
    }

    @Override
    public void onNewsSucc(ArrayList<HomeNewsItem> response) {
        if(response != null && response.size()>0)
        mViewFlipper.removeAllViews();
//        for (int i = 0; i < 5; i++) {
        for (int i = 0; i < response.size(); i++) {
            View view = View.inflate(mContext,R.layout.view_home_news,null);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            tv.setText(response.get(i).getNews_content());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mViewFlipper.addView(view);
        }
        mViewFlipper.startFlipping();
    }

    //轮播数据填充
    private void setBanner(ArrayList<Carousel> list) {
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(2000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter(list));
        mRollViewPager.setHintView(new ColorPointHintView(mContext, Color.WHITE, Color.GRAY));
    }

    private class TestNormalAdapter extends StaticPagerAdapter {
        ArrayList<Carousel> list;

        public TestNormalAdapter(ArrayList<Carousel> list) {
            this.list = list;
        }

        @Override
        public View getView(ViewGroup container, final int position) {
            ImageView view = new ImageView(container.getContext());

            Glide.with(mContext).load(list.get(position).getPic()).thumbnail(0.4f).into((view));
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(mContext, BaseWebActivity.class);
//                    intent.putExtra(BaseWebActivity.URL,list.get(position).getCarousel_url());
//                    showActivity(intent);
                }
            });
            return view;
        }


        @Override
        public int getCount() {
            return list.size();
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        String type = "0";
        String text = "";
        Intent intent = new Intent(mContext,CommNewsListActivity.class);
        switch (view.getId()){
            case R.id.tv_1:
                type = "1";
                text = "资讯";
                intent.putExtra(ConstantKey.STRING_ITEM,type);
                intent.putExtra(ConstantKey.STRING_ITEM2,text);
                showActivity(intent);
                break;
            case R.id.tv_2:
                type = "2";
                text = "展会会议";
                intent.putExtra(ConstantKey.STRING_ITEM,type);
                intent.putExtra(ConstantKey.STRING_ITEM2,text);
                showActivity(intent);
                break;
            case R.id.tv_3:
                type = "3";
                text = "植保";
                intent.putExtra(ConstantKey.STRING_ITEM,type);
                intent.putExtra(ConstantKey.STRING_ITEM2,text);
                showActivity(intent);
                break;
            case R.id.tv_4:
                type = "4";
                text = "技术";
                intent.putExtra(ConstantKey.STRING_ITEM,type);
                intent.putExtra(ConstantKey.STRING_ITEM2,text);
                showActivity(intent);
                break;
            case R.id.tv_5:
                type = "5";
                text = "名特新优";
                intent.putExtra(ConstantKey.STRING_ITEM,type);
                intent.putExtra(ConstantKey.STRING_ITEM2,text);
                showActivity(intent);
                break;
            case R.id.tv_6:
                type = "6";
                text = "器材机械";
                intent.putExtra(ConstantKey.STRING_ITEM,type);
                intent.putExtra(ConstantKey.STRING_ITEM2,text);
                showActivity(intent);
                break;
            case R.id.tv_7:
                type = "7";
                text = "名家说苗";
                intent.putExtra(ConstantKey.STRING_ITEM,type);
                intent.putExtra(ConstantKey.STRING_ITEM2,text);
                showActivity(intent);
                break;
            case R.id.tv_8:
                type = "8";
                text = "大苗商";
                intent.putExtra(ConstantKey.STRING_ITEM,type);
                intent.putExtra(ConstantKey.STRING_ITEM2,text);
                showActivity(intent);
                break;

        }


        switch (view.getId()){
            case R.id.tv_9:
                break;
            case R.id.tv_10:
                Intent shareIntent = new Intent(mContext,ShareActivity.class);
                showActivity(shareIntent);
                break;
            case R.id.tv_11:
                Intent detailItent = new Intent(mContext,DisclaimerActivity.class);
                detailItent.putExtra(ConstantKey.STRING_ITEM,"详细说明");
                detailItent.putExtra(ConstantKey.STRING_ITEM2,R.string.mzsm);
                showActivity(detailItent);
                break;
            case R.id.tv_12:
                Intent infoItent = new Intent(mContext,DisclaimerActivity.class);
                infoItent.putExtra(ConstantKey.STRING_ITEM,"免责声明");
                infoItent.putExtra(ConstantKey.STRING_ITEM2,R.string.mzsm);
                showActivity(infoItent);
                break;
            case R.id.ll_city:
//                showActivity();
                break;
            case R.id.tv_title_right:
                showActivity(new Intent(mContext,SearchListActivity.class));
                break;
        }
    }
}
