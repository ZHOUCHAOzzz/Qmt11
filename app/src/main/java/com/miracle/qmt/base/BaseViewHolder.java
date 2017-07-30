package com.miracle.qmt.base;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by WJQ on 2016/8/11.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public Context mContext;


    public BaseViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
    }

    public abstract int getLayoutId();

    public abstract void onBindViewHolder(View view, int position, T item);

    public void onBindViewHolderWithLisen(View view, int position, T item, IBaseAdapter.ClickListener lisener){}


}
