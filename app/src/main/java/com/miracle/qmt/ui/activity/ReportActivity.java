package com.miracle.qmt.ui.activity;


import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.ReportContract;
import com.miracle.qmt.ui.presenter.ReportPresenter;
import com.miracle.qmt.util.PreferencesUtils;
import com.miracle.qmt.util.T;
import com.miracle.qmt.util.UserManager;

import butterknife.Bind;

/**
 * 留言举报
 */
public class ReportActivity extends BaseActivity<ReportPresenter> implements ReportContract.View,TextWatcher{

    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;

    @Override
    public int getLayoutId() {
        return R.layout.activity_report;
    }

    @Override
    protected void onNetErrorClick() {

    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("留言举报");
        tvTitleRight.setText("保存");
        tvTitleRight.setClickable(true);
        etName.addTextChangedListener(this);
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManager.User user = new UserManager().new User();
                user.setManagement(etName.getText().toString().trim());
                showProgressDialog("加载中");
//                mPresenter.updateInfo(user);
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

//    @Override
//    public void updateSucc() {
//        T.showShort(mContext,"修改成功");
//        PreferencesUtils.setPreferences(mContext,PreferencesUtils.USER_MANAGEMENT,etName.getText().toString());
//        finish();
//    }

}