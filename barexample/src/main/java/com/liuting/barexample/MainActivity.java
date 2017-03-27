package com.liuting.barexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Package:com.liuting.barexample
 * author:liuting
 * Date:2017/3/27
 * Desc:主页面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnProgress;//进度条
    private Button btnSeek;//拖动条
    private Button btnRating;//评分条

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btnProgress = (Button) findViewById(R.id.main_btn_progress);
        btnSeek = (Button) findViewById(R.id.main_btn_seek);
        btnRating = (Button) findViewById(R.id.main_btn_rating);
        btnProgress.setOnClickListener(this);
        btnSeek.setOnClickListener(this);
        btnRating.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.main_btn_progress://进度条
                intent = new Intent(MainActivity.this, ProgressActivity.class);
                break;
            case R.id.main_btn_seek://拖动条
                intent = new Intent(MainActivity.this, SeekActivity.class);
                break;
            case R.id.main_btn_rating://评分条
                intent = new Intent(MainActivity.this, RatingActivity.class);
                break;
        }
        startActivity(intent);
    }
}
