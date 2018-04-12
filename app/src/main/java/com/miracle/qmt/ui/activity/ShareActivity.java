package com.miracle.qmt.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.miracle.qmt.AppController;
import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.util.ConstantValue;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;



/**
 * Created by Administrator on 2017/7/31.
 */

public class ShareActivity extends BaseActivity {
    @Bind(R.id.iv_pserson)
    CircleImageView ivPserson;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.iv_code)
    ImageView ivCode;
    @Bind(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @Bind(R.id.title_txt)
    TextView titleTxt;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    private Bitmap qrBitmap;

    @Override
    public int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("分享");
        mTvRight.setText("分享");
        mTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage image = new UMImage(ShareActivity.this, qrBitmap);//网络图片
                new ShareAction(ShareActivity.this)
                        .withText("全苗通下载地址")
                        .withMedia(image)
                        .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(shareListener)
                        .open();
            }
        });
    }
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ShareActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ShareActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
            finish();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShareActivity.this,"取消了",Toast.LENGTH_LONG).show();
            finish();

        }
    };

    @Override
    public void initData() {
        super.initData();
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.icon_headpic)
                .error(R.drawable.icon_headpic);
        Glide.with(this)
                .load(AppController.getInstance().getUser().getHead_pic())
                .thumbnail(0.4f)
                .apply(options)
                .into(ivPserson);

        //tvName.setText(AppController.getInstance().getUser().getNick_name());
        tvName.setText(AppController.getInstance().getUser().getNick_name());
         qrBitmap = this.generateBitmap(ConstantValue.APKURL, 400, 400);
        ivCode.setImageBitmap(qrBitmap);
    }

    @Override
    protected void onNetErrorClick() {

    }

    private Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
