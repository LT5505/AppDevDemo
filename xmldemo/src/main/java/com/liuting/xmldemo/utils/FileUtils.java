package com.liuting.xmldemo.utils;

import com.liuting.xmldemo.MyApp;

import org.apache.http.util.EncodingUtils;

import java.io.InputStream;

/**
 * Package:com.liuting.xmldemo.utils
 * author:liuting
 * Date:2017/4/6
 * Desc:
 */

public class FileUtils {
    /**
     * 从resource的asset中读取文件数据
     * @param fileName
     * @return
     */
    public static  String readFile(String fileName) {
        String res = "";
        try {

            //得到资源中的asset数据流
            InputStream in = MyApp.getInstance().getResources().getAssets().open(fileName);

            int length = in.available();
            byte[] buffer = new byte[length];

            in.read(buffer);
            in.close();
            res = EncodingUtils.getString(buffer, "UTF-8");

        } catch (Exception e) {

            e.printStackTrace();

        }
        return res;
    }
}
