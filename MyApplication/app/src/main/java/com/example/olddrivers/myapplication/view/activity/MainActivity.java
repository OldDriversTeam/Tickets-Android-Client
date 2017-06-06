package com.example.olddrivers.myapplication.view.activity;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.view.adapter.MainActivityPagerAdapter;
import com.example.olddrivers.myapplication.view.fragment.MovieListFragment;
import com.example.olddrivers.myapplication.view.fragment.SettingFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentPagerAdapter fAdapter;

    private ArrayList<Fragment> fragmentsList;
    private ArrayList<String> titleList;

    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resources = getResources();

        InitViewPager();
    }

    private void InitViewPager() {
        tabLayout = (TabLayout)findViewById(R.id.main_tab);
        viewPager = (ViewPager)findViewById(R.id.main_pager);

        //初始化各fragment
        MovieListFragment movieListFragment = new MovieListFragment();
        SettingFragment settingFragment = new SettingFragment();

        //将fragment装进列表中
        fragmentsList = new ArrayList<>();
        fragmentsList.add(movieListFragment);
        fragmentsList.add(settingFragment);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        titleList = new ArrayList<>();
        titleList.add("电影");
        titleList.add("我的");

        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        for (int i = 0; i < titleList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titleList.get(i)));
        }

        fAdapter = new MainActivityPagerAdapter(getSupportFragmentManager(),fragmentsList,titleList);

        //viewpager加载adapter
        viewPager.setAdapter(fAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(viewPager);
        //tab_FindFragment_title.set
    }

}
