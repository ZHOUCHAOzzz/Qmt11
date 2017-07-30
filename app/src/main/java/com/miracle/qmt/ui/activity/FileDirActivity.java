package com.miracle.qmt.ui.activity;


import com.miracle.qmt.base.BaseListActivity;
import com.miracle.qmt.base.BaseViewHolder;
import com.miracle.qmt.network.MyResult;
import com.miracle.qmt.ui.contract.FileDirContract;
import com.miracle.qmt.ui.model.FileBean;
import com.miracle.qmt.ui.presenter.FileDirPresenter;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Administrator on 2017/7/23.
 * 云文件列表
 */
public class FileDirActivity extends BaseListActivity<FileDirPresenter,FileBean,ArrayList<FileBean>> implements FileDirContract.View{
    @Override
    public int getLayoutType() {
        return FileDirActivity.LAYOUT_TYPE_LINEAR;
    }

    @Override
    public Class<? extends BaseViewHolder<FileBean>> getViewHolder() {
        return null;
    }

    @Override
    public Observable<MyResult<ArrayList<FileBean>>> getObservble() {
        return null;
    }

    @Override
    public void onReloadSuccess(ArrayList<FileBean> response) {

    }

    @Override
    public void onNextPageSuccess(ArrayList<FileBean> response) {

    }

}
