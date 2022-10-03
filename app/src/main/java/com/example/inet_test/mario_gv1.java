package com.example.inet_test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class mario_gv1 extends View {
    private boolean doppelsprung=true, mario_onthefloor=false;
    private Bitmap background_img;
    private int width, height;
    private float inputx, inputy;
    private float speedx=0, speedy=0, speedxmax=17;
    private float sprungkraft=17, schweerkraft=(float)0.5;
    private boolean touch;
    private SensorManager sensorManager;
    private Sensor sensor;
    public int lives = 3;
    private int framecounter=0;
    private float mariox=0, marioy=0;
    private float camerax;
    private Bitmap mario, mario2;

    private boolean drunkmode;
    private boolean taumeln_rechts;
    private int duration = 66;
    private Paint score_paint = new Paint();
    private Bitmap live[] = new Bitmap[2];
    private int score, counter=0;
    private boolean gothit = false;
    private Paint paint2 = new Paint();

    private int coconutx, coconuty, coconutspeed=10;
    private int pineappleX, pineappleY, pineappleSpeed=7;
    private int meteorX, meteorY, meteorSpeed=2;

    public MediaPlayer burp, burp2;
    public MediaPlayer explosion;
    public MediaPlayer crabrave;
    private int mincX,maxcX;

    public mario_gv1(Context context) {
        super(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels+66;
        width = displayMetrics.widthPixels+200;
        mario = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
        mario = Bitmap.createScaledBitmap(mario, width/25, width/25, false);
        mario2 = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
        mario2 = Bitmap.createScaledBitmap(mario2, width/25, width/25, false);

        background_img = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
        background_img = Bitmap.createScaledBitmap(background_img, width, height, false);
        paint2.setColor(Color.RED);
        paint2.setStyle(Paint.Style.FILL);
        score_paint.setColor(Color.GREEN);
        score_paint.setTextSize(70);
        score_paint.setTypeface(Typeface.DEFAULT_BOLD);
        score_paint.setAntiAlias(true);
        live[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        //live[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        live[0] = Bitmap.createScaledBitmap(live[0], width/10, height/10, false);
        live[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_broken);
        //live[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_broken);
        live[1] = Bitmap.createScaledBitmap(live[1], width/10, height/10, false);

        // Bewegungssteuerung
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                inputx = event.values[1];
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, sensor, SensorManager.SENSOR_DELAY_FASTEST);

        //Hier wird das game Initialisiert, Assets laden und anzeigen.


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        framecounter = framecounter%66;
        height = canvas.getHeight();
        width = canvas.getWidth();
        canvas.drawBitmap(background_img,0,0,null);
        mario_move();
        mario_floorcheck();
        mario_hitcheck();
        mario_events();


        camerax = mariox+((speedx/speedxmax)*width/5);

        canvas.drawBitmap(mario, (camerax-mariox)+width/2, marioy, null);
        canvas.drawBitmap(mario2, (camerax-0)+width/2, height/2, null);
        framecounter ++;


        for (int i = 0; i<3; i++){
            int x = width-(3-i)*(width/10);
            int y = 10;
            if (i<lives){
                canvas.drawBitmap(live[0],x,y,null);
            } else {
                canvas.drawBitmap(live[1],x,y,null);
            }
        }


    }

    public void mario_move(){
        if (speedx - inputx / 10<=speedxmax && speedx - inputx / 10>=-speedxmax) {
            speedx = speedx - inputx / 10;
        }
        if (!mario_onthefloor){
            speedy = speedy + schweerkraft;
            marioy=marioy+speedy;
        } else {
            if (speedy<0){
                marioy=marioy+speedy;
            }else {
                speedy = 0;
            }
        }
        if (!wallchecker(speedx)){
            mariox=mariox+speedx;
        }

    }

    public void mario_floorcheck(){
        if (marioy+1.2*mario.getHeight()>=height){
            mario_onthefloor=true;
            doppelsprung=false;
        }
        else {
            mario_onthefloor=false;
        }

    }

    public void mario_hitcheck(){

    }

    public void mario_events(){

    }


    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public boolean wallchecker (float speed){

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touch = true;
            if (mario_onthefloor) {
                speedy = -sprungkraft;
            } else {
                if (!doppelsprung){
                    speedy = -sprungkraft;
                    doppelsprung= true;
                }
            }
        }
        return true;
    }

}
