package com.adionofrei.pressonfix;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Button start,exit;
    private String text="0";
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String TEXT="text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout relativeLayout=findViewById(R.id.layout);
        AnimationDrawable animationDrawable=(AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start()

        start=(Button)findViewById(R.id.button1);
        exit=(Button)findViewById(R.id.button2);

        start.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v){
                Intent myIntent=new Intent(MainActivity.this,TheGame.class);
                MainActivity.this.startActivity(myIntent);
                //if(mySong.isPlaying()==true)
                    //mySong.pause();
            }
        });


        exit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //mySong.pause();
                finish();
            }
        });
    }
}
