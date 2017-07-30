package com.miracle.qmt.ui.view.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseViewHolder;
import com.miracle.qmt.ui.model.NewsItem;
import com.miracle.qmt.util.CommonFunction;

/**
 * Created by Administrator on 2017/7/26.
 */
public class CommonNewsVH extends BaseViewHolder<NewsItem> {
    ImageView mIV;
    TextView mTvTitle,mTvDate;
    public CommonNewsVH(View itemView) {
        super(itemView);
        mIV = (ImageView) itemView.findViewById(R.id.iv);
        mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        mTvDate = (TextView) itemView.findViewById(R.id.tv_date);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_common_news;
    }

    @Override
    public void onBindViewHolder(View view, int position, NewsItem item) {
        CommonFunction.showImage(mContext,item.getFirst_pic(),mIV);
        mTvTitle.setText(item.getDetail_name());
        mTvDate.setText(item.getAdd_time());
    }
}
