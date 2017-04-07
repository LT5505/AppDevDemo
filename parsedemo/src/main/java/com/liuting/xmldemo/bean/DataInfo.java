package com.liuting.xmldemo.bean;

import java.io.Serializable;

/**
 * Package:com.liuting.xmldemo.bean
 * author:liuting
 * Date:2017/4/6
 * Desc:app 信息实体类
 */

public class DataInfo implements Serializable {
    private String id;
    private String name;
    private String version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
