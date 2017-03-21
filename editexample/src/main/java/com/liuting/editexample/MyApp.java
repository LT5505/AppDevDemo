package com.liuting.editexample;

import android.app.Application;

/**
 * Package:com.liuting.editexample
 * author:liuting
 * Date:2017/3/21
 * Desc:Application类
 */

public class MyApp extends Application{
    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance =this;
    }
}
