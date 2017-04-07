package com.liuting.xmldemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.liuting.xmldemo.adapter.MyPagerAdapter;
import com.liuting.xmldemo.fragment.parseJsonFragment;
import com.liuting.xmldemo.fragment.parseXMLFragment;

/**
 *
 * JSON VS XML
 * 1.JSON和XML的数据可读性基本相同
 * 2.JSON和XML同样拥有丰富的解析手段
 * 3.JSON相对于XML来讲，数据的体积小
 * 4.JSON与JavaScript的交互更加方便
 * 5.JSON对数据的描述性比XML较差
 * 6.JSON的速度要远远快于XML
 *
 */
public class MainActivity extends AppCompatActivity {
    private RadioGroup rgXml;//切换导航栏
    private RadioGroup rgJson;//切换导航栏
    private ViewPager vpXml;//ViewPAger
    private ViewPager vpJson;//ViewPAger

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
        rgXml = (RadioGroup) findViewById(R.id.main_rg_xml);
        rgJson = (RadioGroup) findViewById(R.id.main_rg_json);
        vpXml = (ViewPager) findViewById(R.id.main_vp_xml);
        vpJson = (ViewPager) findViewById(R.id.main_vp_json);
//        initXMLView();
        initJsonView();
    }

    /**
     * 初始化显示 XML 布局
     */
    private void initXMLView() {
        vpXml.setVisibility(View.VISIBLE);
        vpJson.setVisibility(View.GONE);
        rgXml.setVisibility(View.VISIBLE);
        rgJson.setVisibility(View.GONE);
        Fragment[] fragments;fragments = new Fragment[4];//Fragments
        fragments[0] = parseXMLFragment.newInstance(parseXMLFragment.PULL_PARSE_TYPE);
        fragments[1] = parseXMLFragment.newInstance(parseXMLFragment.SAX_PARSE_TYPE);
        fragments[2] = parseXMLFragment.newInstance(parseXMLFragment.DOM_PARSE_TYPE);
        fragments[3] = parseXMLFragment.newInstance(parseXMLFragment.XSTREAM_PARSE_TYPE);
        vpXml.clearOnPageChangeListeners();
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        vpXml.setAdapter(adapter);
        vpXml.setOffscreenPageLimit(4);
        vpXml.setCurrentItem(parseXMLFragment.PULL_PARSE_TYPE);
        vpXml.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rgXml.check(R.id.main_rb_pull);
                        break;
                    case 1:
                        rgXml.check(R.id.main_rb_sax);
                        break;
                    case 2:
                        rgXml.check(R.id.main_rb_dom);
                        break;
                    case 3:
                        rgXml.check(R.id.main_rb_xstream);
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
        rgXml.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb_pull:
                        vpXml.setCurrentItem(parseXMLFragment.PULL_PARSE_TYPE);
                        break;
                    case R.id.main_rb_sax:
                        vpXml.setCurrentItem(parseXMLFragment.SAX_PARSE_TYPE);
                        break;
                    case R.id.main_rb_dom:
                        vpXml.setCurrentItem(parseXMLFragment.DOM_PARSE_TYPE);
                        break;
                    case R.id.main_rb_xstream:
                        vpXml.setCurrentItem(parseXMLFragment.XSTREAM_PARSE_TYPE);
                        break;
                }
            }
        });
    }

    /**
     * 初始化显示 Json 布局
     */
    private void initJsonView() {
        vpXml.setVisibility(View.GONE);
        vpJson.setVisibility(View.VISIBLE);
        vpJson.clearOnPageChangeListeners();
        rgXml.setVisibility(View.GONE);
        rgJson.setVisibility(View.VISIBLE);
        Fragment[] fragments = new Fragment[3];//Fragments
        fragments[0] = parseJsonFragment.newInstance(parseJsonFragment.JSON_PARSE_TYPE);
        fragments[1] = parseJsonFragment.newInstance(parseJsonFragment.GSON_PARSE_TYPE);
        fragments[2] = parseJsonFragment.newInstance(parseJsonFragment.FASTJSON_PARSE_TYPE);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        vpJson.setAdapter(adapter);
        vpJson.setOffscreenPageLimit(3);
        vpJson.setCurrentItem(parseJsonFragment.JSON_PARSE_TYPE);
        vpJson.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rgJson.check(R.id.main_rb_json);
                        break;
                    case 1:
                        rgJson.check(R.id.main_rb_gson);
                        break;
                    case 2:
                        rgJson.check(R.id.main_rb_fast_json);
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
        rgJson.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb_json:
                        vpJson.setCurrentItem(parseJsonFragment.JSON_PARSE_TYPE);
                        break;
                    case R.id.main_rb_gson:
                        vpJson.setCurrentItem(parseJsonFragment.GSON_PARSE_TYPE);
                        break;
                    case R.id.main_rb_fast_json:
                        vpJson.setCurrentItem(parseJsonFragment.FASTJSON_PARSE_TYPE);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_item_xml://解析 XML
                initXMLView();
                break;
            case R.id.menu_item_json://解析 JSON
                initJsonView();
                break;
        }
        return true;
    }

}

