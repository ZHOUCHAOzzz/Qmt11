package com.miracle.qmt.ui.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.ui.model.MessageBean;

import java.util.List;


/**
 * Created by wangjian on 2017/8/25.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder>implements View.OnClickListener{
    private static Context con;

    private  List<MessageBean.DataBean> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public MessageAdapter(Context con, List<MessageBean.DataBean> list) {
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
                con).inflate(R.layout.item_message, parent,
                false);
        //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_item_child,null);
        MyViewHolder holder = new MyViewHolder(view);

        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        /*RequestOptions options = new RequestOptions()
                .centerCrop()
                ;
        Glide.with(con)
                .load(list.get(position))
                .apply(options)
                .into(holder.imageView);*/
        holder.itemView.setTag(position);
        holder.title.setText("全面通");
        holder.tv_content.setText(list.get(position).getNews_content());
        holder.tv_date.setText(list.get(position).getAdd_time());
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

        private TextView title;
        private TextView tv_content;
        private TextView tv_date;

        public MyViewHolder(View view) {
            super(view);
            title= (TextView) view.findViewById(R.id.tv_title);
            tv_content= (TextView) view.findViewById(R.id.tv_content);
            tv_date= (TextView) view.findViewById(R.id.tv_date);

        }
    }


}
