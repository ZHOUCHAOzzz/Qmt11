package com.miracle.qmt.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.miracle.qmt.util.pinyin.BitmapUtil;


/**
 * @Description: 文字图片，这个相信大家都知道，比如QQ底部导航上的未读消息数
 * 
 * @author http://blog.csdn.net/finddreams
 */ 
public class ImageTextView extends TextView {
	private Bitmap bitmap;
	private String text;
	Drawable d;

	public ImageTextView(Context context) {
		super(context);
		
	}

	public ImageTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
	}

	public ImageTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	
	public void setIconText(Context context,String text){

		text = text.substring(0, 1);
		bitmap = BitmapUtil.getIndustry(context, text);
		d = BitmapUtil.bitmapTodrawable(bitmap);
		this.setCompoundDrawables(d, null, null, null);
	}

}
