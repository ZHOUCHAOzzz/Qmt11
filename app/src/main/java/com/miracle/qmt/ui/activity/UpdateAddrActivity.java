package com.miracle.qmt.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.UpdateInfoContract;
import com.miracle.qmt.ui.presenter.UpdateInfoPresenter;
import com.miracle.qmt.util.PreferencesUtils;
import com.miracle.qmt.util.T;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/7/30.
 * 修改姓名
 */
public class UpdateAddrActivity extends BaseActivity<UpdateInfoPresenter> implements UpdateInfoContract.View,TextWatcher {
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_addr;
    }

    @Override
    protected void onNetErrorClick() {

    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("修改地址");
        tvTitleRight.setText("保存");
        tvTitleRight.setClickable(true);
        etName.addTextChangedListener(this);
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*UserManager.User user = new UserManager().new User();
                user.setAddress(etName.getText().toString().trim());*/
                PreferencesUtils.setPreferences(UpdateAddrActivity.this,PreferencesUtils.USER_ADDRESS,etName.getText().toString().trim());
                Intent mIntent = new Intent();
                mIntent.putExtra("address", etName.getText().toString().trim());
                // 设置结果，并进行传送
                setResult(104, mIntent);
                finish();
                /*showProgressDialog("加载中");
                mPresenter.updateInfo(user);*/
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(!TextUtils.isEmpty(s)){
            ivDelete.setVisibility(View.GONE);
            tvTitleRight.setTextColor(getResources().getColor(R.color.text_blue));
            tvTitleRight.setClickable(true);
        }else{
            ivDelete.setVisibility(View.VISIBLE);
            tvTitleRight.setTextColor(getResources().getColor(R.color.gray_2));
            tvTitleRight.setClickable(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void updateSucc() {
        T.showShort(mContext,"修改成功");
        PreferencesUtils.setPreferences(mContext,PreferencesUtils.USER_ADDRESS,etName.getText().toString());
        finish();
    }
}
