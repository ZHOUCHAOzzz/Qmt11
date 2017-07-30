package com.miracle.qmt.base;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by WJQ on 2016/8/11.
 */
public class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public ArrayList<T> list;
    private Context context;
    private int mViewHolderID;//item layoutid
    private Class<? extends BaseViewHolder> mItemViewClass;//item class
    private BaseViewHolder mIVH;

    public BaseAdapter(ArrayList<T> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setViewHolder(Class<? extends BaseViewHolder> cls) {
        try {
            mItemViewClass = cls;
            mIVH = ((BaseViewHolder) (cls.getConstructor(View.class).newInstance(new LinearLayout(context))));
            mViewHolderID = mIVH.getLayoutId();
            Logger.d("mViewHolderID is "+mViewHolderID);
        } catch (Exception e) {
            Logger.d("Exceptions is " + e);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            RecyclerView.ViewHolder holder = mItemViewClass.getConstructor(View.class).newInstance(
                    LayoutInflater.from(parent.getContext()).inflate(mViewHolderID, parent, false));
            return holder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Logger.d("position is " + position);
        if (list != null && list.size() > 0)
            ((BaseViewHolder) holder).onBindViewHolder(holder.itemView, position, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
