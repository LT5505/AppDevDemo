package com.liuting.editexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.liuting.editexample.Bean.FaceInfo;
import com.liuting.editexample.adapter.FaceGvAdapter;
import com.liuting.editexample.adapter.MsgListAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Package:com.liuting.editexample
 * author:liuting
 * Date:2017/3/21
 * Desc:仿照QQ插入表情
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editInsert;//插入表情
    private GridView gvFace;//表情网格列表
    private FaceGvAdapter adapter;//表情Adapter
    private List<FaceInfo> listFace;//表情列表
    private List<Editable> listMsg;//内容列表
    private ListView lvMsg;//内容列表
    private MsgListAdapter msgListAdapter;//内容Adapter
    private Button btnSend;//发送
    private ImageView imgFace;//表情图标
    private InputMethodManager inputMethodManager;//输入控制

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 控件初始化
     */
    private void initView() {
        listFace = new ArrayList<>();
        listMsg = new ArrayList<>();
        for (int i = 1; i <= 18; i++) {
            FaceInfo faceInfo = new FaceInfo();
            try {
                //根据index从R.drawable类中获得相应资源ID的Field对象
                Field field = R.drawable.class.getDeclaredField("image" + i);
                //获取资源ID的值，也就是静态变量的值
                int resourceId = Integer.parseInt(field.get(null).toString());
                faceInfo.setResourceId(resourceId);
                listFace.add(faceInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        editInsert = (EditText) findViewById(R.id.main_edit_insert);
        gvFace = (GridView) findViewById(R.id.main_gv_face);
        adapter = new FaceGvAdapter(listFace, MainActivity.this, new FaceGvAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                onItemClick_InsertFace(view, position + 1);
            }
        });
        gvFace.setAdapter(adapter);
        lvMsg = (ListView) findViewById(R.id.main_lv_msg);
        msgListAdapter = new MsgListAdapter(MainActivity.this,listMsg);
        lvMsg.setAdapter(msgListAdapter);
        btnSend = (Button)findViewById(R.id.main_btn_send);
        btnSend.setOnClickListener(this);
        imgFace = (ImageView)findViewById(R.id.main_img_face);
        imgFace.setOnClickListener(this);
        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    /**
     * 发送信息
     *
     * @param editable
     */
    public void onClick_Send(Editable editable) {

        if (editInsert.getText() == null || TextUtils.isEmpty(editInsert.getText())) {
            Toast.makeText(MainActivity.this, "Please input something", Toast.LENGTH_SHORT).show();
        } else {
            listMsg.add(editable);
            msgListAdapter.notifyDataSetChanged();
            //必须清空数据，list.add()方法下追加的是地址赋值，如果没有变化，会覆盖之前的数据
            editInsert.setText("");
            //滑到最底部，目前只对文字有用
            //TODO: 滚动到底部的方法，目前只对文字有用，表情没有效果
            lvMsg.setSelection(ListView.FOCUS_DOWN);
        }
    }

    /**
     * 插入表情
     *
     * @param view
     * @param index
     */
    public void onItemClick_InsertFace(View view, int index) {
        try {
            //根据index从R.drawable类中获得相应资源ID的Field对象
            Field field = R.drawable.class.getDeclaredField("image" + index);
            //获取资源ID的值，也就是静态变量的值
            int resourceId = Integer.parseInt(field.get(null).toString());
            //根据资源ID获得资源图像的Bitmap对象
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
            //根据Bitmap对象创建ImageSpan对象
            ImageSpan imageSpan = new ImageSpan(this, bitmap);
            //创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
            SpannableString spannableString = new SpannableString("image" + index);
            //用ImageSpan对象替换image
            spannableString.setSpan(imageSpan, 0, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //将随机获得的图像追加到EditText控件的最后
            editInsert.append(spannableString);
            //将光标置于EditText控件的最后
            editInsert.setSelection(editInsert.getText().length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.main_btn_send){
            Editable editable =editInsert.getText();
            onClick_Send(editable);
        }else if(view.getId()==R.id.main_img_face){
            if(gvFace.getVisibility()==View.VISIBLE){
                gvFace.setVisibility(View.GONE);
            }else if(gvFace.getVisibility()==View.GONE){
                //显示表情同时隐藏软键盘
                gvFace.setVisibility(View.VISIBLE);
                KeyBoardCancel();
            }
        }
    }

    //强制隐藏软键盘
    public void KeyBoardCancel() {
        View view = getWindow().peekDecorView();
        if (view != null) {
//            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public Boolean isShowKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开
        return isOpen;
    }

//    public void onClick_InsertFace(View view){
//        //随机产生1至5的整数
//        int randomId = 1 + new Random().nextInt(4);
//        try {
//            //根据随机产生的1至5的整数从R.drawable类中获得相应资源ID的Field对象
//            Field field = R.drawable.class.getDeclaredField("image"+randomId);
//            //获取资源ID的值，也就是静态变量的值
//            int resourceId = Integer.parseInt(field.get(null).toString());
//            //根据资源ID获得资源图像的Bitmap对象
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),resourceId);
//            //根据Bitmap对象创建ImageSpan对象
//            ImageSpan imageSpan = new ImageSpan(this,bitmap);
//            //创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
//            SpannableString spannableString = new SpannableString("image");
//            //用ImageSpan对象替换image
//            spannableString.setSpan(imageSpan,0,4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            //将随机获得的图像追加到EditText控件的最后
//            editInsert.append(spannableString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    public void onBackPressed() {
        //判断表情列表是否隐藏，显示时隐藏，隐藏时直接finish
        if(gvFace.getVisibility()==View.VISIBLE){
            gvFace.setVisibility(View.GONE);
        }else{
            finish();
        }
    }
}
