package com.example.inet_test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.View;

public class crabby_gameview extends View{
    private Bitmap background_img;
    private int width, height;
    private Bitmap crab;
    private Bitmap pineapple;
    private Bitmap coconut;
    private Bitmap bananas;
    private Bitmap meteor;
    private int crabX, crabY;
    private boolean einmal = true;
    private float input;
    private SensorManager sensorManager;
    private Sensor sensor;

    private int score, lives, counter;

    private int pineappleX, pineappleY, pineappleSpeed=7;
    private Paint pineapplePaint = new Paint();

    public MediaPlayer burp, burp2;
    public MediaPlayer crabrave;

    public crabby_gameview(Context context) {
        super(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        // Hintergrundbild
        background_img = BitmapFactory.decodeResource(getResources(), R.drawable.beach);
        background_img = Bitmap.createScaledBitmap(background_img, width, height, false);


        // Die Früchte und andere Objekte
        pineapple = BitmapFactory.decodeResource(getResources(), R.drawable.ananas1);
        pineapple = Bitmap.createScaledBitmap(pineapple, width/10, height/10, false);


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
        crabY = height-2*crab.getHeight();
        crabX = (width-crab.getWidth())/2;

        // Sounds und Musik
        burp = MediaPlayer.create(getContext(), R.raw.burp);
        crabrave = MediaPlayer.create(getContext(), R.raw.crabrave);
        crabrave.isLooping();
        crabrave.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(background_img,0,0,null);


        // This needs change
        int mincX = crab.getWidth();
        int maxcX = height - 1*crab.getWidth();

        crabX =(int) (crabX + input);
        if (crabX >width-crab.getWidth()){
            crabX = width-crab.getWidth();
        }
        if (crabX <0){
            crabX = 0;
        }

        pineappleY = pineappleY - pineappleSpeed;

        if (hitchecker(pineappleX, pineappleY)){
            burp.start();
            score = score + 1;
            pineappleY = +2000;
        }
        if (pineappleY < -crab.getWidth()){
            pineappleY = width + 21;
            pineappleX = (int) Math.floor(Math.random() * (maxcX-mincX)) + mincX;
        }
        canvas.drawBitmap(crab, crabX, crabY,null);
        canvas.drawBitmap(pineapple, pineappleX, pineappleY, null);
        //Hier findet das Spiel statt. Diese Funktion wird 66 mal pro sekunde aufgerufen
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


}
