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
    private Bitmap pilger;
    private Bitmap bier, schnapps, light, light2, vodka_e, wasser;
    public int width, height;
    private int carlosX = 10;
    private int carlosY;
    private int carlosspeed;
    private boolean vodkamode = false;
    private boolean redc = true;

    private int score, lives;
    private int counter;

    private int yellowx, yellowy, yellowspeed=7;
    private Paint yellowpaint = new Paint();
    private int greenx, greeny, greenspeed=10;
    private Paint greenpaint = new Paint();
    private int redx, redy, redspeed=13;
    private Paint redpaint = new Paint();
    private int redx2, redy2, redspeed2=15;
    private Paint redpaint2 = new Paint();
    private int vodkax = -500, vodkay, vodkaspeed = 17;
    private int wasserx = -500, wassery, wasserspeed = 20;
    private int pilgerx, pilgery, pilgerspeed = 16;
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
        vodka_e = BitmapFactory.decodeResource(getResources(), R.drawable.vodka_e);
        vodka_e = Bitmap.createScaledBitmap(vodka_e,width/10, height/10, false);
        wasser = BitmapFactory.decodeResource(getResources(), R.drawable.wasser);
        wasser = Bitmap.createScaledBitmap(wasser,width/10, height/10, false);
        pilger = BitmapFactory.decodeResource(getResources(), R.drawable.pilger);
        pilger = Bitmap.createScaledBitmap(pilger,width/10, height/10, false);
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
        counter = 0;
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
        if (carlosY < mincY-3*carlos[0].getHeight()){
            carlosY = mincY-3*carlos[0].getHeight();
        }
        if (carlosY > maxcY){
            carlosY = maxcY;
        }
        carlosspeed = carlosspeed + 1;
        //Animation zu Carlos speed und ausrichtung einbauen

        if (vodkamode){
            counter = counter  + 1;
            if (counter%12<7){
                canvas.drawBitmap(carlos[1], carlosX, carlosY, null);
                redc = false;
            } else {
                canvas.drawBitmap(carlos[0], carlosX, carlosY, null);
                redc = true;
            }

        } else {
            redc = true;
            canvas.drawBitmap(carlos[0], carlosX, carlosY, null);
        }
        if (counter>=420){
            vodkamode = false;
            counter  = 0;
        }

        //if (touch || carlosspeed<0){
        //    canvas.drawBitmap(carlos[0], carlosX, carlosY, null);
        //    touch = false;
        //} else {
        //    canvas.drawBitmap(carlos[0], carlosX, carlosY, null);
        //}

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

        if (score > 50){
            pilgerx = pilgerx - pilgerspeed;
            if (hitchecker(pilgerx, pilgery)){
                score = score + 2;
                pilgerx = -100;
            }
            if (pilgerx < 0){
                pilgerx = width + 21;
                pilgery = (int) Math.floor(Math.random() * (maxcY-mincY)) + mincY;
            }
            canvas.drawBitmap(pilger, pilgerx ,pilgery, null);
        }

        redx = redx - redspeed;
        if (hitchecker(redx, redy) && !vodkamode){
            //score = score + 3;
            lives = lives - 1;
            if (lives == 0){
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                Intent gameover = new Intent(getContext(), game_over.class);
                gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameover.putExtra("score", score);
                gameover.putExtra("ursache", "Cola");
                getContext().startActivity(gameover);
            }
            redx = -100;
        }
        if (redx < 0){
            redx = width + 21;
            redy = (int) Math.floor(Math.random() * (maxcY-mincY)) + mincY;
        }
        canvas.drawBitmap(light, redx, redy, null);

        if (score > 40){
            redx2 = redx2 - redspeed2;
            if (hitchecker(redx2, redy2) && !vodkamode){
                if (!vodkamode) {
                    //score = score + 3;
                    lives = lives - 1;
                    if (lives == 0) {
                        Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                        Intent gameover = new Intent(getContext(), game_over.class);
                        gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        gameover.putExtra("score", score);
                        gameover.putExtra("ursache", "Cola");
                        getContext().startActivity(gameover);
                    }
                }
                redx2 = -100;
            }
            if (redx2 < 0){
                redx2 = width + 21;
                redy2 = (int) Math.floor(Math.random() * (maxcY-mincY)) + mincY;
            }
            canvas.drawBitmap(light2, redx2 ,redy2, null);
        }


        if (((wasserx > -500) || (Math.random() < 0.001)) && score > 60){
            wasserx = wasserx - wasserspeed;
            if (hitchecker(wasserx, wassery)){
                //score = score + 3;
                if (!vodkamode){
                    lives = 0;
                    Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                    Intent gameover = new Intent(getContext(), game_over.class);
                    gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    gameover.putExtra("score", score);
                    gameover.putExtra("ursache", "Wasser");
                    getContext().startActivity(gameover);
                }
                wasserx = -500;
            }
            if (wasserx <= -500){
                wasserx = width + 21;
                wassery = (int) Math.floor(Math.random() * (maxcY-mincY)) + mincY;
            }
            if (wasserx<0){
                wasserx = -500;
            }
            canvas.drawBitmap(wasser, wasserx, wassery, null);
        }

        if (((vodkax > -500) || (Math.random() < 0.001)) && !vodkamode){
            vodkax = vodkax - vodkaspeed;
            if (hitchecker(vodkax, vodkay)){
                //score = score + 3;
                vodkamode = true;
                Toast.makeText(getContext(), "Vodka E!!!", Toast.LENGTH_SHORT).show();
                vodkax = -500;
            }
            if (vodkax <= -500){
                vodkax = width + 21;
                vodkay = (int) Math.floor(Math.random() * (maxcY-mincY)) + mincY;
            }
            if (vodkax<0){
                vodkax = -500;
            }
            canvas.drawBitmap(vodka_e, vodkax, vodkay, null);
        }

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
