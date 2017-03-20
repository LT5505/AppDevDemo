package com.liuting.textexample.widget;

import android.text.TextPaint;
import android.text.style.CharacterStyle;

/**
 * Package:com.liuting.textexample.widget
 * author:liuting
 * Date:2017/3/20
 * Desc:自定义既可以设置背景又可以设置文字颜色的Span类
 */

public class ColorSpan extends CharacterStyle{
    private int mTextColor;//字体颜色
    private int mBackgroundColor;//背景色

    /**
     * @param mTextColor
     * @param mBackgroundColor
     */
    public ColorSpan(int mTextColor, int mBackgroundColor) {
        this.mTextColor = mTextColor;
        this.mBackgroundColor = mBackgroundColor;
    }

    @Override
    public void updateDrawState(TextPaint textPaint) {
        textPaint.bgColor=mBackgroundColor;
        textPaint.setColor(mTextColor);
    }
}
