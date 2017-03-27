package com.liuting.barexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Package:com.liuting.barexample
 * author:liuting
 * Date:2017/3/27
 * Desc:RatingBar显示事例
 * 总结：@和?的差别：
 * 1、@代表的是引用系统或者自定义的资源，比如@mipmap/ic_launcher，在style中使用@来引用事先先定义好的
 * 风格(Style)，相当于一组控件属性的值，如果不同控件的多个属性需要设置相同的值。
 *
 * 2、?代表的是引用系统主题的属性，系统主题是在Android SDK中预定义的，非系统的代码是无法访问的（就像系统中的内部类一样）。
 * 所以不能使用下面的代码直接引用系统的style:
 * <RatingBar .... style="@android:style/Widget.RatingBar.Small"></RatingBar>
 */

public class RatingActivity extends Activity implements RatingBar.OnRatingBarChangeListener{
    private RatingBar barSmall;//小五角星
    private RatingBar barMiddle;//指定五角星
    private TextView tvGoal;//显示分数的文本

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_layout);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        barSmall = (RatingBar) findViewById(R.id.rating_bar_small);
        barMiddle = (RatingBar) findViewById(R.id.rating_bar_middle);
        tvGoal = (TextView) findViewById(R.id.rating_tv_goal);
        barSmall.setOnRatingBarChangeListener(this);
        barMiddle.setOnRatingBarChangeListener(this);
    }

    //RatingBar变化监听
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if(ratingBar.getId()==R.id.rating_bar_small){
            barSmall.setRating(rating);
            tvGoal.setText("Small RatingBar is："+rating);
        }else if(ratingBar.getId()==R.id.rating_bar_middle){
            barMiddle.setRating(rating);
            tvGoal.setText("Middle RatingBar is："+rating);
        }
    }
}
