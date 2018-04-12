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
 * 修改经营内容
 */
public class UpdateManageActivity extends BaseActivity<UpdateInfoPresenter> implements UpdateInfoContract.View,TextWatcher {
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;
    @Bind(R.id.tv_length)
    TextView tvLength;
    final int maxlength=2000;

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_manage;
    }

    @Override
    protected void onNetErrorClick() {

    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("修改经营内容");
        tvTitleRight.setText("保存");
        tvTitleRight.setClickable(true);
        etName.addTextChangedListener(this);
        Intent intent=getIntent();

        if (intent.getStringExtra("Management")!=null){
            String manage=intent.getStringExtra("Management");
            etName.setText(manage);
        }

        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesUtils.setPreferences(UpdateManageActivity.this,PreferencesUtils.USER_MANAGEMENT,etName.getText().toString().trim());
                finish();
                /*UserManager.User user = new UserManager(). new User();

                user.setManagement(etName.getText().toString().trim());*/
               // showProgressDialog("加载中");
              //  mPresenter.updateInfo(user);
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
        tvLength.setText(maxlength-s.length()+"/"+"2000");

    }

    @Override
    public void updateSucc() {
        T.showShort(mContext,"修改成功");
        PreferencesUtils.setPreferences(mContext,PreferencesUtils.USER_MANAGEMENT,etName.getText().toString());
        finish();
    }
}
