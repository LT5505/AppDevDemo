package com.liuting.textexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.liuting.textexample.widget.AddSubNumberLayout;

/**
 * @Title: AddSubNumberActivity
 * @Package: com.liuting.textexample
 * @Description: 增加减少数字控件的展示
 * @author: liuting
 * @Date: 2017/4/26 9:25
 */

public class AddSubNumberActivity extends Activity{
    private AddSubNumberLayout layoutMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_number_layout);

        layoutMain = (AddSubNumberLayout) findViewById(R.id.add_sub_number_layout_main);
//        layoutMain.setMaxValue(11);
//        layoutMain.setMinValue(2);
        layoutMain.setValue(1);
        layoutMain.setOnButtonClickListener(new AddSubNumberLayout.OnButtonClickListener() {
            @Override
            public void onButtonAddClick(View view, int value) {
//                Toast.makeText(AddSubNumberActivity.this,"Add",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onButtonSubClick(View view, int value) {
//                Toast.makeText(AddSubNumberActivity.this,"Sub",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
