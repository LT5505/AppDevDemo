package com.liuting.textexample.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Package:com.liuting.textexample.widget
 * author:liuting
 * Date:2017/3/20
 * Desc:自定义带边框的TextView
 */

public class BorderTextView extends TextView{
    public BorderTextView(Context context) {
        super(context);
    }

    public BorderTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BorderTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        //绘制上边框
        canvas.drawLine(0,0,this.getWidth() - 1,0 ,paint);
        //绘制左边框
        canvas.drawLine(0,0,0,this.getHeight() - 1 ,paint);
        //绘制有边框
        canvas.drawLine(this.getWidth() - 1,0,this.getWidth() - 1,this.getHeight() -1 ,paint);
        //绘制下边框
        canvas.drawLine(0,this.getHeight() - 1,this.getWidth() - 1,this.getHeight() - 1,paint);
    }
}
