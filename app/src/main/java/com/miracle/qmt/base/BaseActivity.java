package com.miracle.qmt.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
 * Activity基类
 *
 * @param <T> Presenter
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IActivity, View.OnClickListener, BaseView {
    //上下文
    public Context mContext;
    //
    public T mPresenter;
    //三种Dialog
    public SweetAlertDialog mSweetSuccDialog;
    public SweetAlertDialog mSweetErrorDialog;
    public SweetAlertDialog mSweetLoadingDialog;
    //网络加载相关
    public FrameLayout mFrameLoading;
    public ImageView mIVNoData;
    public FrameLayout mFrameNoData;
    public FrameLayout mFrameReload;
    //标题
    public TextView mTvTitle;//主标题
    public ImageView mIvLeft;//左侧按钮
    public ImageView mIvRight;//右侧按钮
    public TextView mTvRight;//右侧按钮

    Fragment mPreFragment = null;//上一个Fragment
    Fragment mCurrFragment = null;//当前Fragment

    /**
     * 布局ID
     *
     * @return 布局id
     */
    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getLayoutId() !=0)
        setContentView(getLayoutId());
        mContext = this;
        ButterKnife.bind(this);
        mPresenter = TUtil.getT(this, 0);
        if (this instanceof BaseView && mPresenter != null) mPresenter.setView(this,mContext);
        initView();
        initData();
    }

    @Override
    public void initView() {
        mIvLeft = (ImageView) findViewById(R.id.iv_title_left);
//        mIvRight = (ImageView) findViewById(R.id.iv_title_right);
        mTvTitle = (TextView) findViewById(R.id.title_txt);
        mTvRight = (TextView) findViewById(R.id.tv_title_right);
        if (mTvRight != null)
            mTvRight.setOnClickListener(this);
        if (mIvLeft != null) {
            mIvLeft.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.icon_left));
            mIvLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
        mFrameLoading = (FrameLayout) findViewById(R.id.frame_progress);
        mFrameNoData = (FrameLayout) findViewById(R.id.frame_no_data);
        mIVNoData = (ImageView) findViewById(R.id.iv_no_data);
        mFrameReload = (FrameLayout) findViewById(R.id.frame_reload);
        if (mFrameReload != null) {
            mFrameReload.setOnClickListener(this);
        }
    }

    @Override
    public void initData() {

    }

    //页面跳转-添加动画
    public void showActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    //页面跳转-添加动画
    public void showActivityForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (mPresenter != null) mPresenter.onDestroy();//取消网络连接
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onNetError() {
        hideLoading();
        if (mSweetLoadingDialog != null) {
            mSweetLoadingDialog.dismiss();
        }
    }

    @Override
    public void onComplete() {
        hideLoading();
        if (mSweetLoadingDialog != null) {
            mSweetLoadingDialog.dismiss();
        }
    }

    @Override
    public void showMsg(String msg) {
        com.miracle.qmt.util.T.showShort(mContext,msg);
    }

    //单击重试
    protected abstract void onNetErrorClick();

    public void switchFragment(int containerId, Fragment fragment, Boolean addToStack) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(containerId, fragment, fragment.getTag());
        if (addToStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void popuFragment() {

        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
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
//        hideNoData();
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

    /**
     * @param containerId FrameLayoutId
     * @param fragment
     */
    public void showOrHideFragment(int containerId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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
    CompositeSubscription mCompositeSubscription;
    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }

    public void addSubscription(Subscription s) {
        getCompositeSubscription().add(s);
    }


}
