package com.studio71.tourdo.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.studio71.tourdo.R;
import com.studio71.tourdo.applicationIntro.IntroSlider;

public class Splash extends AppCompatActivity {
    ImageView tourDO;
    RelativeLayout relative_layout;

    Animation splash,background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tourDO = findViewById(R.id.tourDo);
        relative_layout = findViewById(R.id.relative_layout);

        splash = AnimationUtils.loadAnimation(this,R.anim.splash);
        background = AnimationUtils.loadAnimation(this,R.anim.background);

        tourDO.setAnimation(splash);
        relative_layout.setAnimation(background);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(Splash.this, IntroSlider.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}

