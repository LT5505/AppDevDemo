package com.liuting.xmldemo;

import android.app.Application;

/**
 * Package:com.liuting.xmldemo
 * author:liuting
 * Date:2017/4/6
 * Desc:Application
 */

public class MyApp extends Application {
    private static MyApp instance;//MyApp
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApp getInstance() {
        return instance;
    }
}
