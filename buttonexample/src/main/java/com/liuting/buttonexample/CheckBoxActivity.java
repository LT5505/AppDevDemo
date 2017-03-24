package com.liuting.buttonexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Package:com.liuting.buttonexample
 * author:liuting
 * Date:2017/3/24
 * Desc:CheckBox选择事例
 */

public class CheckBoxActivity extends Activity implements View.OnClickListener {
    private List<CheckBox> checkBoxes;//checkbox列表
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkBoxes = new ArrayList<>();
        String[] checkboxText = new String[]{
            "Are you a girl?","Do you like Apple?","Do you want to go aboard?","Do you like Android?"
        };
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_checkbox_layout,null);
        for(int i=0;i<checkboxText.length;i++){
            //通过view_checkbox_layout.xml创建CheckBox
            CheckBox checkBox = (CheckBox) LayoutInflater.from(this).inflate(R.layout.view_checkbox_layout,null);
            checkBoxes.add(checkBox);
            checkBoxes.get(i).setText(checkboxText[i]);
            //将CheckBox列表添加到LinearLayout布局中，在显示按钮之前
            linearLayout.addView(checkBox,i);
        }
        setContentView(linearLayout);
        Button btnSure = (Button)findViewById(R.id.checkbox_btn_sure);
        btnSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String s = "";
        for(CheckBox checkBox : checkBoxes){
            if(checkBox.isChecked()){
                s += checkBox.getText()+"\n";
            }
        }
        if(s.equals("")|| TextUtils.isEmpty(s)){
            s = "You did not select anything";
        }
        new AlertDialog.Builder(this).setMessage(s).setPositiveButton("Close",null).show();
    }
}
