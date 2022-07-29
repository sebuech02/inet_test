package com.example.inet_test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class game_carlosfly extends View{
    private Bitmap background_img;
    private Bitmap carlos[] = new Bitmap[2];
    private Bitmap carlos_anpas[] = new Bitmap[2];
    private Bitmap carlos1[] = new Bitmap[360];
    private Bitmap carlos2[] = new Bitmap[360];
    private Bitmap live[] = new Bitmap[2];
    private Bitmap pilger;
    private Bitmap majo;
    private Bitmap bier, schnapps, light, light2, vodka_e, wasser;
    public int width, height;
    private int carlosX = 10;
    private float carlosY;
    private float carlosspeed;
    private boolean vodkamode = false;
    private boolean redc = true;
    private boolean majomode = false;

    private int score, lives;
    private int counter;

    private int majox=-500, majoy, majospeed=11;
    private int yellowx, yellowy, yellowspeed=7;
    private Paint yellowpaint = new Paint();
    private int greenx, greeny, greenspeed=10;
    private Paint greenpaint = new Paint();
    private int redx, redy, redspeed=13;
    private Paint redpaint = new Paint();
    private int redx2 = -500, redy2, redspeed2=15;
    private Paint redpaint2 = new Paint();
    private int vodkax = -500, vodkay, vodkaspeed = 17;
    private int wasserx = -500, wassery, wasserspeed = 20;
    private int pilgerx = -500, pilgery, pilgerspeed = 16;
    private Paint score_paint = new Paint();
    private boolean touch = false;
    private float speedfac1, speedfac2;
    public MediaPlayer burp, burp2;
    public MediaPlayer hit, hit2, hit3;
    public MediaPlayer vode;
    public MediaPlayer back;
    public MediaPlayer schrei;
    public MediaPlayer mexiko;
    private float gravity = 1, updraft = 22;

    private boolean weltallmode = false, weltallinit = true;
    private int weltallcounter=0;
    private Bitmap weltallback;
    private boolean mustafarmode = false, mustafarinit = true;
    private int mustafarcounter=0;
    private Bitmap mustafarback;

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
        int zahler = 0;
        while (zahler < 360){
            carlos1[zahler] = RotateBitmap(carlos[0], zahler);
            carlos2[zahler] = RotateBitmap(carlos[1], zahler);
            zahler = zahler + 1;
        }
        majo = BitmapFactory.decodeResource(getResources(), R.drawable.majo);
        majo = Bitmap.createScaledBitmap(majo, width/10, height/10, false);
        background_img = BitmapFactory.decodeResource(getResources(), R.drawable.sky);
        background_img = Bitmap.createScaledBitmap(background_img, width, height, false);
        weltallback = BitmapFactory.decodeResource(getResources(), R.drawable.all);
        weltallback = Bitmap.createScaledBitmap(weltallback, width, height, false);
        mustafarback = BitmapFactory.decodeResource(getResources(), R.drawable.mustafar);
        mustafarback = Bitmap.createScaledBitmap(mustafarback, width, height, false);
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
        speedfac1 =(float) -3.5;
        speedfac2 =(float) 1.5;

        burp = MediaPlayer.create(getContext(), R.raw.burp);
        burp2 = MediaPlayer.create(getContext(), R.raw.burp2);
        hit = MediaPlayer.create(getContext(), R.raw.hit);
        hit2 = MediaPlayer.create(getContext(), R.raw.hit3);
        hit3 = MediaPlayer.create(getContext(), R.raw.hit2);
        vode = MediaPlayer.create(getContext(), R.raw.vode);
        schrei = MediaPlayer.create(getContext(),R.raw.schrei);
        mexiko = MediaPlayer.create(getContext(), R.raw.mex);
        mexiko.isLooping();
        back = MediaPlayer.create(getContext(), R.raw.back);
        back.isLooping();
        back.start();

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
            carlosspeed = 0;
            carlosY = maxcY;
        }
        carlosspeed = (carlosspeed + gravity);
        //Animation zu Carlos speed und ausrichtung einbauen
        if (majomode && !vodkamode){
            back.stop();
            mexiko.start();
        }
        if (majomode){
            speedfac1 =(float) -16.5;
            speedfac2 =(float) 12;
        }
        if (carlosspeed<0){
            int temp = (int) ((Math.abs(carlosspeed))*(speedfac1)+360);
            carlos_anpas[0] = carlos1[(int)Math.floor(temp)%360];
            carlos_anpas[1] = carlos2[(int)Math.floor(temp)%360];
            male_carlos(canvas,carlos_anpas);
        } else {
            int temp = (int) (Math.abs(carlosspeed)*(speedfac2));
            carlos_anpas[0] = carlos1[(int)Math.floor(temp)%360];
            carlos_anpas[1] = carlos2[(int)Math.floor(temp)%360];
            male_carlos(canvas,carlos_anpas);
        }




        if (counter>=888){
            vodkamode = false;
            vode.pause();
            if (majomode){
                mexiko.start();
                back.stop();
            } else{
                back.start();
            }

            counter  = 0;
        }

        //if (touch || carlosspeed<0){
        //    canvas.drawBitmap(carlos[0], carlosX, carlosY, null);
        //    touch = false;
        //} else {
        //    canvas.drawBitmap(carlos[0], carlosX, carlosY, null);
        //}
        if (!weltallmode && !mustafarmode) {
            yellowx = yellowx - yellowspeed;

            if (hitchecker(yellowx, yellowy)) {
                burp.start();
                score = score + 1;
                yellowx = -100;
            }
            if (yellowx < -carlos[0].getWidth()) {
                yellowx = width + 21;
                yellowy = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
            }
            greenx = greenx - greenspeed;
            if (hitchecker(greenx, greeny)) {
                burp2.start();
                score = score + 3;
                greenx = -100;
            }
            if (greenx < -carlos[0].getWidth()) {
                greenx = width + 21;
                greeny = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
            }

            canvas.drawBitmap(bier, yellowx, yellowy, null);
            canvas.drawBitmap(schnapps, greenx, greeny, null);

            if (score > 50) {
                pilgerx = pilgerx - pilgerspeed;
                if (hitchecker(pilgerx, pilgery)) {
                    burp.start();
                    score = score + 2;
                    pilgerx = -100;
                }
                if (pilgerx < -carlos[0].getWidth()) {
                    pilgerx = width + 21;
                    pilgery = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                }
                canvas.drawBitmap(pilger, pilgerx, pilgery, null);
            }

            if (((majox > -500) || (Math.random() < 0.00005)) && !majomode) {
                majox = majox - majospeed;
                if (hitchecker(majox, majoy)) {
                    score = score + 22;
                    majomode = true;
                    schrei.start();
                    Toast.makeText(getContext(), "MAJONÄSE!!!", Toast.LENGTH_SHORT).show();
                    majox = -500;
                    if (lives < 3) {
                        lives = lives + 1;
                    }
                }
                if (majox <= -500 && !majomode) {
                    majox = width + 21;
                    majoy = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                }
                if (majox < -carlos[0].getWidth()) {
                    majox = -500;
                }
                canvas.drawBitmap(majo, majox, majoy, null);
            }

            redx = redx - redspeed;
            if (hitchecker(redx, redy) && !vodkamode) {
                //score = score + 3;
                hit.start();
                lives = lives - 1;
                if (lives == 0) {
                    Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                    Intent gameover = new Intent(getContext(), game_over.class);
                    back.stop();
                    vode.stop();
                    mexiko.stop();
                    gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    gameover.putExtra("score", score);
                    gameover.putExtra("ursache", "Cola");
                    getContext().startActivity(gameover);
                }
                redx = -100;
            }
            if (redx < -carlos[0].getWidth()) {
                redx = width + 21;
                redy = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
            }
            canvas.drawBitmap(light, redx, redy, null);

            if (score > 40) {
                redx2 = redx2 - redspeed2;
                if (hitchecker(redx2, redy2) && !vodkamode) {
                    if (!vodkamode) {
                        //score = score + 3;
                        lives = lives - 1;
                        hit3.start();
                        if (lives == 0) {
                            Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                            Intent gameover = new Intent(getContext(), game_over.class);
                            back.stop();
                            mexiko.stop();
                            vode.stop();
                            gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            gameover.putExtra("score", score);
                            gameover.putExtra("ursache", "Cola");
                            getContext().startActivity(gameover);
                        }
                    }
                    redx2 = -100;
                }
                if (redx2 < -carlos[0].getWidth()) {
                    redx2 = width + 21;
                    redy2 = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                }
                canvas.drawBitmap(light2, redx2, redy2, null);
            }


            if (((wasserx > -500) || (Math.random() < 0.001)) && score > 60 || (((Math.random() < 0.002)) && score > 100)) {
                wasserx = wasserx - wasserspeed;
                if (hitchecker(wasserx, wassery)) {
                    //score = score + 3;
                    if (!vodkamode) {
                        lives = 0;
                        hit2.start();
                        Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                        Intent gameover = new Intent(getContext(), game_over.class);
                        back.stop();
                        mexiko.stop();
                        vode.stop();
                        gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        gameover.putExtra("score", score);
                        gameover.putExtra("ursache", "Wasser");
                        getContext().startActivity(gameover);
                    }
                    wasserx = -500;
                }
                if (wasserx <= -500) {
                    wasserx = width + 21;
                    wassery = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                }
                if (wasserx < -carlos[0].getWidth()) {
                    wasserx = -500;
                }
                canvas.drawBitmap(wasser, wasserx, wassery, null);
            }

            if (((vodkax > -500) || (Math.random() < 0.0003)) && !vodkamode) {
                vodkax = vodkax - vodkaspeed;
                if (hitchecker(vodkax, vodkay)) {
                    //score = score + 3;
                    back.pause();
                    mexiko.pause();
                    vode.start();
                    vodkamode = true;
                    Toast.makeText(getContext(), "Vodka E!!!", Toast.LENGTH_SHORT).show();
                    vodkax = -500;
                }
                if (vodkax <= -500) {
                    vodkax = width + 21;
                    vodkay = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                }
                if (vodkax < -carlos[0].getWidth()) {
                    vodkax = -500;
                }
                canvas.drawBitmap(vodka_e, vodkax, vodkay, null);
            }
        }


        if (weltallmode){
            if (weltallcounter>666){
                yellowx = yellowx - yellowspeed;

                if (hitchecker(yellowx, yellowy)) {
                    burp.start();
                    score = score + 1;
                    yellowx = -100;
                }
                if (yellowx < -carlos[0].getWidth()) {
                    yellowx = width + 21;
                    yellowy = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                }
                greenx = greenx - greenspeed;
                if (hitchecker(greenx, greeny)) {
                    burp2.start();
                    score = score + 3;
                    greenx = -100;
                }
                if (greenx < -carlos[0].getWidth()) {
                    greenx = width + 21;
                    greeny = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                }

                canvas.drawBitmap(bier, yellowx, yellowy, null);
                canvas.drawBitmap(schnapps, greenx, greeny, null);

                if (score > 160) {
                    pilgerx = pilgerx - pilgerspeed;
                    if (hitchecker(pilgerx, pilgery)) {
                        burp.start();
                        score = score + 2;
                        pilgerx = -100;
                    }
                    if (pilgerx < -carlos[0].getWidth()) {
                        pilgerx = width + 21;
                        pilgery = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                    }
                    canvas.drawBitmap(pilger, pilgerx, pilgery, null);
                }

                if (((majox > -500) || (Math.random() < 0.00005)) && !majomode) {
                    majox = majox - majospeed;
                    if (hitchecker(majox, majoy)) {
                        score = score + 22;
                        majomode = true;
                        schrei.start();
                        Toast.makeText(getContext(), "MAJONÄSE!!!", Toast.LENGTH_SHORT).show();
                        majox = -500;
                        if (lives < 3) {
                            lives = lives + 1;
                        }
                    }
                    if (majox <= -500 && !majomode) {
                        majox = width + 21;
                        majoy = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                    }
                    if (majox < -carlos[0].getWidth()) {
                        majox = -500;
                    }
                    canvas.drawBitmap(majo, majox, majoy, null);
                }

                redx = redx - redspeed;
                if (hitchecker(redx, redy) && !vodkamode) {
                    //score = score + 3;
                    hit.start();
                    lives = lives - 1;
                    if (lives == 0) {
                        Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                        Intent gameover = new Intent(getContext(), game_over.class);
                        back.stop();
                        vode.stop();
                        mexiko.stop();
                        gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        gameover.putExtra("score", score);
                        gameover.putExtra("ursache", "Cola");
                        getContext().startActivity(gameover);
                    }
                    redx = -100;
                }
                if (redx < -carlos[0].getWidth()) {
                    redx = width + 21;
                    redy = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                }
                canvas.drawBitmap(light, redx, redy, null);

                if (score > 150) {
                    redx2 = redx2 - redspeed2;
                    if (hitchecker(redx2, redy2) && !vodkamode) {
                        if (!vodkamode) {
                            //score = score + 3;
                            lives = lives - 1;
                            hit3.start();
                            if (lives == 0) {
                                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                                Intent gameover = new Intent(getContext(), game_over.class);
                                back.stop();
                                mexiko.stop();
                                vode.stop();
                                gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                gameover.putExtra("score", score);
                                gameover.putExtra("ursache", "Cola");
                                getContext().startActivity(gameover);
                            }
                        }
                        redx2 = -100;
                    }
                    if (redx2 < -carlos[0].getWidth()) {
                        redx2 = width + 21;
                        redy2 = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                    }
                    canvas.drawBitmap(light2, redx2, redy2, null);
                }


                if (((wasserx > -500) || (Math.random() < 0.001)) && score > 160 || (((Math.random() < 0.002)) && score > 100)) {
                    wasserx = wasserx - wasserspeed;
                    if (hitchecker(wasserx, wassery)) {
                        //score = score + 3;
                        if (!vodkamode) {
                            lives = 0;
                            hit2.start();
                            Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                            Intent gameover = new Intent(getContext(), game_over.class);
                            back.stop();
                            mexiko.stop();
                            vode.stop();
                            gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            gameover.putExtra("score", score);
                            gameover.putExtra("ursache", "Wasser");
                            getContext().startActivity(gameover);
                        }
                        wasserx = -500;
                    }
                    if (wasserx <= -500) {
                        wasserx = width + 21;
                        wassery = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                    }
                    if (wasserx < -carlos[0].getWidth()) {
                        wasserx = -500;
                    }
                    canvas.drawBitmap(wasser, wasserx, wassery, null);
                }

                if (((vodkax > -500) || (Math.random() < 0.0003)) && !vodkamode) {
                    vodkax = vodkax - vodkaspeed;
                    if (hitchecker(vodkax, vodkay)) {
                        //score = score + 3;
                        back.pause();
                        mexiko.pause();
                        vode.start();
                        vodkamode = true;
                        Toast.makeText(getContext(), "Vodka E!!!", Toast.LENGTH_SHORT).show();
                        vodkax = -500;
                    }
                    if (vodkax <= -500) {
                        vodkax = width + 21;
                        vodkay = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                    }
                    if (vodkax < -carlos[0].getWidth()) {
                        vodkax = -500;
                    }
                    canvas.drawBitmap(vodka_e, vodkax, vodkay, null);
                }
            } else {
                weltallcounter = weltallcounter + 1;
            }
        }

        if (mustafarmode){
            if (mustafarcounter>666){
                yellowx = yellowx - yellowspeed;

                if (hitchecker(yellowx, yellowy)) {
                    burp.start();
                    score = score + 1;
                    yellowx = -100;
                }
                if (yellowx < -carlos[0].getWidth()) {
                    yellowx = width + 21;
                    yellowy = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                }
                greenx = greenx - greenspeed;
                if (hitchecker(greenx, greeny)) {
                    burp2.start();
                    score = score + 3;
                    greenx = -100;
                }
                if (greenx < -carlos[0].getWidth()) {
                    greenx = width + 21;
                    greeny = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                }

                canvas.drawBitmap(bier, yellowx, yellowy, null);
                canvas.drawBitmap(schnapps, greenx, greeny, null);

                if (score > 160) {
                    pilgerx = pilgerx - pilgerspeed;
                    if (hitchecker(pilgerx, pilgery)) {
                        burp.start();
                        score = score + 2;
                        pilgerx = -100;
                    }
                    if (pilgerx < -carlos[0].getWidth()) {
                        pilgerx = width + 21;
                        pilgery = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                    }
                    canvas.drawBitmap(pilger, pilgerx, pilgery, null);
                }

                if (((majox > -500) || (Math.random() < 0.00005)) && !majomode) {
                    majox = majox - majospeed;
                    if (hitchecker(majox, majoy)) {
                        score = score + 22;
                        majomode = true;
                        schrei.start();
                        Toast.makeText(getContext(), "MAJONÄSE!!!", Toast.LENGTH_SHORT).show();
                        majox = -500;
                        if (lives < 3) {
                            lives = lives + 1;
                        }
                    }
                    if (majox <= -500 && !majomode) {
                        majox = width + 21;
                        majoy = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                    }
                    if (majox < -carlos[0].getWidth()) {
                        majox = -500;
                    }
                    canvas.drawBitmap(majo, majox, majoy, null);
                }

                redx = redx - redspeed;
                if (hitchecker(redx, redy) && !vodkamode) {
                    //score = score + 3;
                    hit.start();
                    lives = lives - 1;
                    if (lives == 0) {
                        Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                        Intent gameover = new Intent(getContext(), game_over.class);
                        back.stop();
                        vode.stop();
                        mexiko.stop();
                        gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        gameover.putExtra("score", score);
                        gameover.putExtra("ursache", "Cola");
                        getContext().startActivity(gameover);
                    }
                    redx = -100;
                }
                if (redx < -carlos[0].getWidth()) {
                    redx = width + 21;
                    redy = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                }
                canvas.drawBitmap(light, redx, redy, null);

                if (score > 150) {
                    redx2 = redx2 - redspeed2;
                    if (hitchecker(redx2, redy2) && !vodkamode) {
                        if (!vodkamode) {
                            //score = score + 3;
                            lives = lives - 1;
                            hit3.start();
                            if (lives == 0) {
                                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                                Intent gameover = new Intent(getContext(), game_over.class);
                                back.stop();
                                mexiko.stop();
                                vode.stop();
                                gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                gameover.putExtra("score", score);
                                gameover.putExtra("ursache", "Cola");
                                getContext().startActivity(gameover);
                            }
                        }
                        redx2 = -100;
                    }
                    if (redx2 < -carlos[0].getWidth()) {
                        redx2 = width + 21;
                        redy2 = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                    }
                    canvas.drawBitmap(light2, redx2, redy2, null);
                }


                if (((wasserx > -500) || (Math.random() < 0.001)) && score > 160 || (((Math.random() < 0.002)) && score > 100)) {
                    wasserx = wasserx - wasserspeed;
                    if (hitchecker(wasserx, wassery)) {
                        //score = score + 3;
                        if (!vodkamode) {
                            lives = 0;
                            hit2.start();
                            Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                            Intent gameover = new Intent(getContext(), game_over.class);
                            back.stop();
                            mexiko.stop();
                            vode.stop();
                            gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            gameover.putExtra("score", score);
                            gameover.putExtra("ursache", "Wasser");
                            getContext().startActivity(gameover);
                        }
                        wasserx = -500;
                    }
                    if (wasserx <= -500) {
                        wasserx = width + 21;
                        wassery = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                    }
                    if (wasserx < -carlos[0].getWidth()) {
                        wasserx = -500;
                    }
                    canvas.drawBitmap(wasser, wasserx, wassery, null);
                }

                if (((vodkax > -500) || (Math.random() < 0.0003)) && !vodkamode) {
                    vodkax = vodkax - vodkaspeed;
                    if (hitchecker(vodkax, vodkay)) {
                        //score = score + 3;
                        back.pause();
                        mexiko.pause();
                        vode.start();
                        vodkamode = true;
                        Toast.makeText(getContext(), "Vodka E!!!", Toast.LENGTH_SHORT).show();
                        vodkax = -500;
                    }
                    if (vodkax <= -500) {
                        vodkax = width + 21;
                        vodkay = (int) Math.floor(Math.random() * (maxcY - mincY)) + mincY;
                    }
                    if (vodkax < -carlos[0].getWidth()) {
                        vodkax = -500;
                    }
                    canvas.drawBitmap(vodka_e, vodkax, vodkay, null);
                }
            } else {
                mustafarcounter = mustafarcounter + 1;
            }
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
        if (score>99){
            if (weltallinit){
                initweltall();
                weltallinit = false;
                weltallmode = true;
            }
        }
        if (score>199){
            if (mustafarinit){
                initmustafar();
                mustafarinit = false;
                mustafarmode = true;
            }
        }
    }

    public boolean hitchecker(int x,int y){
        if (carlosX-schnapps.getWidth() < x && x < (carlosX + carlos[0].getWidth()) && carlosY-schnapps.getHeight() < y && y < (carlosY + carlos[0].getHeight())){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touch = true;
            carlosspeed = -updraft;
        }
        return true;
    }

    public void initweltall(){
        gravity =(float) 0.15;
        updraft = 12;
        background_img = weltallback;
        yellowx = -100;
        greenx =-100;
        redx=-100;
        redx2=-500;
        pilgerx=-500;
        wasserx=-500;
        yellowspeed=yellowspeed-2;
        greenspeed=greenspeed-2;
        redspeed=redspeed-3;
        redspeed2=redspeed2-2;
        pilgerspeed=pilgerspeed-2;
        wasserspeed=wasserspeed-3;
    }

    public void initmustafar(){
        mustafarcounter = 0;
        weltallmode = false;
        gravity =(float) 3.5;
        updraft = 33;
        background_img = mustafarback;
        yellowx = -100;
        greenx =-100;
        redx=-100;
        redx2=-500;
        pilgerx=-500;
        wasserx=-500;
        yellowspeed=yellowspeed+4;
        greenspeed=greenspeed+4;
        redspeed=redspeed+6;
        redspeed2=redspeed2+6;
        pilgerspeed=pilgerspeed+4;
        wasserspeed=wasserspeed+6;
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public void male_carlos(Canvas canvas, Bitmap[] penner){
        if (vodkamode){
            counter = counter  + 1;
            if (counter%12<7){
                canvas.drawBitmap(penner[1], carlosX, carlosY, null);
                redc = false;
            } else {
                canvas.drawBitmap(penner[0], carlosX, carlosY, null);
                redc = true;
            }

        } else {
            redc = true;
            canvas.drawBitmap(penner[0], carlosX, carlosY, null);
        }
    }

}
