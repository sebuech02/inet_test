package com.example.inet_test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class game_carlosfly extends View{
    private Bitmap background_img;
    private Bitmap carlos[] = new Bitmap[2];
    private Bitmap live[] = new Bitmap[2];
    private Bitmap bier, schnapps, light;
    public int width, height;
    private int carlosX = 10;
    private int carlosY;
    private int carlosspeed;

    private int score;

    private int yellowx, yellowy, yellowspeed=16;
    private Paint yellowpaint = new Paint();
    private int greenx, greeny, greenspeed=20;
    private Paint greenpaint = new Paint();

    private Paint score_paint = new Paint();
    private boolean touch = false;

    public game_carlosfly(Context context) {
        super(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        carlos[0] = BitmapFactory.decodeResource(getResources(), R.drawable.carlrot);
        carlos[0] = Bitmap.createScaledBitmap(carlos[0], width/10, height/10, false);
        carlos[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bild1);
        carlos[1] = Bitmap.createScaledBitmap(carlos[1], width/10, height/10, false);
        background_img = BitmapFactory.decodeResource(getResources(), R.drawable.sky);
        background_img = Bitmap.createScaledBitmap(background_img, width, height, false);
        bier = BitmapFactory.decodeResource(getResources(), R.drawable.bier);
        bier = Bitmap.createScaledBitmap(bier, width/10, height/10, false);
        schnapps = BitmapFactory.decodeResource(getResources(), R.drawable.schnapps);
        schnapps = Bitmap.createScaledBitmap(schnapps, width/10, height/10, false);
        light = BitmapFactory.decodeResource(getResources(), R.drawable.light);
        light = Bitmap.createScaledBitmap(light, width/10, height/10, false);
        yellowpaint.setColor(Color.YELLOW);
        yellowpaint.setAntiAlias(false);
        greenpaint.setColor(Color.GREEN);
        greenpaint.setAntiAlias(false);
        score_paint.setColor(Color.GREEN);
        score_paint.setTextSize(70);
        score_paint.setTypeface(Typeface.DEFAULT_BOLD);
        score_paint.setAntiAlias(true);
        live[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        live[0] = Bitmap.createScaledBitmap(live[0], width/10, height/10, false);
        live[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_broken);
        live[1] = Bitmap.createScaledBitmap(live[0], width/10, height/10, false);
        carlosY = 550;
        score = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = canvas.getWidth();
        height = canvas.getHeight();
        canvas.drawBitmap(background_img,0,0,null);


        int mincY = carlos[0].getHeight();
        int maxcY = height - 1*carlos[0].getHeight();
        carlosY = carlosY + carlosspeed;
        if (carlosY < mincY){
            carlosY = mincY;
        }
        if (carlosY > maxcY){
            carlosY = maxcY;
        }
        carlosspeed = carlosspeed + 2;
        if (touch || carlosspeed<0){
            canvas.drawBitmap(carlos[1], carlosX, carlosY, null);
            touch = false;
        } else {
            canvas.drawBitmap(carlos[0], carlosX, carlosY, null);
        }

        yellowx = yellowx - yellowspeed;

        if (hitchecker(yellowx, yellowy)){
            score = score + 1;
            yellowx = -100;
        }
        if (yellowx < 0){
            yellowx = width + 21;
            yellowy = (int) Math.floor(Math.random() * (maxcY-mincY)) + mincY;
        }
        greenx = greenx - greenspeed;
        if (hitchecker(greenx, greeny)){
            score = score + 2;
            greenx = -100;
        }
        if (greenx < 0){
            greenx = width + 21;
            greeny = (int) Math.floor(Math.random() * (maxcY-mincY)) + mincY;
        }

        canvas.drawBitmap(bier, yellowx, yellowy, null);
        canvas.drawBitmap(schnapps, greenx, greeny, null);
        canvas.drawText("Score: "+score, 20,60,score_paint);
        canvas.drawBitmap(live[0],width-carlos[0].getWidth(),10,null);
        canvas.drawBitmap(live[0],width-2*carlos[0].getWidth(),10,null);
        canvas.drawBitmap(live[0],width-3*carlos[0].getWidth(),10,null);
    }

    public boolean hitchecker(int x,int y){
        if (carlosX < x && x < (carlosX + carlos[0].getWidth()) && carlosY-bier.getHeight() < y && y < (carlosY + carlos[0].getHeight())){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touch = true;
            carlosspeed = -22;
        }
        return true;
    }
}
