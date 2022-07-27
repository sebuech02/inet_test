package com.example.inet_test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;
import android.view.View;

public class crabby_gameview extends View{
    private int width, height;
    private Bitmap crab;
    private int crabx, craby;
    private float crabspeed = 0;
    private boolean einmal = true;
    private float input;
    private SensorManager sensorManager;
    private Sensor sensor;

    public crabby_gameview(Context context) {
        super(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                input = event.values[1];
                crabspeed = crabspeed + input/20;
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        //Hier wird das game Initialisiert, Assets laden und anzeigen.
        crab = BitmapFactory.decodeResource(getResources(), R.drawable.carlrot);
        crab = Bitmap.createScaledBitmap(crab, width/10, height/10, false);
        craby = height-2*crab.getHeight();
        crabx = (width-crab.getWidth())/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (crabspeed>0){
            crabspeed =(float) (crabspeed - 0.2);
        }
        if (crabspeed<0){
            crabspeed =(float) (crabspeed + 0.2);
        }
        crabx =(int) (crabx + crabspeed);
        if (crabx>width-crab.getWidth()){
            crabx = width-crab.getWidth();
            crabspeed = 0;
        }
        if (crabx<0){
            crabx = 0;
            crabspeed = 0;
        }
        canvas.drawBitmap(crab, crabx, craby,null);
        //Hier findet das Spiel statt. Diese Funktion wird 66 mal pro sekunde aufgerufen
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }


}
