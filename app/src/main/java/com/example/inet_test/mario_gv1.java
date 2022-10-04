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
    private boolean doppelsprung, mario_onthefloor, wall_rechts=false, wall_links=false, wall_oben=false;
    private Bitmap background_img;
    private int width, height;
    private float inputx, inputy;
    private float speedx=0, speedy=0, speedxmax=17, speedymax;
    private float sprungkraft=17, schweerkraft=(float)0.5;
    private boolean touch;
    private SensorManager sensorManager;
    private Sensor sensor;
    public int lives = 3;
    private int framecounter=0;
    private float mariox=0, marioy=0;
    private float camerax;
    private int blocksize;
    private int[] blocksx = new int[256];
    private int[] blocksy = new int[256];
    private int tolleranz, tolleranz_block_stehen, tolleranz_block_fallen;
    private Bitmap mario, mario2, block;
    private int blockcheck;
    private boolean once;


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
        blocksize = width/33;
        mariox=0;
        int i = 0;
        while (i<222){
            blocksx[i]=-i*blocksize;
            blocksy[i]=height-2*blocksize;
            i++;
        }
        blocksx[222]=blocksize;
        blocksy[222]=height-5*blocksize;
        blocksx[5]=-4* blocksize;
        blocksy[5]=height-10*blocksize;

        mario = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
        mario = Bitmap.createScaledBitmap(mario, width/25, width/25, false);
        mario2 = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
        mario2 = Bitmap.createScaledBitmap(mario2, width/25, width/25, false);
        block = BitmapFactory.decodeResource(getResources(), R.drawable.block);
        block = Bitmap.createScaledBitmap(block, blocksize, blocksize, false);

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
        tolleranz_block_stehen=mario.getWidth()/2;
        tolleranz_block_fallen=mario.getWidth()/4;
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
        once=false;
        mario_move();
        mario_floorcheck();
        mario_hitcheck();
        mario_events();


        camerax = mariox+((speedx/speedxmax)*width/5);

        canvas.drawText("Score: "+score+doppelsprung+' '+speedy
                , 20,60,score_paint);
        canvas.drawBitmap(mario, (camerax-mariox)+width/2, marioy, null);
        canvas.drawBitmap(mario2, (camerax-0)+width/2, height/2, null);
        int j=0;
        while (j<blocksx.length){
            canvas.drawBitmap(block, (camerax-blocksx[j])+width/2, blocksy[j], null);
            j++;
        }

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
        speedy = speedy + schweerkraft;
        //if (speedy>speedymax){
          //  speedy = speedymax;
        //}


        if (speedy>=0){
            if (!mario_onthefloor){
                marioy=marioy+speedy;
            } else {
                doppelsprung=false;
                speedy=0;
            }
        }
        if (speedy<0) {
            if (!wall_oben) {
                marioy = marioy + speedy;
            } else {
                speedy=0;
            }
        }

        if (speedx>=0){
            if (!wall_rechts){
                mariox=mariox+speedx;
            } else {
                speedx=0;
            }
        }
        if (speedx<0) {
            if (!wall_links) {
                mariox = mariox + speedx;
            } else {
                speedx=0;
            }
        }

    }

    public void mario_floorcheck(){
        mario_blockcheck();


        if (marioy-2*mario.getHeight()>height){
            lives--;
            mariox=0;
            marioy=0;
            speedx=0;
            speedy=0;
        }
    }

    public void mario_hitcheck(){

    }

    public void mario_events(){

    }

    public void mario_blockcheck(){
        int temp = 0;
        while (temp<blocksx.length){
            //steht er auf dem Block oder ist genau drunter?
            if ((!once)&&(blocksx[temp]-tolleranz_block_stehen+mario.getWidth()/2 <= mariox+mario.getWidth()/2) && mariox+mario.getWidth()/2<=blocksx[temp]+blocksize+mario.getWidth()/2+tolleranz){
                if ((blocksy[temp]+blocksize/2>=marioy+mario.getHeight()) && (marioy+mario.getHeight()>=blocksy[temp]-blocksize/10)){
                    mario_onthefloor = true;
                    once=true;
                    doppelsprung=false;
                    if (speedy>0) {
                        speedy = 0;
                    }
                    if (marioy+mario.getHeight()>blocksy[temp]){
                        marioy = blocksy[temp] - mario.getHeight();
                    }
                    return;
                }
            }
            temp++;
        }
        if (!once) {
            once=false;
            mario_onthefloor = false;
        }
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
            once = true;
            if (mario_onthefloor) {
                speedy = -sprungkraft;
                doppelsprung=false;
            } else {
                if (!doppelsprung && speedy>-4*sprungkraft/5&& speedy!=0){
                    speedy = -sprungkraft;
                    doppelsprung= true;
                }
            }

        }
        return true;
    }

}
