package com.adionofrei.pressonfix;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

import static android.provider.Telephony.Mms.Part.TEXT;

public class TheGame extends AppCompatActivity {

    //MediaPlayer mySong;
    int point=0;
    int number=0,thehigh=0;
    TextView textView,pointer,highsc;
    Button lucky;
    private String text="0";
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String TEXT="text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_game);

        RelativeLayout relativeLayout=findViewById(R.id.layout);
        AnimationDrawable animationDrawable=(AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        //mySong=MediaPlayer.create(TheGame.this,R.raw.untitled);
        textView = (TextView) findViewById(R.id.textView1);
        lucky = (Button) findViewById(R.id.button1);
        pointer=(TextView)findViewById(R.id.pointxml);
        highsc=(TextView)findViewById(R.id.highscorehere);

       /* if(mySong.isPlaying()==true) {
            mySong.setLooping(false);
            mySong.pause();
        }
        mySong.start();
        mySong.setLooping(true);*/

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(200);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                textView.setText(number + "");
                                number++;
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

        lucky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number%10==0 && number!=0)
                {
                    point++;
                    pointer.setText(point+"");
                    if(point>thehigh)
                        thehigh=point;
                    highsc.setText(thehigh+"");
                    saveData();
                }
                else {
                    number = 0;
                    point=0;
                    pointer.setText(point+"");
                }
            }
        });
        loadData();
        updateData();
    }


    public void saveData()
    {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(TEXT, highsc.getText().toString());
        editor.apply();
    }

    public void loadData()
    {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        text=sharedPreferences.getString(TEXT,"");
    }

    public void updateData()
    {
        highsc.setText(text);
        thehigh=strToInt(text);
    }

    public static int strToInt( String str ){
        int i = 0;
        int num = 0;

        while( i < str.length()) {
            num *= 10;
            num += str.charAt(i++) - '0';
        }
        return num;
    }
}
