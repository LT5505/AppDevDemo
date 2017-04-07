package com.liuting.xmldemo.bean;

import java.io.Serializable;

/**
 * Package:com.liuting.xmldemo.bean
 * author:liuting
 * Date:2017/4/7
 * Desc:
 */

public class DataAppBean implements Serializable {
    private DataInfo app;//app

    public DataInfo getApp() {
        return app;
    }

    public void setApp(DataInfo app) {
        this.app = app;
    }
}
