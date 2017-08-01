package com.miracle.qmt.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.miracle.qmt.R;
import com.miracle.qmt.util.TUtil;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by WJQ on 2016/8/18.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IActivity, View
        .OnClickListener, BaseView {
    public CompositeSubscription mCompositeSubscription;
    private View mContentView;//主布局
    LayoutInflater inflater;
    ViewGroup container;

    public T mPresenter;
    public Context mContext;

    //网络加载相关
    public FrameLayout mFrameLoading;
    public FrameLayout mFrameNoData;
    public ImageView mIVNoData;
    public FrameLayout mFrameReload;

    public SweetAlertDialog mSweetSuccDialog;
    public SweetAlertDialog mSweetErrorDialog;
    public SweetAlertDialog mSweetLoadingDialog;

    public TextView mTvTitle;//主标题
    public ImageView mIvLeft;//左侧按钮
    public ImageView mIvRight;//右侧按钮

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        mContentView = (ViewGroup) inflater.inflate(getLayoutId(), container, false);
        mContext = getActivity();
        ButterKnife.bind(this, mContentView);
        mPresenter = TUtil.getT(this, 0);
        initView();
        initData();
        if (mContentView == null)
            return super.onCreateView(inflater, container, savedInstanceState);
        if (this instanceof BaseView && mPresenter != null) {
            mPresenter.setView(this, mContext);
        }
        return mContentView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public abstract int getLayoutId();

    //数据重新加载
    public abstract void onNetErrorClick();

    public void initView() {
        mIvLeft = (ImageView) findViewById(R.id.iv_title_left);
        mIvRight = (ImageView) findViewById(R.id.iv_title_right);
        mTvTitle = (TextView) findViewById(R.id.title_txt);
        mFrameLoading = (FrameLayout) findViewById(R.id.frame_progress);
        mFrameNoData = (FrameLayout) findViewById(R.id.frame_no_data);
        mIVNoData = (ImageView) findViewById(R.id.iv_no_data);
        mFrameReload = (FrameLayout) findViewById(R.id.frame_reload);
        if (mFrameReload != null) {
            mFrameReload.setOnClickListener(this);
        }
        if (mIvLeft != null) {
            mIvLeft.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.icon_left));
            mIvLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().finish();
                }
            });
            mIvLeft.setVisibility(View.INVISIBLE);
        }
    }

    public void initData() {

    }

    //页面跳转-添加动画
    public void showActivity(Intent intent) {
        startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    //页面跳转-添加动画
    public void showActivityForResult(Context context, Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public View findViewById(int id) {
        if (mContentView != null)
            return mContentView.findViewById(id);
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frame_reload:
                onNetErrorClick();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);//解除ButterKnife
        if (mPresenter != null) mPresenter.onDestroy();//取消网络连接
        if (this.mCompositeSubscription != null) this.mCompositeSubscription.unsubscribe();
    }

    @Override
    public void onStateSuccess() {
        hideNetErr();
        hideNoData();
        hideLoading();
    }

    @Override
    public void onStateFail() {
        hideLoading();
        hideNetErr();
        showNoData();
    }

    @Override
    public void onStateNetError() {
        hideLoading();
        hideNoData();
//        showNetErr();
        showNoData();
    }

    @Override
    public void showLoading() {
        if (mFrameLoading != null) {
            mFrameLoading.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (mFrameLoading != null) {
            mFrameLoading.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNoData() {
        if (mFrameNoData != null) {
            mFrameNoData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideNoData() {
        if (mFrameNoData != null) {
            mFrameNoData.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNetErr() {
        if (mFrameReload != null) {
            mFrameReload.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideNetErr() {
        if (mFrameReload != null) {
            mFrameReload.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSuccDialog(String msg) {
        mSweetSuccDialog = new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE);
        mSweetSuccDialog.setTitleText(msg);
        mSweetSuccDialog.setConfirmText("确定");
        mSweetSuccDialog.show();
    }

    @Override
    public void showErrorDialog(String msg) {
        mSweetErrorDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE);
        mSweetErrorDialog.setTitleText("提示");
        mSweetErrorDialog.setContentText(msg);
        mSweetErrorDialog.setConfirmText("确定");
        mSweetErrorDialog.show();
    }

    @Override
    public void showProgressDialog(String msg) {
        mSweetLoadingDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        mSweetLoadingDialog.setTitleText(msg);
        mSweetLoadingDialog.setConfirmText("确定");
        mSweetLoadingDialog.show();
    }

    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }

    public void addSubscription(Subscription s) {
        getCompositeSubscription().add(s);
    }

    @Override
    public void onNetError() {

    }

    public void onUpLoadSucc(String imgs){

    }

    @Override
    public void onComplete() {
        if (mSweetLoadingDialog != null) {
            mSweetLoadingDialog.dismiss();
        }
    }

    @Override
    public void showMsg(String msg) {
        com.miracle.qmt.util.T.showShort(mContext, msg);
    }

    Fragment mPreFragment = null;//上一个Fragment
    Fragment mCurrFragment = null;//当前Fragment

    /**
     * @param containerId FrameLayoutId
     * @param fragment
     */
    public void showOrHideFragment(int containerId, Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        mPreFragment = mCurrFragment;
        mCurrFragment = fragment;
        if (fragment != null && fragment.isAdded()) {
            if (fragment.isHidden()) {
                transaction.show(fragment);
                transaction.commitAllowingStateLoss();
            }
        } else {
            transaction.add(containerId, fragment, fragment.getTag());
//            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        }
        if (mPreFragment != null) {
            transaction.hide(mPreFragment);
//            transaction.commit();
        }
    }

    public void switchFragment(int containerId, Fragment fragment, Boolean addToStack) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(containerId, fragment, fragment.getTag());
        if (addToStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
