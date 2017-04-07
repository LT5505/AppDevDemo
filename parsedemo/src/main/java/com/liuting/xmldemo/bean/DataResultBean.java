package com.liuting.xmldemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Package:com.liuting.xmldemo.bean
 * author:liuting
 * Date:2017/4/7
 * Desc:
 */

public class DataResultBean implements Serializable {
    private List<DataAppBean> apps;

    public List<DataAppBean> getApps() {
        return apps;
    }

    public void setApps(List<DataAppBean> apps) {
        this.apps = apps;
    }
}
