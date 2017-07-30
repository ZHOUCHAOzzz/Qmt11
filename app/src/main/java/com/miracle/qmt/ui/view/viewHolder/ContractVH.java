package com.miracle.qmt.ui.view.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseViewHolder;
import com.miracle.qmt.ui.view.ImageTextView;
import com.miracle.qmt.util.pinyin.SortModel;


/**
 * Created by Administrator on 2017/7/22.
 */

public class ContractVH extends BaseViewHolder<SortModel> {
    TextView mTv;
    ImageTextView mIv;
    public ContractVH(View itemView) {
        super(itemView);
        mTv = (TextView) itemView.findViewById(R.id.tv);
        mIv = (ImageTextView) itemView.findViewById(R.id.iv);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_contract;
    }

    @Override
    public void onBindViewHolder(View view, int position, SortModel item) {
        mTv.setText(item.getName());
        mIv.setIconText(mContext,item.getName());
    }
}
