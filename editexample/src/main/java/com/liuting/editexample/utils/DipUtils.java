package com.liuting.editexample.utils;

import android.content.Context;

import com.liuting.editexample.MyApp;

/**
 * Package:com.liuting.editexample.utils
 * author:liuting
 * Date:2017/3/21
 * Desc:单位转换类
 */

public class DipUtils {
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context paramContext, float pxValue) {
        return (int) (0.5F + pxValue / paramContext.getResources().getDisplayMetrics().density);
    }

    public static int px2dip(float pxValue) {
        return (int) (0.5F + pxValue * MyApp.getInstance().getResources().getDisplayMetrics().density);
    }
}
