package com.miracle.qmt.base;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;


import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import com.miracle.qmt.R;
import com.miracle.qmt.network.MyObserver;
import com.miracle.qmt.network.MyResult;
import com.miracle.qmt.util.RecyclerItemClickListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WJQ on 2016/8/25.
 * O 是Presenter 默认写BasePresenter
 *
 * @T BaseModel也就是bean
 * @M 是HttpModel
 */
public abstract class BaseListActivity<O extends BasePresenter, T, M> extends BaseActivity<O> implements XRecyclerView.LoadingListener {
    public static final String TAG = "BaseListActivity";

    public static final int LAYOUT_TYPE_LINEAR = 1;
    public static final int LAYOUT_TYPE_GRID = 2;
    public static final int LIST_PERPAGE_NUM = 10;

    public ArrayList<T> mArrayList;
    //    @Bind(R.id.xrcv)
    public XRecyclerView mXRcv;
    public BaseAdapter<T> mAdapter;
    public LinearLayoutManager mLlManager;
    public GridLayoutManager mGridManager;
    public int mPage = 1;
    public Boolean isNextPage = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_baselist;
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

    @Override
    public void initData() {
        super.initData();
        showLoading();
        requestData();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        showLoading();
//        reloadData();
    }

    public void initRcv() {
        mXRcv = (XRecyclerView) findViewById(R.id.xrcv);
        mXRcv.setRefreshProgressStyle(ProgressStyle.BallGridPulse);
        mXRcv.setLaodingMoreProgressStyle(ProgressStyle.BallGridPulse);
        mXRcv.setLoadingListener(this);
        mXRcv.setLoadingMoreEnabled(setLoadMoreEnable());
        mXRcv.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                position = (position > 0) ? position - 1 : 0;
                if (position >= mArrayList.size()) {
                    onItemClickEvent(view, null, position, mArrayList);
                    onItemClickEvent(view, null, position);
                } else {
                    onItemClickEvent(view, mArrayList.get(position), position, mArrayList);
                    onItemClickEvent(view, mArrayList.get(position), position);
                }
//                onItemClickEvent(view, mArrayList.get(position), position);
            }
        }));

        if (getLayoutType() == LAYOUT_TYPE_LINEAR) {
            mLlManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
            mXRcv.setLayoutManager(mLlManager);
        } else if (getLayoutType() == LAYOUT_TYPE_GRID) {
            mGridManager = new GridLayoutManager(this, getSpanCount());
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
        mAdapter = new BaseAdapter<>(mArrayList, this);
        mAdapter.setViewHolder(getViewHolder());
        mXRcv.setAdapter(mAdapter);
    }

    public void onItemClickEvent(View view, T item, int position) {

    }

    public void onItemClickEvent(View view, T item, int position, ArrayList<T> list) {

    }

    public Boolean setLoadMoreEnable() {
        return true;
    }

    public int getSpanCount() {
        return 2;
    }

    public abstract int getLayoutType();//布局类型

    public abstract Class<? extends BaseViewHolder<T>> getViewHolder();//Viewholder类

//    public abstract void requestData();//请求数据

    public abstract Observable<MyResult<M>> getObservble();

    public void requestData() {
        if (getObservble() == null) {
            Logger.e("Obserrvble is null");
            return;
        }
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
                        } else {
                            onReloadSuccess(response);
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

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        onComplete();
                    }
                });
        addSubscription(mSubscription);
    }

    public void onComplete() {
        onStateSuccess();
        Logger.d("onComplete");
    }

    public void reloadData() {
//        mArrayList.clear();
        isNextPage = false;
        mPage = 1;
        requestData();
    }

    public abstract void onReloadSuccess(M response);

    public abstract void onNextPageSuccess(M response);

    public void dealData(ArrayList<T> list) {
        mXRcv.refreshComplete();
        if (list != null) {
            mArrayList.clear();
            mArrayList.addAll(list);
        }
        mAdapter.notifyDataSetChanged();

    }

    public void dealNextPage(ArrayList<T> list) {
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
