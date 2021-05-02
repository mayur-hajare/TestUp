package com.myur.testup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN=5000;

    //variables
    Animation bottomAnim,topAnim;
    ImageView Image;
    TextView tv,textView,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Anim
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        //Hooks
        tv=findViewById(R.id.textview1);
        tv.setAnimation(bottomAnim);

        textView=findViewById(R.id.textView2);
        textView.setAnimation(topAnim);

        textView2=findViewById(R.id.textView3);
        textView2.setAnimation(topAnim);


        //Splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }

}