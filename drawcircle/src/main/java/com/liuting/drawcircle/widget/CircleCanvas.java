package com.liuting.drawcircle.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.liuting.drawcircle.Bean.CircleInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Package:com.liuting.drawcircle.widget
 * author:liuting
 * Date:2017/3/16
 * Desc:绘制实心圆控件
 */

public class CircleCanvas extends View{
    public List<CircleInfo> mCircleInfos = new ArrayList<>();//保存绘制历史
    public CircleCanvas(Context context) {
        super(context);
    }

    public CircleCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(CircleInfo circleInfo : mCircleInfos){
            Paint paint = new Paint();
            paint.setColor(circleInfo.getColor());
            canvas.drawCircle(circleInfo.getX(),circleInfo.getY(),circleInfo.getRadius(),paint);
        }
    }
}
