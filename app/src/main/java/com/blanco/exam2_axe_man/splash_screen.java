package com.blanco.exam2_axe_man;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        setActionBarTitle("The Axe Man");

        TextView textViewCounter = findViewById(R.id.textView_splash_screen_count);

        String chopCount = String.valueOf(getChopCount());
        textViewCounter.setText(chopCount);


        final Button resetButton = (Button) findViewById(R.id.button_splash_screen_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChopCount();
                Toast.makeText(splash_screen.this, "Chop count has been reset", Toast.LENGTH_SHORT).show();
                TextView textViewCounter = findViewById(R.id.textView_splash_screen_count);
                textViewCounter.setText("0");

            }
        });

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(splash_screen.this, MainActivity.class));
            }
        };

        Timer switch_screen = new Timer();
        switch_screen.schedule(task, 5000);

    }
    public void resetChopCount(){
        SharedPreferences settings = getSharedPreferences("my_prefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        int setCount = 0;
        editor.putInt("chopCount", setCount);
        editor.commit();
    }

    public int getChopCount(){
        SharedPreferences settings = getSharedPreferences("my_prefs", 0);
        int chopCountReturn = settings.getInt("chopCount", 0);
        return chopCountReturn;
    }

    public void setActionBarTitle(String title){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
    }

}
