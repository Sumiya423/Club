package com.example.cpclubltd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {


    Animation topAnim, bottomAnim;
    ImageView imageView;
    TextView cpcTV,version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        imageView=findViewById(R.id.imageViewSplash);
        cpcTV=findViewById(R.id.textView);
        version=findViewById(R.id.textView1);

        imageView.setAnimation(topAnim);
        cpcTV.setAnimation(bottomAnim);
        version.setAnimation(bottomAnim);

        Thread thread = new Thread(){
            public void run(){
                try{

                    sleep(5000);
                }
                catch(Exception e){

                    e.printStackTrace();
                }
                finally {

                    Intent intent= new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };thread.start();
    }
}