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
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class crabby_gameview extends View{
    private Bitmap background_img;
    private int width, height;
    private Bitmap crab;
    private float crabspeed = 0;
    private float störung;
    private Bitmap pineapple;
    private Bitmap coconut;
    private Bitmap bananas;
    private Bitmap meteor1, meteor2, meteor3;
    private int crabX, crabY;
    private boolean einmal = true;
    private float input;
    private SensorManager sensorManager;
    private Sensor sensor;
    private int framecounter;
    private boolean drunkmode;
    private boolean taumeln_rechts;
    private int duration = 66;
    private Paint score_paint = new Paint();
    private Bitmap live[] = new Bitmap[2];
    private int score, lives, counter=0;
    private boolean gothit = false;
    private Paint paint2 = new Paint();

    private int coconutx, coconuty, coconutspeed=10;
    private int pineappleX, pineappleY, pineappleSpeed=7;
    private int meteorX, meteorY, meteorSpeed=2;

    public MediaPlayer burp, burp2;
    public MediaPlayer explosion;
    public MediaPlayer crabrave;
    private int mincX,maxcX;

    public crabby_gameview(Context context) {
        super(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels+66;
        width = displayMetrics.widthPixels+200;

        // Hintergrundbild
        background_img = BitmapFactory.decodeResource(getResources(), R.drawable.beach);
        background_img = Bitmap.createScaledBitmap(background_img, width, height, false);
        paint2.setColor(Color.RED);
        paint2.setStyle(Paint.Style.FILL);

        // Die Früchte und andere Objekte
        pineapple = BitmapFactory.decodeResource(getResources(), R.drawable.ananas1);
        pineapple = Bitmap.createScaledBitmap(pineapple, width/10, height/10, false);
        coconut = BitmapFactory.decodeResource(getResources(), R.drawable.kokosnuss);
        coconut = Bitmap.createScaledBitmap(coconut, width/10, height/10, false);
        meteor1 = BitmapFactory.decodeResource(getResources(), R.drawable.meteor1);
        meteor1 = Bitmap.createScaledBitmap(meteor1, width/5, height/5, false);
        score_paint.setColor(Color.GREEN);
        score_paint.setTextSize(70);
        score_paint.setTypeface(Typeface.DEFAULT_BOLD);
        score_paint.setAntiAlias(true);
        live[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        live[0] = Bitmap.createScaledBitmap(live[0], width/10, height/10, false);
        live[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_broken);
        live[1] = Bitmap.createScaledBitmap(live[1], width/10, height/10, false);

        // Bewegungssteuerung
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                input = event.values[1];
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, sensor, SensorManager.SENSOR_DELAY_FASTEST);

        //Hier wird das game Initialisiert, Assets laden und anzeigen.
        crab = BitmapFactory.decodeResource(getResources(), R.drawable.niklas_slav2);
        crab = Bitmap.createScaledBitmap(crab, width/10, height/10, false);
        crabY =(int) (height-1.3*crab.getHeight());
        crabX = (width-crab.getWidth())/2;
        crabspeed = 0;
        lives = 3;
        drunkmode = false;
        mincX = 0;
        maxcX = width-crab.getWidth();


        // Sounds und Musik
        burp = MediaPlayer.create(getContext(), R.raw.burp);
        explosion = MediaPlayer.create(getContext(), R.raw.explosion);
        crabrave = MediaPlayer.create(getContext(), R.raw.crabrave_short);
        crabrave.setLooping(true);
        crabrave.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height = canvas.getHeight();
        width = canvas.getWidth();
        canvas.drawBitmap(background_img,0,0,null);
        if (drunkmode){
            if (framecounter>=duration){
                if (taumeln_rechts) {
                    störung =(float) Math.random()/5;
                    framecounter = 0;
                    duration =(int) Math.floor(Math.random()*33);
                    taumeln_rechts = false;
                } else {
                    störung =(float) (Math.random()-1)/5;
                    framecounter = 0;
                    duration =(int) Math.floor(Math.random()*33);
                    taumeln_rechts = true;
                }
            } else {
                framecounter = framecounter + 1;
            }
        }else{
            störung = 0;
        }
        crabspeed = crabspeed + input/30 + störung;

        // This needs change


        if (crabspeed>0){
            crabspeed =(float) (crabspeed - 0.01);
        }
        if (crabspeed<0){
            crabspeed =(float) (crabspeed + 0.01);
        }


        crabX =(int) (crabX + crabspeed);

        if (crabX >width-crab.getWidth()){
            crabX = width-crab.getWidth();
            crabspeed = 0;
        }
        if (crabX <0){
            crabspeed = 0;
            crabX = 0;
        }

        // Ananas
        pineappleY = pineappleY + pineappleSpeed;
        if (hitchecker(pineappleX, pineappleY)){
            burp.start();
            score = score + 1;
            pineappleY =  -2*pineapple.getHeight();
            pineappleX = (int) Math.floor(Math.random() * (maxcX));
        }
        if (pineappleY > height+2*crab.getWidth()){
            pineappleY = -2*pineapple.getHeight();
            pineappleX = (int) Math.floor(Math.random() * (maxcX));
        }

        // Kokosnuss
        coconuty = coconuty + coconutspeed;
        if (hitchecker(coconutx, coconuty)){
            burp.start();
            lives = lives - 1;
            // gothit für hitanimation als Test
            gothit = true;
            coconuty =  -2*coconut.getHeight();
            coconutx = (int) Math.floor(Math.random() * (maxcX));
        }
        if (coconuty > height+2*crab.getWidth()){
            coconuty = -2*coconut.getHeight();
            coconutx = (int) Math.floor(Math.random() * (maxcX));
        }

        // Meteor
        if (((meteorX > -500) || (Math.random() < 0.5))){
            meteorX = meteorX + meteorSpeed/2;
            meteorY = meteorY + meteorSpeed;
            if (hitchecker(meteorX, meteorY)){
                score = 0;
                explosion.start();
                Toast.makeText(getContext(), "Die Dinos sind jetzt wegen dir ausgestorben!", Toast.LENGTH_LONG).show();
                meteorY = -500;
                lives = 0;
            }
            if (meteorY <= -500){
                meteorY = 2*meteor1.getHeight();
                meteorX = (int) Math.floor(Math.random() * (maxcX));
            }
            if (meteorY<-crab.getWidth()){
                meteorY = -500;
            }
            canvas.drawBitmap(meteor1, meteorX, meteorY, null);
        }

        if (gothit) {
            if (counter>44) {
                counter = 0;
                gothit = false;
            } else {
                counter = counter + 1;
            }
        } else {
            counter = 0;
        }
        male_crab(canvas);
        //canvas.drawBitmap(crab, crabX, crabY,null);

        canvas.drawBitmap(pineapple, pineappleX, pineappleY, null);
        canvas.drawBitmap(coconut,coconutx,coconuty,null);
        //Hier findet das Spiel statt. Diese Funktion wird 66 mal pro sekunde aufgerufen
        canvas.drawText("Score: "+score, 20,60,score_paint);

        for (int i = 0; i<3; i++){
            int x = width-(3-i)*crab.getWidth();
            int y = 10;
            if (i<lives){
                canvas.drawBitmap(live[0],x,y,null);
            } else {
                canvas.drawBitmap(live[1],x,y,null);
            }
        }
        if (lives<=0){
            //TODO Toast message fixen (ist der Activity Context kaputt?)
            //Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
            //TODO Music fixen, lässt sich nicht beenden
            //crabrave.stop();
            //crabrave.release();
            Intent gameover = new Intent(getContext(), game_over.class);
            gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            gameover.putExtra("score", score);
            gameover.putExtra("ursache", "Tod");
            //TODO gameover Activity öffnen, aktuell sehr langsam
            getContext().startActivity(gameover);
        }
    }

    public boolean hitchecker(int x,int y){
        if (crabX-pineapple.getWidth() < x && x < (crabX + crab.getWidth()) && crabY-pineapple.getHeight() < y && y < (crabY + crab.getHeight())){
            return true;
        }
        return false;
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    private void male_crab(Canvas canvas){
        if (gothit){
            Paint hitpaint = new Paint();
            ColorFilter filter = new PorterDuffColorFilter(ContextCompat.getColor(getContext(), R.color.red), PorterDuff.Mode.SRC_IN);
            hitpaint.setColorFilter(filter);
            //crab flash red
            canvas.drawBitmap(crab,crabX,crabY,hitpaint);
            //Screen flash red
            canvas.drawPaint(paint2);
        }else {
            canvas.drawBitmap(crab, crabX, crabY,null);
        }
    }

}
