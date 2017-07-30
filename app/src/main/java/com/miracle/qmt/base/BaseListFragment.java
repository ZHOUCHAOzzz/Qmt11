package com.miracle.qmt.base;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.miracle.qmt.R;
import com.miracle.qmt.network.MyObserver;
import com.miracle.qmt.network.MyResult;
import com.miracle.qmt.util.DividerGridItemDecoration;
import com.miracle.qmt.util.RecyclerItemClickListener;


import java.util.ArrayList;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WJQ on 2016/8/25.
 * T 是Presenter 默认写BasePresenter
 * O 是ArrayList的泛型
 * M 是Gson解析的类型
 */
public abstract class BaseListFragment<T extends BasePresenter, O, M> extends BaseFragment<T> implements XRecyclerView.LoadingListener {
    public static final int LAYOUT_TYPE_LINEAR = 1;
    public static final int LAYOUT_TYPE_GRID = 2;
    public static final int LIST_PERPAGE_NUM = 10;

    public ArrayList<O> mArrayList;
    public XRecyclerView mXRcv;
    public BaseAdapter<O> mAdapter;
    public LinearLayoutManager mLlManager;
    public GridLayoutManager mGridManager;
    public int mPage = 1;
    public Boolean isNextPage = false;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_baselist;
    }

    @Override
    public void onNetErrorClick() {
        requestData();
    }

    @Override
    public void initView() {
        mArrayList = new ArrayList<>();
        super.initView();
        initRcv();
        setAdapter();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        showLoading();
////        requestData();
//        reloadData();
//    }

    @Override
    public void initData() {
        super.initData();
        showLoading();
        requestData();
    }

    public void initRcv() {
        mXRcv = (XRecyclerView) findViewById(R.id.xrcv);
        mXRcv.setRefreshProgressStyle(ProgressStyle.BallGridPulse);
        mXRcv.setLaodingMoreProgressStyle(ProgressStyle.BallGridPulse);
        mXRcv.setLoadingListener(this);
        mXRcv.setLoadingMoreEnabled(setLoadMoreEnable());
        mXRcv.setPullRefreshEnabled(setRefreshEnable());

        mXRcv.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                position = (position > 0) ? position - 1 : 0;
//                if (position >= mArrayList.size()){
//                    onItemClickEvent(view, null, position,mArrayList);
//                }else{
//                    onItemClickEvent(view, mArrayList.get(position), position,mArrayList);
//                }
                position = (position > 0) ? position - 1 : 0;
                if (position >= mArrayList.size()) {
                    onItemClickEvent(view, null, position, mArrayList);
                    onItemClickEvent(view, null, position);
                } else {
                    onItemClickEvent(view, mArrayList.get(position), position, mArrayList);
                    onItemClickEvent(view, mArrayList.get(position), position);
                }

            }
        }));

        if (getLayoutType() == LAYOUT_TYPE_LINEAR) {
            mLlManager = new LinearLayoutManager(mContext, LinearLayout.VERTICAL, false);
            mXRcv.setLayoutManager(mLlManager);
        } else if (getLayoutType() == LAYOUT_TYPE_GRID) {
            mXRcv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
            DividerGridItemDecoration itemDecoration = new DividerGridItemDecoration(mContext);
            mXRcv.addItemDecoration(itemDecoration);
            mGridManager = new GridLayoutManager(mContext, getSpanCount());
            mGridManager.setAutoMeasureEnabled(true);
            mGridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == 0 || position == mArrayList.size()) {
                        return getSpanCount();
                    } else {
                        return 1;
                    }
                }
            });
            mXRcv.setLayoutManager(mGridManager);
        }
    }

    public void setAdapter() {
        mAdapter = new BaseAdapter<>(mArrayList, mContext);
        mAdapter.setViewHolder(getViewHolder());
        mXRcv.setAdapter(mAdapter);
    }

    public void onItemClickEvent(View view, O item, int position, ArrayList<O> list) {

    }

    public void onItemClickEvent(View view, O item, int position) {

    }


    public Boolean setLoadMoreEnable() {
        return true;
    }

    public Boolean setRefreshEnable() {
        return true;
    }

    public int getSpanCount() {
        return 2;
    }

    public abstract int getLayoutType();//布局类型

    public abstract Class<? extends BaseViewHolder<O>> getViewHolder();//Viewholder类

    public abstract Observable<MyResult<M>> getObservble();

    public void requestData() {
        if(getObservble() == null)
            return;
        Subscription mSubscription;
        mSubscription = getObservble().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserver<M>(null) {
                    @Override
                    public void onSuccess(M response) {
                        super.onSuccess(response);
                        onStateSuccess();
                        if (isNextPage) {
                            onNextPageSuccess(response);
                        } else {//若没有数据，这块需要自己去处理一下
                            onReloadSuccess(response);
                            onStateFail();
                        }
                    }

                    @Override
                    public void onFail(String msg) {
                        super.onFail(msg);
                        onStateFail();
                        mXRcv.refreshComplete();
                        mXRcv.noMoreLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        onStateNetError();
                    }
                });
        addSubscription(mSubscription);
    }

    public void reloadData() {
//        mArrayList.clear();
        isNextPage = false;
        mPage = 1;
        requestData();
    }

    public abstract void onReloadSuccess(M response);

    public abstract void onNextPageSuccess(M response);

    public void dealData(ArrayList<O> list) {
        mXRcv.refreshComplete();
        if (list != null) {
            mArrayList.clear();
            mArrayList.addAll(list);
        }
        mAdapter.notifyDataSetChanged();

    }

    public void dealNextPage(ArrayList<O> list) {
        mXRcv.loadMoreComplete();
        if (list != null && list.size() > 0) {
            mArrayList.addAll(list);
            mAdapter.notifyDataSetChanged();
        } else {
            mXRcv.noMoreLoading();
        }
    }

    @Override
    public void onRefresh() {
        //刷新
        reloadData();
    }

    @Override
    public void onLoadMore() {
        //加载更多
        mPage++;
        isNextPage = true;
        requestData();
    }

}
