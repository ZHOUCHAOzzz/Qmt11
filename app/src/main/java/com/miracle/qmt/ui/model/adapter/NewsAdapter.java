package com.miracle.qmt.ui.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.miracle.qmt.R;

import java.util.List;




/**
 * Created by wangjian on 2017/8/25.
 */

public class NewsAdapter extends  RecyclerView.Adapter<NewsAdapter.MyViewHolder>implements View.OnClickListener{
    private static Context con;

    private List<String> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public NewsAdapter(Context con, List<String> list) {
        this.con = con;
        this.list = list;

    }
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int pos);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       /* View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wait_audit,null);
        MyViewHolder holder = new MyViewHolder(view);*/
        View view = LayoutInflater.from(
                con).inflate(R.layout.item_rvpics, parent,
                false);
        //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_item_child,null);
        MyViewHolder holder = new MyViewHolder(view);

        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                ;
        Glide.with(con)
                .load(list.get(position))
                .apply(options)
                .into(holder.imageView);
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(Integer) v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            imageView= (ImageView) view.findViewById(R.id.iv_pics);

        }
    }


}
