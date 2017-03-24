package com.liuting.buttonexample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.widget.Button;

/**
 * Package:com.liuting.buttonexample
 * author:liuting
 * Date:2017/3/24
 * Desc:图文混排事例
 */

public class ImageTextActivity extends Activity{
    private Button btn05;//span图文混排

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_text_layout);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btn05 = (Button) findViewById(R.id.image_text_btn_05);
        SpannableString spannableStringLeft = new SpannableString("left");
        Bitmap bitmapLeft = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round);
        ImageSpan imageSpanLeft = new ImageSpan(bitmapLeft, DynamicDrawableSpan.ALIGN_BOTTOM);
        //将left转为ImageSpan对象
        spannableStringLeft.setSpan(imageSpanLeft,0,4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString spannableStringRight = new SpannableString("right");
        Bitmap bitmapRight = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round);
        ImageSpan imageSpanRight = new ImageSpan(bitmapRight);
        //将right转为ImageSpan对象
        spannableStringRight.setSpan(imageSpanRight,0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //添加左侧图像
        btn05.append(spannableStringLeft);
        btn05.append("My Button");
        //添加右侧图像
        btn05.append(spannableStringRight);
    }
}
