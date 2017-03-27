package com.liuting.barexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Package:com.liuting.barexample
 * author:liuting
 * Date:2017/3/27
 * Desc:ProgressBar进度条页面
 */

public class ProgressActivity extends Activity implements View.OnClickListener{
    private ProgressBar barHorizontal;//水平进度条
    private ProgressBar barVertical;//圆形进度条
    private Button btnIncrease;//增加进度
    private Button btnReduce;//减少进度
    private int progress;//进度

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //标题栏显示圆形进度条，已被弃用
//        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
//        setContentView(R.layout.activity_progress_layout);
//        setProgressBarIndeterminateVisibility(true);

        //标题栏显示水平进度条，已被弃用，进度范围为0~10000
//        requestWindowFeature(Window.FEATURE_PROGRESS);
//        setContentView(R.layout.activity_progress_layout);
//        setProgressBarVisibility(true);
//        setProgress(3500);
        setContentView(R.layout.activity_progress_layout);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btnIncrease = (Button) findViewById(R.id.progress_btn_increase);
        btnReduce = (Button) findViewById(R.id.progress_btn_reduce);
        barHorizontal = (ProgressBar) findViewById(R.id.progress_bar_horizontal);
        barVertical = (ProgressBar) findViewById(R.id.progress_bar_vertical);
        btnIncrease.setOnClickListener(this);
        btnReduce.setOnClickListener(this);
        progress = barHorizontal.getProgress();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.progress_btn_increase://增加进度
                progress += (0.2*progress);
                barHorizontal.setProgress(progress);
                barVertical.setProgress(progress);
                break;
            case R.id.progress_btn_reduce://减少进度
                progress -= (0.2*progress);
                barHorizontal.setProgress(progress);
                barVertical.setProgress(progress);
                break;
        }
    }
}
