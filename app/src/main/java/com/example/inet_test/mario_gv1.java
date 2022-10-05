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
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class mario_gv1 extends View {
    private boolean doppelsprung, mario_onthefloor, wall_rechts=false, wall_links=false, wall_oben=false;
    private Bitmap background_img;
    private int width, height;
    private float inputx, inputy;
    private float speedx=0, speedy=0, speedxmax=12, speedymax;
    private float sprungkraft=17, schweerkraft=(float)0.5;
    private boolean touch;
    private SensorManager sensorManager;
    private Sensor sensor;
    public int lives = 3;
    private int framecounter=0;
    private float mariox=0, marioy=0;
    private float camerax;
    private int blocksize;
    private int[] blocksx;
    private int[] blocksy;
    private int tolleranz, tolleranz_block_stehen, tolleranz_block_fallen;
    private Bitmap mario, mario2, block;
    private int blockcheck;
    private boolean once, once2, once3, once4;
    private boolean hohepasst = false;
    private float oldspeed, newspeed;
    private int current_frame=88;
    private float old_camerax, new_camerax;
    private boolean init_cameratransition=false;


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
        /*
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
        blocksx[22]=-22* blocksize;
        blocksy[22]=height-3*blocksize;

         */
        init_level_blocks();

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
        tolleranz=blocksize/5;
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
        once2=false;
        once3=false;
        once4=false;
        mario_move();
        mario_floorcheck();
        mario_hitcheck();
        mario_events();
        if (!init_cameratransition) {
            oldspeed = newspeed;
            newspeed = speedx;
        }

        if ((Math.abs(newspeed-oldspeed)>speedxmax/2)&&(!init_cameratransition)) {
            init_cameratransition = true;
            current_frame = 1;
        }
        //Camera fixen
        /*if (init_cameratransition)
        {
            if (current_frame<16){
                camerax=mariox+(((((16+current_frame)/(15))*(oldspeed))/speedxmax)*width/5);
                canvas.drawBitmap(mario2,blocksize,blocksize,null);
                current_frame++;
            }
            if (current_frame<49&&current_frame>15){
                camerax=mariox+(((((-current_frame+16)/(16))*(newspeed))/speedxmax)*width/5);
                canvas.drawBitmap(mario2,2*blocksize,2*blocksize,null);
                current_frame++;
            }
            if (current_frame>=33) {
                init_cameratransition = false;
            }
            } else {
            camerax = mariox+((speedx/speedxmax)*width/5);
        }*/


        //Alter Camera Wiggle, schrecklich bei zusammenstößen
        //camerax = mariox+((speedx/speedxmax)*width/5);
        camerax = mariox-width/7;
        canvas.drawText("Score: "+score+' '+wall_links+' '+hohepasst+' '+mariox+' '+marioy, 20,60,score_paint);
        canvas.drawBitmap(mario, (camerax-mariox)+width/2, marioy, null);
        //canvas.drawBitmap(mario2, (camerax-0)+width/2, height/2, null);
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
            if (!wall_links){
                mariox=mariox+speedx;
            } else {
                speedx=0;
            }
        }
        if (speedx<0) {
            if (!wall_rechts) {
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
            if ((blocksx[temp]-tolleranz_block_stehen+mario.getWidth()/2 <= mariox+mario.getWidth()/2) && mariox+mario.getWidth()/2<=blocksx[temp]+blocksize+mario.getWidth()/2+tolleranz){
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
                }
            }

            if ((blocksx[temp]-tolleranz_block_fallen+mario.getWidth()/2 <= mariox+mario.getWidth()/2) && mariox+mario.getWidth()/2<=blocksx[temp]+blocksize+mario.getWidth()/2+tolleranz_block_fallen){
                if ((blocksy[temp]+blocksize+blocksize/4>=marioy) && (marioy>=blocksy[temp]+blocksize)){
                    wall_oben = true;
                    once2=true;
                    if (speedy<0) {
                        speedy = 0;
                    }
                    if (marioy<=blocksy[temp]+blocksize){
                        marioy = blocksy[temp] + blocksize;
                    }
                }
            }

            if ((blocksy[temp]<=marioy+mario.getHeight()-tolleranz)&&(blocksy[temp]>=marioy-blocksize+tolleranz)){
                hohepasst=true;
                if ((mariox-mario.getWidth()>=blocksx[temp]-blocksize/4)&&(mariox-mario.getWidth()<=blocksx[temp])){
                    wall_rechts = true;
                    once3=true;
                    if (speedx<0) {
                        speedx = 0;
                    }
                    if (mariox-mario.getWidth()<blocksx[temp]){
                        mariox = blocksx[temp] + mario.getWidth();
                    }
                }
            }
            if ((blocksy[temp]<=marioy+mario.getHeight()-tolleranz)&&(blocksy[temp]>=marioy-blocksize+tolleranz)){
                hohepasst=true;
                if ((mariox<=blocksx[temp]-blocksize+blocksize/4)&&(mariox>=blocksx[temp]-blocksize)){
                    wall_links = true;
                    once4=true;
                    if (speedx>0) {
                        speedx = 0;
                    }
                    if (mariox>blocksx[temp]-blocksize){
                        mariox = blocksx[temp] - blocksize;
                    }
                }
            }

            temp++;
        }
        if (!once) {
            mario_onthefloor=false;
        }
        if (!once2){
            wall_oben=false;
        }
        if (!once3){
            wall_rechts=false;
        }
        if (!once4){
            wall_links=false;
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

    private void init_level_blocks(){
        ArrayList xwerte = new ArrayList<Integer>();
        ArrayList ywerte = new ArrayList<Integer>();
        for (int i=0; i*blocksize<height; i++){
            xwerte.add(3*blocksize);
            ywerte.add(height-(2+i)*blocksize);
        }
        for (int i=2; i>-5; i--){
            xwerte.add(i*blocksize);
            ywerte.add(height-2*blocksize);
        }
        xwerte.add(-6*blocksize);
        ywerte.add(height-2*blocksize);
        xwerte.add(-8*blocksize);
        ywerte.add(height-2*blocksize);
        for (int i=-10; i>-22; i--){
            xwerte.add(i*blocksize);
            ywerte.add(height-2*blocksize);
        }
        xwerte.add(-12*blocksize);
        ywerte.add(height-3*blocksize);
        xwerte.add(-13*blocksize);
        ywerte.add(height-3*blocksize);
        xwerte.add(-13*blocksize);
        ywerte.add(height-4*blocksize);
        xwerte.add(-14*blocksize);
        ywerte.add(height-4*blocksize);
        xwerte.add(-14*blocksize);
        ywerte.add(height-5*blocksize);
        xwerte.add(-15*blocksize);
        ywerte.add(height-5*blocksize);
        xwerte.add(-15*blocksize);
        ywerte.add(height-6*blocksize);
        xwerte.add(-16*blocksize);
        ywerte.add(height-6*blocksize);
        xwerte.add(-16*blocksize);
        ywerte.add(height-7*blocksize);
        xwerte.add(-17*blocksize);
        ywerte.add(height-7*blocksize);
        xwerte.add(-17*blocksize);
        ywerte.add(height-8*blocksize);
        xwerte.add(-18*blocksize);
        ywerte.add(height-8*blocksize);
        xwerte.add(-18*blocksize);
        ywerte.add(height-9*blocksize);

        xwerte.add(-23*blocksize);
        ywerte.add(height-6*blocksize);
        xwerte.add(-25*blocksize);
        ywerte.add(height-2*blocksize);
        xwerte.add(-29*blocksize);
        ywerte.add(height-2*blocksize);

        xwerte.add(-32*blocksize);
        ywerte.add(height-4*blocksize);
        xwerte.add(-33*blocksize);
        ywerte.add(height-4*blocksize);
        xwerte.add(-34*blocksize);
        ywerte.add(height-4*blocksize);
        xwerte.add(-35*blocksize);
        ywerte.add(height-4*blocksize);

        xwerte.add(-44*blocksize);
        ywerte.add(height-2*blocksize);

        xwerte.add(-50*blocksize);
        ywerte.add(height-2*blocksize);
        xwerte.add(-51*blocksize);
        ywerte.add(height-2*blocksize);
        xwerte.add(-52*blocksize);
        ywerte.add(height-2*blocksize);
        xwerte.add(-53*blocksize);
        ywerte.add(height-2*blocksize);
        xwerte.add(-54*blocksize);
        ywerte.add(height-2*blocksize);
        xwerte.add(-55*blocksize);
        ywerte.add(height-2*blocksize);

        for (int i=0; i*blocksize<height; i++){
            xwerte.add(-56*blocksize);
            ywerte.add(height-(2+i)*blocksize);
        }
        int[] bocksx = new int[xwerte.size()];
        int[] bocksy = new int[xwerte.size()];
        blocksx= convertIntegers(xwerte);
        blocksy=convertIntegers(ywerte);
    }
    public static int[] convertIntegers(ArrayList<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

}
