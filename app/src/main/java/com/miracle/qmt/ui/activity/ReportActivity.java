package com.miracle.qmt.ui.activity;


import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.ReportContract;
import com.miracle.qmt.ui.model.ResultBean;
import com.miracle.qmt.ui.presenter.ReportPresenter;

import butterknife.Bind;
import me.leefeng.promptlibrary.PromptDialog;

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
    private PromptDialog promptDialog;

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
        //创建对象
        promptDialog = new PromptDialog(this);
        mTvTitle.setText("留言举报");
        tvTitleRight.setText("保存");
        tvTitleRight.setClickable(true);
        etName.addTextChangedListener(this);
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etName.getText().toString())) {
                    promptDialog.showWarn("留言成功");
                    return;
                }
                mPresenter.getData(etName.getText().toString().trim());
              //  showProgressDialog("加载中");
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

    @Override
    public void getData(ResultBean s) {
        switch (s.getResult()){
            case 0:
                Toast.makeText(ReportActivity.this,"举报成功",Toast.LENGTH_SHORT).show();
            //    promptDialog.showSuccess("留言成功");
                finish();
                break;
            default:
                Toast.makeText(ReportActivity.this,"举报失败",Toast.LENGTH_SHORT).show();

                break;

        }

    }

//    @Override
//    public void updateSucc() {
//        T.showShort(mContext,"修改成功");
//        PreferencesUtils.setPreferences(mContext,PreferencesUtils.USER_MANAGEMENT,etName.getText().toString());
//        finish();
//    }

}
