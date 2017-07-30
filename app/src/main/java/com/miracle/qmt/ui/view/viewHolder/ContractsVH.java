package com.miracle.qmt.ui.view.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseViewHolder;
import com.miracle.qmt.ui.model.ContractItem;
import com.miracle.qmt.ui.view.ImageTextView;

/**
 * Created by Administrator on 2017/7/30.
 */

public class ContractsVH extends BaseViewHolder<ContractItem> {
    TextView mTv;
    ImageTextView mIv;
    public ContractsVH(View itemView) {
        super(itemView);
        mTv = (TextView) itemView.findViewById(R.id.tv);
        mIv = (ImageTextView) itemView.findViewById(R.id.iv);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_contract;
    }

    @Override
    public void onBindViewHolder(View view, int position, ContractItem item) {

    }
}
