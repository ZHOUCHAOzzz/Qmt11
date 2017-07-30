package com.miracle.qmt.ui.activity;

import android.annotation.SuppressLint;


import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseListActivity;
import com.miracle.qmt.base.BaseViewHolder;
import com.miracle.qmt.network.MyResult;
import com.miracle.qmt.ui.contract.ConContract;
import com.miracle.qmt.ui.presenter.ContractPresenter;
import com.miracle.qmt.ui.view.SideBar;
import com.miracle.qmt.ui.view.viewHolder.ContractVH;
import com.miracle.qmt.util.pinyin.SortModel;
import java.util.ArrayList;
import butterknife.Bind;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/22.
 * 本地通讯录
 */
public class ContractActivity extends BaseListActivity<ContractPresenter,SortModel,ArrayList<SortModel>> implements ConContract.View {
    @Override
    public int getLayoutId() {
        return R.layout.activity_contract;
    }

    @Override
    public int getLayoutType() {
        return BaseListActivity.LAYOUT_TYPE_LINEAR;
    }

    @Override
    public Class<? extends BaseViewHolder<SortModel>> getViewHolder() {
        return ContractVH.class;
    }

    @Override
    public Observable<MyResult<ArrayList<SortModel>>> getObservble() {
        return null;
    }

    @Override
    public void onReloadSuccess(ArrayList<SortModel> response) {

    }

    @Override
    public void onNextPageSuccess(ArrayList<SortModel> response) {

    }

    @Bind(R.id.sidebar)
    SideBar mSideBar;
    @Override
    public void initData() {
//        super.initData();
//        mSideBar.setTextView(dialog);
        mPresenter.getData();

        // 设置右侧触摸监听
        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @SuppressLint("NewApi")
            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
//                int position = adapter.getPositionForSection(s.charAt(0));
//                if (position != -1) {
//                    sortListView.setSelection(position);
//                }
            }
        });
//
//        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                String number = callRecords.get(((SortModel) adapter
//                        .getItem(position)).getName());
//                Toast.makeText(mContext, number, Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    @Override
    public void getContractSucc(ArrayList<SortModel> mList) {
        dealData(mList);
    }
}
