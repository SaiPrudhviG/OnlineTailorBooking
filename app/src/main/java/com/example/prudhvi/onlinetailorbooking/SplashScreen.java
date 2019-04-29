package com.example.prudhvi.onlinetailorbooking;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
ImageView imageView;
Handler handler;
int SPLASH_TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView=findViewById(R.id.splashimg);
        Animation myanim=AnimationUtils.loadAnimation(this, R.anim.animation);
        imageView.startAnimation(myanim);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent n=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(n);
                finish();
            }
        },SPLASH_TIME);

    }
}
