package com.liuting.textexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Package:com.liuting.textexample
 * author:liuting
 * Date:2017/3/24
 * Desc:主页面
 */

public class MainActivity extends Activity implements View.OnClickListener{
    private Button btnHtml;//html文本事例
    private Button btnBorder;//边框文本事例
    private Button btnSpan;//SpanString文本事例
    private Button btnMarquee;//跑马灯事例

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btnHtml = (Button) findViewById(R.id.main_btn_html);
        btnBorder = (Button) findViewById(R.id.main_btn_border);
        btnSpan = (Button) findViewById(R.id.main_btn_span);
        btnMarquee = (Button) findViewById(R.id.main_btn_marquee);
        btnHtml.setOnClickListener(this);
        btnBorder.setOnClickListener(this);
        btnSpan.setOnClickListener(this);
        btnMarquee.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent ;
        switch (view.getId()){
            case R.id.main_btn_html:
                intent= new Intent(MainActivity.this,HtmlTextActivity.class);
                startActivity(intent);
                break;
            case R.id.main_btn_border:
                intent= new Intent(MainActivity.this,BorderTextActivity.class);
                startActivity(intent);
                break;
            case R.id.main_btn_span:
                intent= new Intent(MainActivity.this,ClickableSpanActivity.class);
                startActivity(intent);
                break;
            case R.id.main_btn_marquee:
                intent= new Intent(MainActivity.this,MarqueeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
