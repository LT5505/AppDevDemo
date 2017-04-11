package com.liuting.okhttpexample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OKHttp 的功能
 * 1、get、post 请求
 * 2、文件的上传下载
 * 3、加载图片（内部会图片大小自动压缩）
 * 4、支持请求回调，直接返回对象、对象集合
 * 5、支持 Session 的保持
 *
 * OKHttp 的优势
 * 1、允许连接到同一主机地址的所有请求，提高请求效率
 * 2、共享 Socket，减少对服务器的请求次数
 * 3、通过连接池，减少请求延迟
 * 4、缓存响应数据来减少重复的网络请求
 * 5、减少对数据流量的消耗
 * 6、自动处理 GZip 压缩
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvResult;//数据结果
    private Button btnGet;//get 方法获取数据
    private Button btnPost;//post 方法获取数据
    private OkHttpClient client;//OKHttpClient
    private static final int GET_TYPE = 0;
    private static final int POST_TYPE = 1;
    private static final int ERROR_TYPE = -1;//错误
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");//格式为 json，编码为 utf-8
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ERROR_TYPE:
                case GET_TYPE:
                case POST_TYPE:
                    tvResult.setText(msg.obj.toString());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tvResult = (TextView) findViewById(R.id.main_tv_result);
        btnGet = (Button) findViewById(R.id.main_btn_get);
        btnPost = (Button) findViewById(R.id.main_btn_post);
        btnGet.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//10s
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .build();
    }

    /**
     * get 方法获取数据
     *
     * @param url url
     */
    private void initGetData(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            Message message = new Message();
            message.what = GET_TYPE;
            message.obj = response.body().toString();
            handler.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            Message message = new Message();
            message.what = ERROR_TYPE;
            message.obj = e.getMessage().toString();
            handler.sendMessage(message);
        }
    }

    /**
     * post 方法获取数据
     *
     * @param url     url
     * @param params  参数
     */
    private void initPostData(String url, String params) {
//        RequestBody body = RequestBody.create(JSON, params);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            Message message = new Message();
//            message.what = POST_TYPE;
//            message.obj = response.body().toString();
//            handler.sendMessage(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//            Message message = new Message();
//            message.what = ERROR_TYPE;
//            message.obj = e.getMessage().toString();
//            handler.sendMessage(message);
//        }
        RequestBody body = RequestBody.create(JSON, params);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.what = ERROR_TYPE;
                message.obj = e.getMessage().toString();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = new Message();
                message.what = POST_TYPE;
                message.obj = response.body().toString();
                handler.sendMessage(message);
            }
        });
    }

    private String getParams(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_get:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        initGetData("https://raw.github.com/square/okhttp/master/README.md");
                    }
                }).start();

                break;
            case R.id.main_btn_post:
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
                        String json = getParams("Jesse", "Jake");
                        initPostData("http://www.roundsapp.com/post", json);
//                    }
//                }).start();
                break;
        }
    }
}
