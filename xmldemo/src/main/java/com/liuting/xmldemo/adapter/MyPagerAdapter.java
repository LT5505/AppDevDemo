package com.liuting.xmldemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Package:com.liuting.xmldemo.adapter
 * author:liuting
 * Date:2017/4/6
 * Desc:ViewPager的适配器
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments;

    public MyPagerAdapter(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
