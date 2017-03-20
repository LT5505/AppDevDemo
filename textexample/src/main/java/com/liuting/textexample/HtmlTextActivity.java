package com.liuting.textexample;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Package:com.liuting.textexample
 * author:liuting
 * Date:2017/3/20
 * Desc:实现TextView显示富文本
 * 总结：设置文本样式的4种方法：
 * 1、将android:autoLink属性值设为true。系统会自动识别E-mail、电话和网址等特殊文本。
 * 2、使用HTML标签，例如，<font></font>、<img>等，不要设置android:autoLink属性。
 * 3、在Java代码中直接使用Span对象来设置文本样式。这种方式需要将文本转换为一个SpannableString或SpnnableStringBuilder对象，
 * 然后在SpannableString或SpnnableStringBuilder对象中使用setSpan方法将要设置样式的文本转换成相应的Span对象。
 * 4、在字符串中使用<a></a>标签（只支持<a></a>标签）设置可单击的链接。不要设置android:autoLink属性，否则不起作用。
 * 上述4种方法只要涉及单击动作，就必须使用TextView.setMovementMethod方法设置相应的MovementMethod对象。
 *
 */
public class HtmlTextActivity extends AppCompatActivity {

    /**
     * 在TextView类中预定义了一些类似于HTML的标签，通过这些标签，可以使TextView控件显示不同的颜色、大小、字体的文字。
     * 比较常用的标签：
     * <font></font>设置颜色和字体，只支持color和face
     * <big></big>设置大号字
     * <small></small>设置小号字，如果<big></big>和<small></small>都没有，表示正常字号
     * <i></i>斜体
     * <b></b>粗体
     * <tt></tt>等宽字体（Monospace）
     * <br>换行（相当于"\n"）
     * <p>换行（相当于"\n\n"），直接使用"\n"不起作用
     * <a></a>链接地址
     * <img></img>插入图像
     */
    private TextView tvStyle;//用于显示不同颜色、字体、大小的文本

    /**
     * 设置TextView标签的android:autoLink属性可以在显示的文本中将URL地址、E-mail地址、电话等特殊内容高亮显示，并且在点击时触发动作
     * 属性值和功能
     * 1、none:不匹配任何链接（默认属性）；
     * 2、Web:匹配web网址；
     * 3、email:匹配E-mail地址；
     * 4、phone:匹配电话号码；
     * 5、map:匹配映射地址；
     * 6、all:匹配所有链接
     *
     */
    private TextView tvUrl;//用于显示带URL、Email和电话号码的文本

    /**
     *使用<img>标签给文本添加表情
     * <img>标签只有一个src属性，该属性原则上应该指向一个图像地址或者可以找到某个图像资源的唯一标识。
     * 在这里解析src属性值的工作需要在ImageGetter对象的getDrawable方法中完成。
     */
    private TextView tvImg;//用于显示表情
    private TextView tvStr;//用于显示URL、电话的文本

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_layout);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        tvStyle = (TextView) findViewById(R.id.html_tv_style);
        tvUrl = (TextView) findViewById(R.id.html_tv_url);
        tvImg = (TextView) findViewById(R.id.html_tv_img);
        tvStr = (TextView) findViewById(R.id.html_tv_str);
        String html = "<font color='red'>Hello world.</font><br>";
        html += "<font color='#0000FF'><big><i>Hello world</i></big></font><p>";
        html += "<font color='@"+android.R.color.white+"' ><tt><b><big><u>Hello world</u></big></b></tt></font><p>";
        html +="<big><a href ='http://www.cnblogs.com/LT5505'>我的网站</a></big><br>";
        //发送邮件并且抄送给LTina93@163.com，邮件内容为Testing mailto
        html +="<big><a href ='mailto:2451350522@qq.com?subject=Testing mailto&cc=LTina93@163.com'>我的Email</a></big><br>";
        html +="<big><a href ='tel:+86 0795-12345678'>我的电话</a></big><br>";
        //发送短信给10086和10010，内容为hello
        html +="<big><a href ='sms:10086,10010?body=hello'>发送短信</a></big><br>";
        CharSequence charSequence = Html.fromHtml(html);
        tvStyle.setText(charSequence);
        tvStyle.setMovementMethod(LinkMovementMethod.getInstance());

        String text = "我的URL：http://www.cnblogs.com/LT5505\n";
        text += "我的Email：2451350522@qq.com\n";
        text += "我的电话：+86 0795-12345678";
        tvUrl.setText(text);
        tvUrl.setMovementMethod(LinkMovementMethod.getInstance());

        String img = "图像1<img src='image1'/>图像2<img src='image2'/>图像3<img src='image3'/>";
        tvImg.setTextColor(Color.BLACK);
        tvImg.setBackgroundColor(Color.WHITE);
        CharSequence charSequence1 = Html.fromHtml(img, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable drawable =getResources().getDrawable(getResourceId(source));
                //第三个图像文件按50%等比例显示24*24
                if(source.equals("image3")){
                    drawable.setBounds(0,0,drawable.getIntrinsicWidth()/2,drawable.getIntrinsicHeight()/2);
                }else{
                    drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
                }
                return drawable;
            }
        },null);
        tvImg.setText(charSequence1);
        tvStr.setMovementMethod(LinkMovementMethod.getInstance());
    }


    /**
     *由于无法直接使用文件名来引用res/drawable中的图像资源，
     * 且R.drawable类中相应的资源ID变量名就是图像文件的文件名
     * 所以利用发射技术从R.drawable类中通过图像资源文件名
     * 获得相应的资源ID
     *
     * @param name  res/drawable中的图像文件名
     * @return
     */
    public int getResourceId(String name){
        try {
            Field field = R.drawable.class.getField(name);
            return Integer.parseInt(field.get(null).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
