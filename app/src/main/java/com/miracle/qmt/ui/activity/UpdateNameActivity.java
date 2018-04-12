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
public class UpdateNameActivity extends BaseActivity<UpdateInfoPresenter> implements UpdateInfoContract.View,TextWatcher {
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_name;
    }

    @Override
    protected void onNetErrorClick() {

    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("修改姓名");
        tvTitleRight.setText("保存");
        tvTitleRight.setClickable(true);
        etName.addTextChangedListener(this);

        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*UserManager.User user = new UserManager().new  User();
                user.setName(etName.getText().toString().trim());*/
                PreferencesUtils.setPreferences(UpdateNameActivity.this,PreferencesUtils.USER_NAME,etName.getText().toString().trim());
                Intent mIntent = new Intent();
                mIntent.putExtra("name", etName.getText().toString().trim());
                // 设置结果，并进行传送
                setResult(101, mIntent);
                finish();

                // showProgressDialog("处理中");
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

    }

    @Override
    public void updateSucc() {
        T.showShort(mContext,"修改成功");
        PreferencesUtils.setPreferences(mContext,PreferencesUtils.USER_NAME,etName.getText().toString());
       // setResult(100);
        finish();
    }
}
