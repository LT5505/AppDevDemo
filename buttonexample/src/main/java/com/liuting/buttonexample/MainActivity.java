package com.liuting.buttonexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnChangeWidth;//改变Button大小事例
    private Button btnImageText;//图文混排
    private Button btnCheckBox;//选择按钮
    private Button btnChangeImage;//改变图片

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
        btnChangeWidth = (Button)findViewById(R.id.main_btn_change_width);
        btnChangeWidth.setOnClickListener(this);
        btnImageText = (Button)findViewById(R.id.main_btn_image_text);
        btnImageText.setOnClickListener(this);
        btnCheckBox = (Button)findViewById(R.id.main_btn_checkbox);
        btnCheckBox.setOnClickListener(this);
        btnChangeImage = (Button)findViewById(R.id.main_btn_change_image);
        btnChangeImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.main_btn_change_width:
                intent = new Intent(MainActivity.this,ChangeWidthActivity.class);
                startActivity(intent);
                break;
            case R.id.main_btn_image_text:
                intent = new Intent(MainActivity.this,ImageTextActivity.class);
                startActivity(intent);
                break;
            case R.id.main_btn_checkbox:
                intent = new Intent(MainActivity.this,CheckBoxActivity.class);
                startActivity(intent);
                break;
            case R.id.main_btn_change_image:
                intent = new Intent(MainActivity.this,ChangeImageActivity.class);
                startActivity(intent);
                break;

        }
    }
}
