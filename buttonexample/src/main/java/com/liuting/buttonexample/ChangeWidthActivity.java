package com.liuting.buttonexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Package:com.liuting.buttonexample
 * author:liuting
 * Date:2017/3/24
 * Desc:改变button大小事例
 */

public class ChangeWidthActivity extends Activity implements View.OnClickListener {
    private Button btn01;//按钮1
    private Button btn02;//按钮2

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_width_layout);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btn01 = (Button) findViewById(R.id.change_width_btn_01);
        btn02 = (Button) findViewById(R.id.change_width_btn_02);
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int value = 1;//变化值
        Button button = (Button) view;
        //宽度达到了屏幕宽度就缩小
        if (value == 1 && button.getWidth() == getWindowManager().getDefaultDisplay().getWidth()) {
            value = -1;
            //宽度小于了100就扩大
        } else if (value == -1 && button.getWidth() < 100) {
            value = 1;
        }
        button.setWidth(button.getWidth() + (int) (button.getWidth() * 0.1) * value);
        button.setHeight(button.getHeight() + (int) (button.getHeight() * 0.1) * value);
    }
}
