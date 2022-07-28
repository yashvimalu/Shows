package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Animation a1;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        a1 = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        iv = findViewById(R.id.imageView);
        iv.setAnimation(a1);

        int SPLASH_SCREEN = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Welcome.class);

                ActivityOptions options  = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,iv,"logo_image");
                startActivity(intent,options.toBundle());

            }
        }, SPLASH_SCREEN);
    }
}