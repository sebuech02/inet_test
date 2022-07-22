package com.example.inet_test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class game_carlosfly extends View{
    private Bitmap background_img;
    private Bitmap carlos[] = new Bitmap[2];
    private Bitmap live[] = new Bitmap[2];
    private Bitmap bier, schnapps, light, light2;
    public int width, height;
    private int carlosX = 10;
    private int carlosY;
    private int carlosspeed;

    private int score, lives;

    private int yellowx, yellowy, yellowspeed=7;
    private Paint yellowpaint = new Paint();
    private int greenx, greeny, greenspeed=10;
    private Paint greenpaint = new Paint();
    private int redx, redy, redspeed=13;
    private Paint redpaint = new Paint();
    private int redx2, redy2, redspeed2=15;
    private Paint redpaint2 = new Paint();

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
        light2 = BitmapFactory.decodeResource(getResources(), R.drawable.light);
        light2 = Bitmap.createScaledBitmap(light2,width/10, height/10, false);
        yellowpaint.setColor(Color.YELLOW);
        yellowpaint.setAntiAlias(false);
        greenpaint.setColor(Color.GREEN);
        greenpaint.setAntiAlias(false);
        redpaint.setColor(Color.RED);
        redpaint.setAntiAlias(false);
        redpaint2.setColor(Color.RED);
        redpaint2.setAntiAlias(false);
        score_paint.setColor(Color.GREEN);
        score_paint.setTextSize(70);
        score_paint.setTypeface(Typeface.DEFAULT_BOLD);
        score_paint.setAntiAlias(true);
        live[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        live[0] = Bitmap.createScaledBitmap(live[0], width/10, height/10, false);
        live[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_broken);
        live[1] = Bitmap.createScaledBitmap(live[1], width/10, height/10, false);
        carlosY = 550;
        score = 0;
        lives = 3;
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
        carlosspeed = carlosspeed + 1;
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
            score = score + 3;
            greenx = -100;
        }
        if (greenx < 0){
            greenx = width + 21;
            greeny = (int) Math.floor(Math.random() * (maxcY-mincY)) + mincY;
        }

        canvas.drawBitmap(bier, yellowx, yellowy, null);
        canvas.drawBitmap(schnapps, greenx, greeny, null);

        redx = redx - redspeed;
        if (hitchecker(redx, redy)){
            //score = score + 3;
            lives = lives - 1;
            if (lives == 0){
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                Intent gameover = new Intent(getContext(), game_over.class);
                gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameover.putExtra("score", score);
                getContext().startActivity(gameover);
            }
            redx = -100;
        }
        if (redx < 0){
            redx = width + 21;
            redy = (int) Math.floor(Math.random() * (maxcY-mincY)) + mincY;
        }

        if (score > 27){
            redx2 = redx2 - redspeed2;
            if (hitchecker(redx2, redy2)){
                //score = score + 3;
                lives = lives - 1;
                if (lives == 0){
                    Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                    Intent gameover = new Intent(getContext(), game_over.class);
                    gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    gameover.putExtra("score", score);
                    getContext().startActivity(gameover);
                }
                redx2 = -100;
            }
            if (redx2 < 0){
                redx2 = width + 21;
                redy2 = (int) Math.floor(Math.random() * (maxcY-mincY)) + mincY;
            }
            canvas.drawBitmap(light2, redx2 ,redy2, null);
        }

        canvas.drawBitmap(light, redx, redy, null);
        canvas.drawText("Score: "+score, 20,60,score_paint);

        for (int i = 0; i<3; i++){
            int x = width-(3-i)*carlos[0].getWidth();
            int y = 10;
            if (i<lives){
                canvas.drawBitmap(live[0],x,y,null);
            } else {
                canvas.drawBitmap(live[1],x,y,null);
            }
        }


    }

    public boolean hitchecker(int x,int y){
        if (carlosX < x && x < (carlosX + carlos[0].getWidth()) && carlosY-schnapps.getHeight() < y && y < (carlosY + carlos[0].getHeight())){
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