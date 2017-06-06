package com.example.olddrivers.myapplication.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by bin on 2017/6/6.
 */

public class MainActivityPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragment_list;                         //fragment列表
    private List<String> title_list;                              //tab名的列表

    public MainActivityPagerAdapter(FragmentManager fm,List<Fragment> fragment_list,List<String> title_list) {
        super(fm);
        this.fragment_list = fragment_list;
        this.title_list = title_list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment_list.get(position);
    }

    @Override
    public int getCount() {
        return fragment_list.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return title_list.get(position % title_list.size());
    }
}
