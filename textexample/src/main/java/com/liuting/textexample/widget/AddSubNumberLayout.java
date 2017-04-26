package com.liuting.textexample.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liuting.textexample.R;

/**
 * @Title: AddSubNumberLayout
 * @Package: com.liuting.textexample.widget
 * @Description: 自定义可以加减的数字控件
 * @author: liuting
 * @Date: 2017/4/26 9:12
 */

public class  AddSubNumberLayout extends LinearLayout implements View.OnClickListener{
    private LayoutInflater mInflater;//LayoutInflater
    private Button btnAdd;//加按钮
    private Button btnSub;//减按钮
    private TextView tvNum;//数字
    private int value;//初始值
    private int minValue;//最小值
    private int maxValue;//最大值
    private int variation = 1;//差值

    private  OnButtonClickListener mOnButtonClickListener;
    public AddSubNumberLayout(Context context) {
        this(context,null);
    }

    public AddSubNumberLayout(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public AddSubNumberLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mInflater = LayoutInflater.from(context);
        initView(context,attrs,defStyleAttr);
    }

    /**
     * @author: liuting
     * @date: 2017/4/26 14:49
     * @Title: initView
     * @Description: 初始化
     * @param: [context, attrs, defStyleAttr]
     * @return: void
     * @throws
     */
    public void initView(Context context,AttributeSet attrs,int defStyleAttr){

        View view = mInflater.inflate(R.layout.view_add_sub_number_layout,this,true);
        btnAdd = (Button) view.findViewById(R.id.view_btn_add);
        btnSub = (Button) view.findViewById(R.id.view_btn_sub);
        tvNum = (TextView) view.findViewById(R.id.view_tv_num);

        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);

        if (attrs != null){
            TintTypedArray array = TintTypedArray.obtainStyledAttributes(context,attrs, R.styleable.AddSubNumberLayout, defStyleAttr,0);
            int value = array.getInt(R.styleable.AddSubNumberLayout_value,1);
            setValue(value);

            int minVal =  array.getInt(R.styleable.AddSubNumberLayout_minValue,1);
            setMinValue(minVal);

            int maxVal =  array.getInt(R.styleable.AddSubNumberLayout_maxValue,1);
            setMaxValue(maxVal);

            Drawable drawableBtnAdd =array.getDrawable(R.styleable.AddSubNumberLayout_btnAddBackground);
            Drawable drawableBtnSub =array.getDrawable(R.styleable.AddSubNumberLayout_btnSubBackground);
            Drawable drawableTextView =array.getDrawable(R.styleable.AddSubNumberLayout_textViewBackground);

            setButtonAddBackground(drawableBtnAdd);
            setButtonSubBackground(drawableBtnSub);
            setTexViewBackground(drawableTextView);

            setBtnAddTextColor(array.getColor(R.styleable.AddSubNumberLayout_btnAddTextColor, Color.BLACK));
            setBtnSubTextColor(array.getColor(R.styleable.AddSubNumberLayout_btnSubTextColor,Color.BLACK));
            setTextViewTextColor(array.getColor(R.styleable.AddSubNumberLayout_textViewTextColor,Color.BLACK));

            setBtnAddTextSize(array.getDimension(R.styleable.AddSubNumberLayout_btnAddTextSize,R.dimen.btn_add_text_size));
            setBtnSubTextSize(array.getDimension(R.styleable.AddSubNumberLayout_btnSubTextSize,R.dimen.btn_add_text_size));
            setTextViewTextSize(array.getDimension(R.styleable.AddSubNumberLayout_textViewTextSize,R.dimen.btn_add_text_size));

            setVariation(array.getInt(R.styleable.AddSubNumberLayout_variation,variation));
            array.recycle();
        }
    }

    public int getVariation() {
        return variation;
    }

    public void setVariation(int variation) {
        this.variation = variation;
    }

    /**
     * @author: liuting
     * @date: 2017/4/26 14:49
     * @Title: getValue
     * @Description: 获取初始值
     * @param: []
     * @return: int
     * @throws
     */
    public int getValue() {

        String val =  tvNum.getText().toString();

        if(val !=null && !"".equals(val))
            this.value = Integer.parseInt(val);

        return value;
    }

    /**
     * @author: liuting
     * @date: 2017/4/26 14:49
     * @Title: setValue
     * @Description: 设置初始值
     * @param: [value]
     * @return: void
     * @throws
     */
    public void setValue(int value) {
        if(value <= getMinValue()){
            this.value = getMinValue();
        }else{
            this.value = value;
        }

        tvNum.setText(this.value+"");
    }

    public int getMinValue() {
        return minValue;
    }

    /**
     * @author: liuting
     * @date: 2017/4/26 14:48
     * @Title: setMinValue
     * @Description: 设置最小值
     * @param: [minValue]
     * @return: void
     * @throws
     */
    public void setMinValue(int minValue) {
        this.minValue = minValue;
        if(this.value <= minValue){
            this.value = minValue;
            tvNum.setText(this.value+"");
        }
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }


    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.mOnButtonClickListener = onButtonClickListener;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.view_btn_add) {

            numAdd();
            if (mOnButtonClickListener != null) {

                mOnButtonClickListener.onButtonAddClick(v,value);
            }

        } else if (v.getId() == R.id.view_btn_sub) {

            numSub();

            if (mOnButtonClickListener != null) {

                mOnButtonClickListener.onButtonSubClick(v,value);
            }
        }
    }

    /**
     * @author: liuting
     * @date: 2017/4/26 14:47
     * @Title: numAdd
     * @Description:  数值加
     * @param: []
     * @return: void
     * @throws
     */
    private void numAdd(){

        if((value + variation) <= maxValue)
            value=value+variation;
        else
            Toast.makeText(getContext(),"不能再加了",Toast.LENGTH_SHORT).show();

        tvNum.setText(value+"");
    }

    /**
     * @author: liuting
     * @date: 2017/4/26 14:48
     * @Title: numSub
     * @Description: 数值减
     * @param: []
     * @return: void
     * @throws
     */
    private void numSub(){

        if(value >= (minValue+variation))
            value=value-variation;
        else
            Toast.makeText(getContext(),"不能再减了",Toast.LENGTH_SHORT).show();

        tvNum.setText(value+"");
    }

    public void setTexViewBackground(Drawable drawable){
        tvNum.setBackgroundDrawable(drawable);
    }

    public void setTextViewBackground(int drawableId){
        setTexViewBackground(getResources().getDrawable(drawableId));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setButtonAddBackground(Drawable background){
        this.btnAdd.setBackground(background);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setButtonSubBackground(Drawable background){
        this.btnSub.setBackground(background);
    }

    public void setBtnAddTextColor(int color){
        btnAdd.setTextColor(color);
    }

    public void setBtnSubTextColor(int color){
        btnSub.setTextColor(color);
    }

    public void setTextViewTextColor(int color){
        tvNum.setTextColor(color);
    }

    public void setBtnAddTextSize(float size){
        btnAdd.setTextSize(TypedValue.COMPLEX_UNIT_PX,size);
    }

    public void setBtnSubTextSize(float size){
        btnSub.setTextSize(TypedValue.COMPLEX_UNIT_PX,size);
    }

    public void setTextViewTextSize(float size){
        tvNum.setTextSize(TypedValue.COMPLEX_UNIT_PX,size);
    }

    public interface  OnButtonClickListener{

        void onButtonAddClick(View view,int value);
        void onButtonSubClick(View view,int value);
    }
}
