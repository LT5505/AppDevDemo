package com.liuting.xmldemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.liuting.xmldemo.adapter.MyPagerAdapter;
import com.liuting.xmldemo.fragment.parseXMLFragment;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rgIndicator;//切换导航栏
    private ViewPager vpMain;//ViewPAger
    private Fragment[] fragments = new Fragment[3];//Fragments
    private int type;//类型区分

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        rgIndicator = (RadioGroup) findViewById(R.id.main_rg_indicator);
        vpMain = (ViewPager) findViewById(R.id.main_vp_main);

        fragments[0] = parseXMLFragment.newInstance(parseXMLFragment.PULL_PARSE_TYPE);
        fragments[1] = parseXMLFragment.newInstance(parseXMLFragment.SAX_PARSE_TYPE);
        fragments[2] = parseXMLFragment.newInstance(parseXMLFragment.DOM_PARSE_TYPE);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        vpMain.setAdapter(adapter);
        vpMain.setOffscreenPageLimit(3);
        vpMain.setCurrentItem(0);
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rgIndicator.check(R.id.main_rb_pull);
                        break;
                    case 1:
                        rgIndicator.check(R.id.main_rb_sax);
                        break;
                    case 2:
                        rgIndicator.check(R.id.main_rb_dom);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /**
         * 点击RadioButton 切换Fragment
         */
        rgIndicator.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb_pull:
                        vpMain.setCurrentItem(parseXMLFragment.PULL_PARSE_TYPE);
                        break;
                    case R.id.main_rb_sax:
                        vpMain.setCurrentItem(parseXMLFragment.SAX_PARSE_TYPE);
                        break;
                    case R.id.main_rb_dom:
                        vpMain.setCurrentItem(parseXMLFragment.DOM_PARSE_TYPE);
                        break;
                }
            }
        });
    }
}

