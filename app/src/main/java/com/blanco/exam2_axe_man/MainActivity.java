package com.blanco.exam2_axe_man;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setActionBarTitle("The Axe Man");
        addChopCount(1);
        play_sound();
        animate_tree();
        animate_axe();
        animate_axe2();
        runDelay();

    }

    public void animate_tree(){
        ImageView tree = findViewById(R.id.activity_main_tree);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation_falling_tree); //this comes from the previously made resource
        tree.startAnimation(animation);
    }

    public void animate_axe(){
        ImageView axe = findViewById(R.id.activity_main_axe);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation_axe_throw); //this comes from the previously made resource
        axe.startAnimation(animation);
    }
    public void animate_axe2(){
        ImageView axe = findViewById(R.id.activity_main_axe2);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation_axe_throw2); //this comes from the previously made resource
        axe.startAnimation(animation);
    }

    public void play_sound() {
        final MediaPlayer falling_tree_sound = MediaPlayer.create(this, R.raw.falling);
        falling_tree_sound.setVolume(1.0f, 1.0f);
        falling_tree_sound.start();
    }

    public void addChopCount(int addChop){
        SharedPreferences settings = getSharedPreferences("my_prefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        int currentChopCount = getChopCount();
        currentChopCount = currentChopCount + addChop;
        editor.putInt("chopCount", currentChopCount);
        editor.commit();
    }

    public int getChopCount(){
        SharedPreferences settings = getSharedPreferences("my_prefs", 0);
        int chopCountReturn = settings.getInt("chopCount", 0);
        return chopCountReturn;
    }

    public void runDelay(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 7500);
    }

    public void setActionBarTitle(String title){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
    }

}
