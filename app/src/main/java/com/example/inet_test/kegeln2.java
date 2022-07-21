package com.example.inet_test;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import java.util.Timer;
import java.util.TimerTask;


public class kegeln2 extends MainActivity{
    private int phase = 1;
    private int width;
    private int height;
    private ImageView kugel;
    private ImageView kugel95;
    private ImageView kugel90;
    private ImageView kugel85;
    private ImageView kugel80;
    private ImageView kugel75;
    private ImageView kugel70;
    private ImageView kugel65;
    private ImageView kugel60;
    private ImageView kugel55;
    private ImageView kugel50;
    private ImageView kugel45;
    private ImageView kugel40;
    private ImageView kugel35;
    private ImageView kugel30;
    private ImageView kugel25;
    private ImageView kugel20;
    private ImageView kugel15;
    private ImageView kugel10;
    private ImageView kugel05;
    private ImageView bier1;
    private ImageView bier2;
    private ImageView bier3;
    private ImageView bier4;
    private ImageView bier5;
    private ImageView bier6;
    private ImageView bier7;
    private ImageView bier8;
    private ImageView bier9;
    private ImageView hit;
    private ImageView blue_dot;
    private ImageView blue_dot2;
    private float initalx;
    private float initaly;
    private float destx;
    private float desty;
    private float zwischenstoppx;
    private float zwischenstoppy;
    private double speed;
    private double angle;
    private int imagewidth;
    private int depth = 0;
    private int counter = 0;
    private float xdist;
    private float ydist;
    private float remainx;
    private float remainy;
    private double rate;
    private boolean einmal;
    private double lenge;
    private boolean carlosinbound;
    private float zwischenergebx;
    private float zwischenergeby;
    private boolean turn_valid;
    private TextView ergebniss;
    private float landungx;
    private float landungy;
    private float landungz;
    private int levelid = util.levelid;
    private int leveldepth;
    private int bier_width;
    private int toleranz;
    private int depthcounter = 0;
    private boolean nixgetroffen = true;
    private int heighscore = util.heighscore;
    private int versuche = util.versuche;
    private  int current_result;
    private boolean einmalzwei = true;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kegeln2);
        util.levelid = 1;
        util.versuche = 2;
        util.heighscore = 0;
        phase = 1;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        initialize_game();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                publish_result();
                switch(phase){
                    case 1:
                        phase = 2;
                        break;
                    case 4:
                        initgameanimation();
                        break;
                    case 5:
                        doturn();
                        break;
                    case 6:
                        aftermath();
                        break;
                    default:
                        break;
                }
            }
        }, 0, 1);
    }

    public void publish_result(){

    }

    public void initialize_game(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        kugel = (ImageView) findViewById(R.id.kugel2);
        kugel.getLayoutParams().width = width/10;
        imagewidth = width/10;
        kugel95 = (ImageView) findViewById(R.id.kugel952);
        kugel90 = (ImageView) findViewById(R.id.kugel902);
        kugel85 = (ImageView) findViewById(R.id.kugel852);
        kugel80 = (ImageView) findViewById(R.id.kugel802);
        kugel75 = (ImageView) findViewById(R.id.kugel752);
        kugel70 = (ImageView) findViewById(R.id.kugel702);
        kugel65 = (ImageView) findViewById(R.id.kugel652);
        kugel60 = (ImageView) findViewById(R.id.kugel602);
        kugel55 = (ImageView) findViewById(R.id.kugel552);
        kugel50 = (ImageView) findViewById(R.id.kugel502);
        kugel45 = (ImageView) findViewById(R.id.kugel452);
        kugel40 = (ImageView) findViewById(R.id.kugel402);
        kugel35 = (ImageView) findViewById(R.id.kugel352);
        kugel30 = (ImageView) findViewById(R.id.kugel302);
        kugel25 = (ImageView) findViewById(R.id.kugel252);
        kugel20 = (ImageView) findViewById(R.id.kugel202);
        kugel15 = (ImageView) findViewById(R.id.kugel152);
        kugel10 = (ImageView) findViewById(R.id.kugel102);
        kugel05 = (ImageView) findViewById(R.id.kugel052);
        ergebniss = (TextView) findViewById(R.id.ergebnisse2);
        hit = (ImageView) findViewById(R.id.hit2);
        bier1 = (ImageView) findViewById(R.id.bier12);
        bier2 = (ImageView) findViewById(R.id.bier22);
        bier3 = (ImageView) findViewById(R.id.bier32);
        bier4 = (ImageView) findViewById(R.id.bier42);
        bier5 = (ImageView) findViewById(R.id.bier52);
        bier6 = (ImageView) findViewById(R.id.bier62);
        bier7 = (ImageView) findViewById(R.id.bier72);
        bier8 = (ImageView) findViewById(R.id.bier82);
        bier9 = (ImageView) findViewById(R.id.bier92);
        switch (util.levelid){
            case 1:
                leveldepth = 5000;
                toleranz = 2000;
                versuche = util.versuche;
                break;
            case 2:
                leveldepth = 6000;
                versuche = util.versuche;
                toleranz = 2000;
                break;
        }
        initiui();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

        phase = phase + 1;
    }

    public void initiui(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                kugel95.getLayoutParams().width =(int) ((width/10) * 0.95);
                kugel90.getLayoutParams().width =(int) ((width/10) * 0.90);
                kugel85.getLayoutParams().width =(int) ((width/10) * 0.85);
                kugel80.getLayoutParams().width =(int) ((width/10) * 0.80);
                kugel75.getLayoutParams().width =(int) ((width/10) * 0.75);
                kugel70.getLayoutParams().width =(int) ((width/10) * 0.70);
                kugel65.getLayoutParams().width =(int) ((width/10) * 0.65);
                kugel60.getLayoutParams().width =(int) ((width/10) * 0.60);
                kugel55.getLayoutParams().width =(int) ((width/10) * 0.55);
                kugel50.getLayoutParams().width =(int) ((width/10) * 0.50);
                kugel45.getLayoutParams().width =(int) ((width/10) * 0.45);
                kugel40.getLayoutParams().width =(int) ((width/10) * 0.40);
                kugel35.getLayoutParams().width =(int) ((width/10) * 0.35);
                kugel30.getLayoutParams().width =(int) ((width/10) * 0.30);
                kugel25.getLayoutParams().width =(int) ((width/10) * 0.25);
                kugel20.getLayoutParams().width =(int) ((width/10) * 0.20);
                kugel15.getLayoutParams().width =(int) ((width/10) * 0.15);
                kugel10.getLayoutParams().width =(int) ((width/10) * 0.10);
                kugel05.getLayoutParams().width =(int) ((width/10) * 0.05);
                hit.getLayoutParams().width = (int) (width/10);
                blue_dot = (ImageView) findViewById(R.id.blue_dot12);
                blue_dot2 = (ImageView) findViewById(R.id.blue_dot22);
                bier_width =(int) ((width/10)*(11-util.levelid)/10);
                bier1.getLayoutParams().width = bier_width;
                bier2.getLayoutParams().width = bier_width;
                bier3.getLayoutParams().width = bier_width;
                bier4.getLayoutParams().width = bier_width;
                bier5.getLayoutParams().width = bier_width;
                bier6.getLayoutParams().width = bier_width;
                bier7.getLayoutParams().width = bier_width;
                bier8.getLayoutParams().width = bier_width;
                bier9.getLayoutParams().width = bier_width;
                blue_dot.getLayoutParams().width = bier_width;
                blue_dot2.getLayoutParams().width = bier_width;
                blue_dot.setVisibility(View.INVISIBLE);
                blue_dot2.setVisibility(View.INVISIBLE);
                kugel95.setVisibility(View.INVISIBLE);
                kugel90.setVisibility(View.INVISIBLE);
                kugel85.setVisibility(View.INVISIBLE);
                kugel80.setVisibility(View.INVISIBLE);
                kugel75.setVisibility(View.INVISIBLE);
                kugel70.setVisibility(View.INVISIBLE);
                kugel65.setVisibility(View.INVISIBLE);
                kugel60.setVisibility(View.INVISIBLE);
                kugel55.setVisibility(View.INVISIBLE);
                kugel50.setVisibility(View.INVISIBLE);
                kugel45.setVisibility(View.INVISIBLE);
                kugel40.setVisibility(View.INVISIBLE);
                kugel35.setVisibility(View.INVISIBLE);
                kugel30.setVisibility(View.INVISIBLE);
                kugel25.setVisibility(View.INVISIBLE);
                kugel20.setVisibility(View.INVISIBLE);
                kugel15.setVisibility(View.INVISIBLE);
                kugel10.setVisibility(View.INVISIBLE);
                kugel05.setVisibility(View.INVISIBLE);
                kugel.setVisibility(View.VISIBLE);
                hit.setVisibility(View.INVISIBLE);
                bier1.setVisibility(View.VISIBLE);
                bier2.setVisibility(View.VISIBLE);
                bier3.setVisibility(View.VISIBLE);
                bier4.setVisibility(View.VISIBLE);
                bier5.setVisibility(View.VISIBLE);
                bier6.setVisibility(View.VISIBLE);
                bier7.setVisibility(View.VISIBLE);
                bier8.setVisibility(View.VISIBLE);
                bier9.setVisibility(View.VISIBLE);
                bier_width = bier1.getWidth();
                kugel.setX(width/2 - width/20);
                kugel.setY(height-(5*width/20));
                bier1.setX(width/2-bier_width/2);
                bier1.setY(3*bier1.getHeight());
                bier2.setX(bier1.getX()-bier_width/2);
                bier2.setY(3*bier1.getHeight() - bier1.getHeight()/2);
                bier3.setX(bier1.getX()+bier_width/2);
                bier3.setY(3*bier1.getHeight() - bier1.getHeight()/2);
                bier4.setX(bier1.getX()-bier_width);
                bier4.setY(3*bier1.getHeight()-bier1.getHeight());
                bier5.setX(bier1.getX());
                bier5.setY(3*bier1.getHeight()-bier1.getHeight());
                bier6.setX(bier1.getX()+bier_width);
                bier6.setY(3*bier1.getHeight()-bier1.getHeight());
                bier7.setX(bier1.getX()-bier_width/2);
                bier7.setY(3*bier1.getHeight() - 3*bier1.getHeight()/2);
                bier8.setX(bier1.getX()+bier_width/2);
                bier8.setY(3*bier1.getHeight() - 3*bier1.getHeight()/2);
                bier9.setX(bier1.getX());
                bier9.setY(3*bier1.getHeight()-2*bier1.getHeight());
            }
        });
    }

    public void initgameanimation(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                kugel.setX(width/2 - width/20);
                kugel.setY(height-(5*width/20));
                kugel.setVisibility(View.VISIBLE);
                carlosinbound = true;
            }
        });
        phase = 5;
    }

    public void doturn(){
        lenge = Math.sqrt((destx-initalx)*(destx-initalx) + (desty-initaly)*(desty-initaly))/height;
        speed = (height) * (1/lenge)/500;
        angle = Math.atan(Math.abs(destx-initalx)/Math.abs(desty-initaly));
        xdist = destx - initalx;
        ydist = desty - initaly;
        remainx = Math.abs(xdist);
        remainy = Math.abs(ydist);
        if (depthcounter>2) {
            depth = (int) (depth + speed);
            depthcounter = 0;
        }
        else {
            depthcounter = depthcounter + 1;
        }
        if (carlosinbound) {
            if (Math.abs(xdist) > Math.abs(ydist)) {
                rate = Math.abs(xdist / ydist);
                if (einmal){
                    move_carlos(0, (int) 1 * (int) Math.signum(ydist), depth);
                    //depth = (int) (depth + speed);
                    remainy = remainy - 1;
                    zwischenergeby = remainy;
                    zwischenergebx = remainx;
                    einmal = false;
                } else{
                    if (zwischenergebx / zwischenergeby > rate && zwischenergebx!=0) {
                        move_carlos(1 * (int) Math.signum(xdist), 0, depth);
                        zwischenergebx = zwischenergebx - 1;
                    } else{
                        einmal = true;
                    }
                }
            } else {
                rate = Math.abs(ydist / xdist);
                if (einmal){
                    move_carlos((int) 1 * (int) Math.signum(xdist), 0, depth);
                    //depth = (int) (depth + speed);
                    remainx = remainx - 1;
                    zwischenergeby = remainy;
                    zwischenergebx = remainx;
                    einmal = false;
                } else {
                    if (zwischenergeby / zwischenergebx > rate) {
                        move_carlos(0, 1 * (int) Math.signum(ydist), depth);
                        zwischenergeby = zwischenergeby - 1;
                    } else {
                        einmal = true;
                    }
                }
            }
            //bounddetection();
            if (kugel.getX()<0 || kugel.getX()>width) {
                carlosinbound = false;
                turn_valid = false;
                phase = phase + 1;
            }
            if (kugel.getY()>height){
                carlosinbound = false;
                turn_valid = false;
                phase = phase + 1;
            }
            if (kugel.getY()<=0){
                carlosinbound = false;
                turn_valid = true;
                phase = phase + 1;
            }
            if (depth>leveldepth){
                carlosinbound = false;
                turn_valid = true;
                phase = phase +1;
            }
        }
    }

    public void move_carlos(int x, int y, int z){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                kugel.setX(kugel.getX()+x);
                kugel.setY(kugel.getY()+y);
                //kugel.getLayoutParams().width = (width/10) - (depth/10000) * (width/10);
                if ((depth < 1001) && (depth > 500)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel95.setVisibility(View.VISIBLE);
                    kugel95.setX(kugel.getX());
                    kugel95.setY(kugel.getY());
                    kugel = kugel95;
                }
                if ((depth > 1000) && (depth < 1501)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel90.setVisibility(View.VISIBLE);
                    kugel90.setX(kugel.getX());
                    kugel90.setY(kugel.getY());
                    kugel = kugel90;
                }
                if ((depth < 2001) && (depth > 1500)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel85.setVisibility(View.VISIBLE);
                    kugel85.setX(kugel.getX());
                    kugel85.setY(kugel.getY());
                    kugel = kugel85;
                }
                if ((depth > 2000) && (depth < 2501)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel80.setVisibility(View.VISIBLE);
                    kugel80.setX(kugel.getX());
                    kugel80.setY(kugel.getY());
                    kugel = kugel80;
                }
                if ((depth < 3001) && (depth > 2500)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel75.setVisibility(View.VISIBLE);
                    kugel75.setX(kugel.getX());
                    kugel75.setY(kugel.getY());
                    kugel = kugel75;
                }
                if ((depth > 3000) && (depth < 3501)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel70.setVisibility(View.VISIBLE);
                    kugel70.setX(kugel.getX());
                    kugel70.setY(kugel.getY());
                    kugel = kugel70;
                }
                if ((depth < 4001) && (depth > 3500)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel65.setVisibility(View.VISIBLE);
                    kugel65.setX(kugel.getX());
                    kugel65.setY(kugel.getY());
                    kugel = kugel65;
                }
                if ((depth > 4000) && (depth < 4501)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel60.setVisibility(View.VISIBLE);
                    kugel60.setX(kugel.getX());
                    kugel60.setY(kugel.getY());
                    kugel = kugel60;
                }
                if ((depth < 5001) && (depth > 4500)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel55.setVisibility(View.VISIBLE);
                    kugel55.setX(kugel.getX());
                    kugel55.setY(kugel.getY());
                    kugel = kugel55;
                }
                if ((depth > 5000) && (depth < 5501)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel50.setVisibility(View.VISIBLE);
                    kugel50.setX(kugel.getX());
                    kugel50.setY(kugel.getY());
                    kugel = kugel50;
                }
                if ((depth < 6001) && (depth > 5500)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel45.setVisibility(View.VISIBLE);
                    kugel45.setX(kugel.getX());
                    kugel45.setY(kugel.getY());
                    kugel = kugel45;
                }
                if ((depth > 6000) && (depth < 6501)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel40.setVisibility(View.VISIBLE);
                    kugel40.setX(kugel.getX());
                    kugel40.setY(kugel.getY());
                    kugel = kugel40;
                }
                if ((depth < 7001) && (depth > 6500)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel35.setVisibility(View.VISIBLE);
                    kugel35.setX(kugel.getX());
                    kugel35.setY(kugel.getY());
                    kugel = kugel35;
                }
                if ((depth > 7000) && (depth < 7501)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel30.setVisibility(View.VISIBLE);
                    kugel30.setX(kugel.getX());
                    kugel30.setY(kugel.getY());
                    kugel = kugel30;
                }
                if ((depth < 8001) && (depth > 7500)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel25.setVisibility(View.VISIBLE);
                    kugel25.setX(kugel.getX());
                    kugel25.setY(kugel.getY());
                    kugel = kugel25;
                }
                if ((depth > 8000) && (depth < 8501)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel20.setVisibility(View.VISIBLE);
                    kugel20.setX(kugel.getX());
                    kugel20.setY(kugel.getY());
                    kugel = kugel20;
                }
                if ((depth < 9001) && (depth > 8500)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel15.setVisibility(View.VISIBLE);
                    kugel15.setX(kugel.getX());
                    kugel15.setY(kugel.getY());
                    kugel = kugel15;
                }
                if ((depth > 9000) && (depth < 9501)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel10.setVisibility(View.VISIBLE);
                    kugel10.setX(kugel.getX());
                    kugel10.setY(kugel.getY());
                    kugel = kugel10;
                }
                if ((depth < 10001) && (depth > 9500)){
                    kugel.setVisibility(View.INVISIBLE);
                    kugel05.setVisibility(View.VISIBLE);
                    kugel05.setX(kugel.getX());
                    kugel05.setY(kugel.getY());
                    kugel = kugel05;
                }
            }
        });
    }

    public void aftermath(){
        publish_result();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (phase == 2) {
                    initalx = event.getX();
                    initaly = event.getY();
                    kugel.setX(initalx - kugel.getWidth()/2);
                    kugel.setY(initaly - kugel.getHeight());
                    phase = phase +1;
                    blue_dot.setVisibility(View.VISIBLE);
                    blue_dot.setX(initalx - blue_dot.getWidth());
                    blue_dot.setY(initaly - blue_dot.getHeight());
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (phase == 3) {
                    zwischenstoppx = event.getX();
                    zwischenstoppy = event.getY();
                    kugel.setX(zwischenstoppx - kugel.getWidth()/2);
                    kugel.setY(zwischenstoppy - kugel.getHeight());
                }
                break;
            case MotionEvent.ACTION_UP:
                if (phase == 3){
                    blue_dot2.setVisibility(View.VISIBLE);
                    blue_dot2.setX(event.getX() - blue_dot2.getWidth());
                    blue_dot2.setY(event.getY() - blue_dot2.getHeight());
                    destx = event.getX();
                    desty = event.getY();
                    phase = phase + 1;
                }
                break;
        }
        return true;
    }
}

