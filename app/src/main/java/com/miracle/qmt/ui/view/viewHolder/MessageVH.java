package com.miracle.qmt.ui.view.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseViewHolder;
import com.miracle.qmt.ui.model.MessageItem;

/**
 * Created by Administrator on 2017/7/31.
 */
public class MessageVH extends BaseViewHolder<MessageItem> {
    TextView mTvTitle,mTvContent,mTvDate;

    public MessageVH(View itemView) {
        super(itemView);
        mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        mTvDate = (TextView) itemView.findViewById(R.id.tv_date);
        mTvContent = (TextView) itemView.findViewById(R.id.tv_content);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_message;
    }

    @Override
    public void onBindViewHolder(View view, int position, MessageItem item) {

    }
}
