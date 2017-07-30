package com.miracle.qmt.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by WJQ on 2016/9/20.
 */
public class IBaseAdapter<T> extends BaseAdapter<T>{

    ClickListener mListener;

    public IBaseAdapter(ArrayList<T> list, Context context) {
        super(list, context);
    }

    public void setListener(ClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).onBindViewHolderWithLisen(holder.itemView, position,list.get(position),mListener);
    }

    public interface ClickListener<T>{
        void iClick(int i, T t);
        void iItemClick(int i, T t);
    }
}
