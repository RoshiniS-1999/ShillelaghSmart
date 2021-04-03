package com.example.shillelagh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN=5000;

    Animation topanim;
    ImageView image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        image1 = (ImageView)findViewById(R.id.title);
        image1.setAnimation(topanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent x = new Intent(getApplicationContext(),Login.class);
                startActivity(x);
                finish();
            }
        },SPLASH_SCREEN);
    }
}
