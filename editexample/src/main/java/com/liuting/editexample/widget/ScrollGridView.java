package com.liuting.editexample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Package:com.liuting.editexample.widget
 * author:liuting
 * Date:2017/3/21
 * Desc:自定义在ScrollView中可滑动GridView
 */

public class ScrollGridView extends GridView {
    public ScrollGridView(Context context) {
        super(context);
    }

    public ScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
