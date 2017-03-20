package com.liuting.drawcircle;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.liuting.drawcircle.Bean.CircleInfo;
import com.liuting.drawcircle.widget.CircleCanvas;

import java.util.Random;

/**
 * Package:com.liuting.drawcircle
 * author:liuting
 * Date:2017/3/16
 * Desc:实现自动画圆的功能，自定义View的初步
 */

public class MainActivity extends AppCompatActivity {
    private CircleCanvas mCircleCanvas;//定义一个画布类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup viewGroup = (ViewGroup)getLayoutInflater().inflate(R.layout.activity_draw,null);
        mCircleCanvas = new CircleCanvas(this);
        viewGroup.addView(mCircleCanvas,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,350));
        setContentView(viewGroup);
    }

    /**
     * 开始随机绘制圆形
     *
     * @param view
     */
    public void onClick_DrawRandomCircle(View view){
        Random random = new Random();
        float randomX = (float)(100+random.nextInt(100));//生成圆心横坐标（100~200）
        float randomY = (float)(100+random.nextInt(100));//生成圆心纵坐标（100~200）
        float randomRadius = (float)(20+random.nextInt(40));//生成圆形半径（20~60）
        int randomColor = 0;
        if(random.nextInt(100)>50){
            randomColor = Color.BLUE;
        }else{
            if(random.nextInt(100)>50){
                randomColor = Color.RED;
            }else{
                randomColor = Color.GREEN;
            }
        }
        CircleInfo circleInfo = new CircleInfo();
        circleInfo.setX(randomX);
        circleInfo.setY(randomY);
        circleInfo.setRadius(randomRadius);
        circleInfo.setColor(randomColor);
        mCircleCanvas.mCircleInfos.add(circleInfo);
        mCircleCanvas.invalidate();//让画布重绘
    }

    /**
     * 清除画布
     *
     * @param view
     */
    public void onClick_Clear(View view){
        mCircleCanvas.mCircleInfos.clear();
        mCircleCanvas.invalidate();//让画布重绘
    }
}
