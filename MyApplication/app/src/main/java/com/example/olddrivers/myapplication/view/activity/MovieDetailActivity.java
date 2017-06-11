package com.example.olddrivers.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.FilmItem;

public class MovieDetailActivity extends AppCompatActivity {

    LinearLayout description_layout;
    ScrollView description_scrollView;
    TextView description_text;
    ImageView description_expand;
    LinearLayout detail_layout;
    ScrollView detail_scrollView;
    TextView detail_text;
    ImageView detail_expand;
    Button btn_buy;

    Bundle bundle_in;
    FilmItem filmItem;
    int default_description_lines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initialize();
        setListener();
    }

    void initialize() {
        description_layout = (LinearLayout) findViewById(R.id.description_layout);
        description_scrollView = (ScrollView) findViewById(R.id.description_scrollview);
        description_text = (TextView) findViewById(R.id.description_view);
        description_expand = (ImageView) findViewById(R.id.expand_view);
        detail_layout = (LinearLayout) findViewById(R.id.detail_layout);
        detail_scrollView = (ScrollView) findViewById(R.id.detail_scrollview);
        detail_text = (TextView) findViewById(R.id.detail_view);
        detail_expand = (ImageView) findViewById(R.id.detail_expand_view);
        btn_buy = (Button) findViewById(R.id.btn_toBuying);

        /*bundle_in = getIntent().getExtras();
        movie = Movies.get(bundle_in.getInt("position"));*/

        filmItem = new FilmItem("神奇女侠", null, null);

        default_description_lines = 5;
        description_text.setHeight(description_text.getLineHeight() * default_description_lines);
        detail_text.setHeight(detail_text.getLineHeight() * default_description_lines);

        description_text.post(new Runnable() {
            @Override
            public void run() {
                description_expand.setVisibility(description_text.getLineCount() > default_description_lines ? View.VISIBLE : View.GONE);
            }
        });

        detail_text.post(new Runnable() {
            @Override
            public void run() {
                detail_expand.setVisibility(detail_text.getLineCount() > default_description_lines ? View.VISIBLE : View.GONE);
            }
        });

    }

    void setListener() {

        description_expand.setOnClickListener(new View.OnClickListener() {
            boolean isExpand;//是否已展开的状态

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                description_text.clearAnimation();//清楚动画效果
                final int deltaValue;//默认高度，即前边由maxLine确定的高度
                final int startValue = description_text.getHeight();//起始高度
                int durationMillis = 350;//动画持续时间
                if (isExpand) {
                    /**
                     * 折叠动画
                     * 从实际高度缩回起始高度
                     */
                    deltaValue = description_text.getLineHeight() * description_text.getLineCount() - startValue;
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    description_expand.startAnimation(animation);
                } else {
                    /**
                     * 展开动画
                     * 从起始高度增长至实际高度
                     */
                    deltaValue = description_text.getLineHeight() * default_description_lines - startValue;
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    description_expand.startAnimation(animation);
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                        description_text.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }
                };
                animation.setDuration(durationMillis);
                description_text.startAnimation(animation);
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, CinemaChoosingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("FilmItem", filmItem);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        detail_expand.setOnClickListener(new View.OnClickListener() {
            boolean isExpand;//是否已展开的状态

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                detail_text.clearAnimation();//清楚动画效果
                final int deltaValue;//默认高度，即前边由maxLine确定的高度
                final int startValue = detail_text.getHeight();//起始高度
                int durationMillis = 350;//动画持续时间
                if (isExpand) {
                    /**
                     * 折叠动画
                     * 从实际高度缩回起始高度
                     */
                    deltaValue = detail_text.getLineHeight() * detail_text.getLineCount() - startValue;
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    detail_expand.startAnimation(animation);
                } else {
                    /**
                     * 展开动画
                     * 从起始高度增长至实际高度
                     */
                    deltaValue = detail_text.getLineHeight() * default_description_lines - startValue;
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    detail_expand.startAnimation(animation);
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                        detail_text.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }
                };
                animation.setDuration(durationMillis);
                detail_text.startAnimation(animation);
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, CinemaChoosingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("FilmItem", filmItem);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

}
