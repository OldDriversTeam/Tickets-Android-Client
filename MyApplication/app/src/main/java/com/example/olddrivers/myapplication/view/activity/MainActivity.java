package com.example.olddrivers.myapplication.view.activity;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.olddrivers.myapplication.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentsList;
    private TextView movie_text, setting_text;

    private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
    private int position_one;
    private int position_two;
    private int fragmentExecuteCount;

    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        fragmentExecuteCount = 4;

        resources = getResources();

        InitWidth();

        InitTextView();

        InitViewPager();
    }

    private void InitTextView() {
        movie_text = (TextView) findViewById(R.id.tb_movie);
        setting_text = (TextView) findViewById(R.id.tb_setting);

        movie_text.setOnClickListener(new MyOnClickListener(0));
        setting_text.setOnClickListener(new MyOnClickListener(1));
    }
}
