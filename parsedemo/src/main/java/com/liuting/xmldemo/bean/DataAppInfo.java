package com.liuting.xmldemo.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.List;

/**
 * Package:com.liuting.xmldemo.bean
 * author:liuting
 * Date:2017/4/7
 * Desc:apps 信息实体类
 */

@XStreamAlias("apps")
public class DataAppInfo implements Serializable {

    //该变量不是xml中的一个字段，应去掉
    @XStreamImplicit()
    private List<DataInfo> app;

    public List<DataInfo> getApp() {
        return app;
    }

    public void setApp(List<DataInfo> app) {
        this.app = app;
    }
}
