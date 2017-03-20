package com.liuting.textexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

/**
 * Package:com.liuting.textexample
 * author:liuting
 * Date:2017/3/20
 * Desc:文本水平跑马灯和垂直滚动效果
 */

public class MarqueeActivity extends Activity{
    private TextView tvVertical;//显示垂直滚动
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee_layout);
        tvVertical = (TextView)findViewById(R.id.marquee_tv_vertical);
        //要让垂直的文本滚动起来必须设置滚动属性ScrollingMovementMethod
        tvVertical.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
