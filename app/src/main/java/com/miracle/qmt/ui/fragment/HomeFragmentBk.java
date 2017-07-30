package com.miracle.qmt.ui.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseListFragment;
import com.miracle.qmt.base.BaseViewHolder;
import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyResult;
import com.miracle.qmt.ui.contract.HomeContract;
import com.miracle.qmt.ui.model.Carousel;
import com.miracle.qmt.ui.model.HomeCate;
import com.miracle.qmt.ui.presenter.HomePresenter;
import com.miracle.qmt.ui.view.viewHolder.HomeInfoVH;

import java.util.ArrayList;

import butterknife.Bind;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/25.
 * 首页
 */
public class HomeFragmentBk extends BaseListFragment<HomePresenter, HomeCate, ArrayList<HomeCate>> implements HomeContract.View {

    @Bind(R.id.roll_view_pager)
    RollPagerView mRollViewPager;

    public static HomeFragmentBk mFragment;

    public static HomeFragmentBk newInstance() {
        mFragment = new HomeFragmentBk();
        return mFragment;
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("首页");
    }

    @Override
    public int getLayoutType() {
        return BaseListFragment.LAYOUT_TYPE_GRID;
    }

    @Override
    public int getSpanCount() {
        return 4;
    }

    @Override
    public Class<? extends BaseViewHolder<HomeCate>> getViewHolder() {
        return HomeInfoVH.class;
    }

    @Override
    public Observable<MyResult<ArrayList<HomeCate>>> getObservble() {
        return ApiFactory.getXynSingleton().information();
    }

    @Override
    public void onReloadSuccess(ArrayList<HomeCate> response) {
        dealData(response);
    }

    @Override
    public void onNextPageSuccess(ArrayList<HomeCate> response) {

    }


    @Override
    public Boolean setRefreshEnable() {
        return false;
    }

    @Override
    public Boolean setLoadMoreEnable() {
        return false;
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
}
