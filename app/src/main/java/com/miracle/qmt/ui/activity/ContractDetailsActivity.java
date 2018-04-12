package com.miracle.qmt.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.ContractDetailContract;
import com.miracle.qmt.ui.model.CollectionBean;
import com.miracle.qmt.ui.model.ConDetailsBean;
import com.miracle.qmt.ui.model.adapter.NewsAdapter;
import com.miracle.qmt.ui.presenter.ContractDetailsPresenter;
import com.miracle.qmt.util.ConstantValue;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Administrator on 2017/9/10.
 */

public class ContractDetailsActivity extends BaseActivity<ContractDetailsPresenter> implements ContractDetailContract.View {

    @Bind(R.id.iv_head)
    CircleImageView ivHead;
    @Bind(R.id.tv_nikename)
    TextView tvNikename;
    @Bind(R.id.tv_company)
    TextView tvCompany;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_manage)
    TextView tvManage;
    @Bind(R.id.tv_buy)
    TextView tvBuy;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.rv_pics)
    RecyclerView rvPics;
    @Bind(R.id.shoucang)
    LinearLayout  rl_shoucang;
    @Bind(R.id.tv_collect)
    TextView tvCollect;

    NewsAdapter adapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_contractdetails;
    }

    @Override
    protected void onNetErrorClick() {

    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("个人详情");
        //   ninePhotoLayout = (BGANinePhotoLayout) findViewById(R.id.npl_item_moment_photos);
        tvManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ContractDetailsActivity.this,TextActivity.class);
                intent.putExtra("manage",tvManage.getText().toString());
                startActivity(intent);
            }
        });



    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        String userid = intent.getStringExtra("userid");
        Log.d("userid",userid);
        mPresenter.getData(userid);
    }

    @Override
    public void getContractSucc(final ConDetailsBean bean) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.icon_headpic)
                .error(R.drawable.icon_headpic);

        Glide.with(this)
                .load(bean.getInfo().getHead_pic())
                .thumbnail(0.4f)
                .apply(options)
                .into(ivHead);
        //   CommonFunction.showImage(mContext, ConstantValue.BASE_URL + bean.getInfo().getHead_pic(), ivHead, R.drawable.icon_headpic);
        tvNikename.setText(bean.getInfo().getNick_name());
        tvCompany.setText(bean.getInfo().getCompany_name());
        tvAddress.setText(bean.getInfo().getAddress());
        tvManage.setText(bean.getInfo().getManagement());
        tvBuy.setText(bean.getInfo().getBuy());
        tvPhone.setText(bean.getInfo().getPhone());
        rl_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.collection(bean.getInfo().getUser_id()+"");
            }
        });

        final ArrayList<String> imgs = new ArrayList<>();

        String photos = bean.getInfo().getPic();
        String[] strs = photos.split(",");
        if (strs.length == 1) {
            imgs.add(ConstantValue.BASE_URL + strs[0].toString());
        } else {
            for (int i = 0, len = strs.length; i < len; i++) {
                imgs.add(ConstantValue.BASE_URL + strs[i].toString());

            }
        }
        for (int i = 0; i < imgs.size(); i++) {
            Log.d("ContractDetailsActivity", imgs.get(i));
        }

        adapter = new NewsAdapter(ContractDetailsActivity.this, imgs);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvPics.setLayoutManager(linearLayoutManager);
        rvPics.setAdapter(adapter);
        adapter.setOnItemClickListener(new NewsAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                //使用方式
                Intent intent = new Intent(mContext, ImagePagerActivity.class);
                intent.putStringArrayListExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, imgs);
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, pos);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public void collection(CollectionBean bean) {
        Toast.makeText(mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
        tvCollect.setText("已收藏");

    }


    @OnClick(R.id.tv_phone)
    public void onViewClicked() {
        if (tvPhone.getText().toString()!=null){
            Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+ tvPhone.getText().toString()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
    @OnClick(R.id.shoucang)
    public void shouchang() {


    }
}
