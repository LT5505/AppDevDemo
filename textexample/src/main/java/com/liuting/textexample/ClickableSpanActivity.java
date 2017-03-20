package com.liuting.textexample;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.liuting.textexample.widget.ColorSpan;

/**
 * Package:com.liuting.textexample
 * author:liuting
 * Date:2017/3/20
 * Desc:使用隐式创建ClickableSpan对象实例的方法来设置Span，并在其中覆盖OnClick方法
 */

public class ClickableSpanActivity extends Activity {
    private TextView tvAction1;//跳转
    private TextView tvAction2;//跳转
    private TextView tvBackground;//显示部分带背景的文本

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span_layout);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        tvAction1 = (TextView) findViewById(R.id.span_tv_action1);
        tvAction2 = (TextView) findViewById(R.id.span_tv_action2);
        tvBackground = (TextView) findViewById(R.id.span_tv_background);
        String text1 = "显示拨打电话";
        String text2 = "显示Activity2";
        SpannableString spannableString = new SpannableString(text1);
        SpannableString spannableString1 = new SpannableString(text2);
        /*
        * 4个参数，第一个参数设置ClickableSpan的对象，
        * 第2个和3个表示Span的某段的起始位置和终止位置，
        * 第4个参数在TextView中意义不大，在EditText代表一定的意义。
        *
        * 1、Spanned.SPAN_EXCLUSIVE_INCLUSIVE:在Span前面输入的字符不应用Span的效果，在后面输入的字符应用Span效果
        * 2、Spanned.SPAN_INCLUSIVE_EXCLUSIVE:在Sapn前面输入的字符应用Span效果，在后面输入的不应用Span效果
        * 3、Spanned.SPAN_INCLUSIVE_INCLUSIVE:在Span前后输入的字符都应用Span的效果
        * 4、Spanned.SPAN_EXCLUSIVE_EXCLUSIVE:在Span前后输入的字符都不应用Span的效果
        *
        * */
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ClickableSpanActivity.this,HtmlTextActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:12345678"));
                if (ActivityCompat.checkSelfPermission(ClickableSpanActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        }, 0, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString1.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClickableSpanActivity.this, HtmlTextActivity.class);
                startActivity(intent);
            }
        }, 0, text2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //使用SpannableString对象设置两个TextView控件的内容
        tvAction1.setText(spannableString);
        tvAction2.setText(spannableString1);

        //单击链接时凡是要执行的动作，都必须设置MovementMethod对象
        tvAction1.setMovementMethod(LinkMovementMethod.getInstance());
        tvAction2.setMovementMethod(LinkMovementMethod.getInstance());

        String textBackground = "<没有背景><黄色背景>\n\n<蓝色背景，红色文字>";
        //将字符串转换成SpannableString对象
        SpannableString spannableString2 = new SpannableString(textBackground);
        //确定要设置的字符串的start和end
        int start = 6;
        int end = 12;
        //创建BackgroundColorSpan对象
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.YELLOW);
        //使用setSpan方法指定子字符串转换为BackgroundColorSpan对象
        spannableString2.setSpan(backgroundColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //<蓝色背景，红色文字>的开始位置（每个“\n”算一个长度）
        //end就是text.length()
        start = 14;
        ColorSpan colorSpan = new ColorSpan(Color.RED, Color.BLUE);
        spannableString2.setSpan(colorSpan, start, textBackground.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用SpannableString对象设置TextView控件
        tvBackground.setText(spannableString2);
    }
}
