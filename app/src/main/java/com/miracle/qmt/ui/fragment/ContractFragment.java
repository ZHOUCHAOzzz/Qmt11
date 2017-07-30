package com.miracle.qmt.ui.fragment;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseFragment;
import com.miracle.qmt.base.BaseListFragment;
import com.miracle.qmt.base.BasePresenter;
import com.miracle.qmt.base.BaseViewHolder;
import com.miracle.qmt.network.ApiFactory;
import com.miracle.qmt.network.MyResult;
import com.miracle.qmt.ui.model.ContractItem;
import com.miracle.qmt.ui.view.viewHolder.ContractsVH;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Administrator on 2017/7/25.
 * 通讯录
 */
public class ContractFragment extends BaseListFragment<BasePresenter,ContractItem,ArrayList<ContractItem>> {

    public static ContractFragment mFragment;
    public static ContractFragment newInstance(){
        mFragment = new ContractFragment();
        return  mFragment;
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("通讯录");
    }

    @Override
    public int getLayoutType() {
        return BaseListFragment.LAYOUT_TYPE_LINEAR;
    }

    @Override
    public Class<? extends BaseViewHolder<ContractItem>> getViewHolder() {
        return ContractsVH.class;
    }

    @Override
    public Observable<MyResult<ArrayList<ContractItem>>> getObservble() {
        return ApiFactory.getXynSingleton().contract("");
    }

    @Override
    public void onReloadSuccess(ArrayList<ContractItem> response) {

    }

    @Override
    public void onNextPageSuccess(ArrayList<ContractItem> response) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_contract;
    }

    @Override
    public void onNetErrorClick() {

    }
}
