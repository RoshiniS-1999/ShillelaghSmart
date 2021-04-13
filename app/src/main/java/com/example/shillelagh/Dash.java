package com.example.shillelagh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Dash extends AppCompatActivity {

    List<model> models;
    ViewPager viewPager;
    Adapter adapter;
    Integer[] colors=null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        models = new ArrayList<>();
        models.add(new model(R.drawable.compass,"Navigation"));
        models.add(new model(R.drawable.compass,"Object Detection"));
        models.add(new model(R.drawable.compass,"Emergency"));
        models.add(new model(R.drawable.compass,"Navigation"));

        adapter = new Adapter(models,this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color1)
        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position <(adapter.getCount() -1) && position < (colors.length -1)){
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position+1]));
                }
                else{
                    viewPager.setBackgroundColor(colors[colors.length -1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}