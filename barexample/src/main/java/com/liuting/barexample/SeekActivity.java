package com.liuting.barexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Package:com.liuting.barexample
 * author:liuting
 * Date:2017/3/27
 * Desc:SeekBar显示界面
 */

public class SeekActivity extends Activity implements SeekBar.OnSeekBarChangeListener{
    private SeekBar bar01;//拖动条
    private SeekBar bar02;//拖动条
    private TextView tvMain;//显示进度条的文本

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_layout);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        bar01 = (SeekBar) findViewById(R.id.seek_bar_01);
        bar02 = (SeekBar) findViewById(R.id.seek_bar_02);
        tvMain = (TextView) findViewById(R.id.seek_tv_main);
        bar01.setOnSeekBarChangeListener(this);
        bar02.setOnSeekBarChangeListener(this);
    }

    //SeekBar拖动进度变化监听
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        if(seekBar.getId()==R.id.seek_bar_01){
            tvMain.setText("bar_01的位置是： "+progress);
        }else if(seekBar.getId()==R.id.seek_bar_02){
            tvMain.setText("bar_02的位置是： "+progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
