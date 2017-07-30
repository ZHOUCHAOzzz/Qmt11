package com.miracle.qmt.ui.view.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseViewHolder;
import com.miracle.qmt.ui.model.HomeCate;
import com.miracle.qmt.util.CommonFunction;

/**
 * Created by Administrator on 2017/7/29.
 */
public class HomeInfoVH extends BaseViewHolder<HomeCate> {
    TextView mTv;
    ImageView mIv;

    public HomeInfoVH(View itemView) {
        super(itemView);
        mTv = (TextView) itemView.findViewById(R.id.tv);
        mIv = (ImageView) itemView.findViewById(R.id.iv);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_home_info;
    }

    @Override
    public void onBindViewHolder(View view, int position, HomeCate item) {
        mTv.setText(item.getInformation_name());
        CommonFunction.showImage(mContext, item.getInformation_pic(), mIv);
    }
}
