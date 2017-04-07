package com.liuting.xmldemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.liuting.xmldemo.R;
import com.liuting.xmldemo.bean.DataAppBean;
import com.liuting.xmldemo.bean.DataInfo;
import com.liuting.xmldemo.bean.DataResultBean;
import com.liuting.xmldemo.utils.FileUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Package:com.liuting.xmldemo.fragment
 * author:liuting
 * Date:2017/4/7
 * Desc:Json 数据解析
 */

public class parseJsonFragment extends BaseFragment{
    private boolean isPrepared;// 标志位，标志已经初始化完成。
    private int type;//类型
    private TextView textView;//解析结果
    public static final int JSON_PARSE_TYPE = 0;//使用 JsonObject 解析
    public static final int GSON_PARSE_TYPE = 1;//使用 Gson 解析
    public static final int FASTJSON_PARSE_TYPE = 2;//使用 FastJson 解析
    private List<DataInfo> list;//数据列表

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case JSON_PARSE_TYPE:
                case GSON_PARSE_TYPE:
                case FASTJSON_PARSE_TYPE:
                    StringBuffer stringBuffer = new StringBuffer();
                    for(int i = 0; i<list.size();i++){
                        stringBuffer.append("id is ");
                        stringBuffer.append(list.get(i).getId());
                        stringBuffer.append("\n name is ");
                        stringBuffer.append(list.get(i).getName());
                        stringBuffer.append("\n version is ");
                        stringBuffer.append(list.get(i).getVersion());
                        stringBuffer.append("\n\n");
                    }
                    textView.setText(stringBuffer.toString());
                    break;
            }
        }
    };

    public static parseJsonFragment newInstance(int type){
        parseJsonFragment parseJsonFragment = new parseJsonFragment();
        Bundle args = new Bundle();
        args.putInt("type",type);
        parseJsonFragment.setArguments(args);
        return parseJsonFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parse_xml_layout,null);
        type = getArguments().getInt("type");
        isPrepared = true;
        initView(view);
        return view;
    }

    public void initView(View view){
        textView = (TextView)view.findViewById(R.id.parse_xml_tv_result);
        readLocalFile();
    }

    public void readLocalFile(){
        String response = FileUtils.readFile("get_data.json");
        list = new ArrayList<>();
        switch (type) {
            case JSON_PARSE_TYPE://json 方法解析
                parseJsonWithJsonObject(response);
                handler.sendEmptyMessage(JSON_PARSE_TYPE);
                break;
            case GSON_PARSE_TYPE://Gson 方法解析
                parseJsonWithGson(response);
                handler.sendEmptyMessage(GSON_PARSE_TYPE);
                break;
            case FASTJSON_PARSE_TYPE://fastjson 方法解析
                parseJsonWithFastJson(response);
                handler.sendEmptyMessage(FASTJSON_PARSE_TYPE);
                break;
        }
    }

    /**
     * 使用 JsonObject 解析数据
     *
     * Android 你内置的 json 数据解析方法。
     *
     * @param jsonData  json数据
     */
    private void parseJsonWithJsonObject(String jsonData){

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray appArray = jsonObject.getJSONArray("apps");
            for(int i = 0;i < appArray.length();i++){
                JSONObject appObject = appArray.getJSONObject(i);
                JSONObject object = appObject.getJSONObject("app");
                DataInfo dataInfo = new DataInfo();
                dataInfo.setId(object.getString("id"));
                dataInfo.setName(object.getString("name"));
                dataInfo.setVersion(object.getString("version"));
                list.add(dataInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 Gson 解析数据
     *
     * Google 官方发布的。
     * Gson这个Java类库可以把Java对象转换成JSON，也可以把JSON字符串转换成一个相等的Java对象。
     * Gson支持任意复杂Java对象包括没有源代码的对象。
     *
     * @param jsonData  json数据
     */
    private void parseJsonWithGson(String jsonData){
        Gson gson = new Gson();
        DataResultBean dataResultBean = (DataResultBean)gson.fromJson(jsonData,DataResultBean.class);
        for(DataAppBean dataAppBean : dataResultBean.getApps()){
            list.add(dataAppBean.getApp());
        }
    }

    /**
     * 使用 FastJson 解析数据
     *
     * 阿里巴巴开源的框架
     * FastJson是一个Json处理工具包，包括“序列化”和“反序列化”两部分，它具备如下特征：
     * 速度最快，测试表明，fastjson具有极快的性能，超越任其他的Java Json parser。包括自称最快的JackJson。
     * 功能强大，完全支持Java Bean、集合、Map、日期、Enum，支持范型，支持自省。
     * 无依赖，能够直接运行在Java SE 5.0以上版本
     * 支持Android。
     * 开源 (Apache 2.0)
     *
     * @param jsonData  json数据
     */
    private void parseJsonWithFastJson(String jsonData){
        DataResultBean dataResultBean = JSON.parseObject(jsonData, DataResultBean.class);
        for(DataAppBean dataAppBean : dataResultBean.getApps()){
            list.add(dataAppBean.getApp());
        }
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        } else {
            readLocalFile();
        }
    }
}
