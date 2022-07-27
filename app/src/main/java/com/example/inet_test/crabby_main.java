package com.example.inet_test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.RequiresApi;

import java.util.Timer;
import java.util.TimerTask;

public class crabby_main extends MainActivity{
    private crabby_gameview gameview;
    private Handler handler = new Handler();
    private final static long intervall = 15;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameview = new crabby_gameview(this);
        setContentView(gameview);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameview.invalidate();
                    }
                });
            }
        }, 0, intervall);
    }
}
