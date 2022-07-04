package com.example.inet_test;

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


public class kegeln extends MainActivity{
    public int phase = 1;
    public int width;
    public int height;
    public ImageView kugel;
    public ImageView kugel95;
    public ImageView kugel90;
    public ImageView kugel85;
    public ImageView kugel80;
    public ImageView kugel75;
    public ImageView kugel70;
    public ImageView kugel65;
    public ImageView kugel60;
    public ImageView kugel55;
    public ImageView kugel50;
    public ImageView kugel45;
    public ImageView kugel40;
    public ImageView kugel35;
    public ImageView kugel30;
    public ImageView kugel25;
    public ImageView kugel20;
    public ImageView kugel15;
    public ImageView kugel10;
    public ImageView kugel05;
    public ImageView bier1;
    public ImageView bier2;
    public ImageView bier3;
    public ImageView bier4;
    public ImageView bier5;
    public ImageView bier6;
    public ImageView bier7;
    public ImageView bier8;
    public ImageView bier9;

    public ImageView hit;
    public ImageView blue_dot;
    public ImageView blue_dot2;
    public float initalx;
    public float initaly;
    public float destx;
    public float desty;
    public float zwischenstoppx;
    public float zwischenstoppy;
    public int line_lange;
    public double speed;
    public double angle;
    public int imagewidth;
    public int depth = 0;
    public boolean once=true;
    public int counter = 0;
    public float xdist;
    public float ydist;
    public float remainx;
    public float remainy;
    public double rate;
    public boolean einmal;
    public double lenge;
    public boolean carlosinbound;
    public float zwischenergebx;
    public float zwischenergeby;
    public boolean turn_valid;
    public TextView ergebniss;
    public float landungx;
    public float landungy;
    public float landungz;
    public int levelid;
    public int leveldepth;
    public int bier_width;
    public int toleranz;
    public int depthcounter = 0;
    public boolean nixgetroffen = true;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kegeln);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        kugel = (ImageView) findViewById(R.id.kugel);
        kugel.getLayoutParams().width = width/10;
        imagewidth = width/10;
        levelid = 1;
        kugel95 = (ImageView) findViewById(R.id.kugel95);
        kugel90 = (ImageView) findViewById(R.id.kugel90);
        kugel85 = (ImageView) findViewById(R.id.kugel85);
        kugel80 = (ImageView) findViewById(R.id.kugel80);
        kugel75 = (ImageView) findViewById(R.id.kugel75);
        kugel70 = (ImageView) findViewById(R.id.kugel70);
        kugel65 = (ImageView) findViewById(R.id.kugel65);
        kugel60 = (ImageView) findViewById(R.id.kugel60);
        kugel55 = (ImageView) findViewById(R.id.kugel55);
        kugel50 = (ImageView) findViewById(R.id.kugel50);
        kugel45 = (ImageView) findViewById(R.id.kugel45);
        kugel40 = (ImageView) findViewById(R.id.kugel40);
        kugel35 = (ImageView) findViewById(R.id.kugel35);
        kugel30 = (ImageView) findViewById(R.id.kugel30);
        kugel25 = (ImageView) findViewById(R.id.kugel25);
        kugel20 = (ImageView) findViewById(R.id.kugel20);
        kugel15 = (ImageView) findViewById(R.id.kugel15);
        kugel10 = (ImageView) findViewById(R.id.kugel10);
        kugel05 = (ImageView) findViewById(R.id.kugel05);
        ergebniss = (TextView) findViewById(R.id.ergebnisse);
        hit = (ImageView) findViewById(R.id.hit);
        bier1 = (ImageView) findViewById(R.id.bier1);
        bier2 = (ImageView) findViewById(R.id.bier2);
        bier3 = (ImageView) findViewById(R.id.bier3);
        bier4 = (ImageView) findViewById(R.id.bier4);
        bier5 = (ImageView) findViewById(R.id.bier5);
        bier6 = (ImageView) findViewById(R.id.bier6);
        bier7 = (ImageView) findViewById(R.id.bier7);
        bier8 = (ImageView) findViewById(R.id.bier8);
        bier9 = (ImageView) findViewById(R.id.bier9);

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

        blue_dot = (ImageView) findViewById(R.id.blue_dot);
        blue_dot2 = (ImageView) findViewById(R.id.blue_dot2);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                switch(phase){
                    case 1:
                        initialize_game();
                        break;
                    case 2:
                        waitfortouch();
                        break;
                    case 3:
                        getinputs();
                        break;
                    case 4:
                        initgameanimation();
                        break;
                    case 5:
                        doturn();
                        break;
                    case 6:
                        doturn2();
                        break;
                    case 7:
                        aftermath();
                        break;
                    default:
                        break;
                }
            }
        }, 0, 1);
    }

    public void initialize_game() {
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
        hit.setVisibility(View.INVISIBLE);

        kugel.setX(width/2 - width/20);
        kugel.setY(height-(5*width/20));
        position_bier();
        switch (levelid){
            case 1:
                leveldepth = 5000;
                toleranz = 2000;
                publish_result("Level " + levelid+System.lineSeparator() + "Tiefe: " + leveldepth);
                break;
        }
        phase = 2;
    }

    public void position_bier(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bier_width = (width/10)*((11-levelid)/10);
                bier1.getLayoutParams().width = bier_width;
                bier2.getLayoutParams().width = bier_width;
                bier3.getLayoutParams().width = bier_width;
                bier4.getLayoutParams().width = bier_width;
                bier5.getLayoutParams().width = bier_width;
                bier6.getLayoutParams().width = bier_width;
                bier7.getLayoutParams().width = bier_width;
                bier8.getLayoutParams().width = bier_width;
                bier9.getLayoutParams().width = bier_width;
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

    public void waitfortouch() {
        //kugel.setX(kugel.getX()+width/1000);
    }

    public void getinputs(){
        //kugel.setY(kugel.getY()+height/1000);
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
                phase = 7;
            }
            if (kugel.getY()>height){
                carlosinbound = false;
                turn_valid = false;
                phase = 7;
            }
            if (kugel.getY()<=0){
                carlosinbound = false;
                turn_valid = true;
                phase = 7;
            }
            if (depth>leveldepth){
                carlosinbound = false;
                turn_valid = true;
                phase = 7;
            }
        }
    }

    public void aftermath(){
        //phase  = -1;
        if (turn_valid){
            showhit();
            landungx = kugel.getX()+kugel.getWidth()/2;
            landungy = kugel.getY()+kugel.getHeight()/2;
            landungz = depth;
            if ((landungx<bier4.getX()) || (landungx>bier6.getX()+bier6.getWidth())){
                publish_result("nix getroffen, leider daneben");
            } else {
                if (landungy < bier9.getY()){
                    publish_result("Zu weit gepfeffert");
                } else {
                    if (landungy > bier1.getY()+bier1.getHeight()){
                        publish_result("Leider zu kurz");
                    } else {
                        hitdetection();
                        //publish_result("schöner Strike!");
                    }
                }
            }
        } else {
            publish_result("Out of bounds, besser zielen");
        }
        phase = phase + 1;
    }
    public void showhit(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hit.setVisibility(View.VISIBLE);
                hit.setX(kugel.getX());
                hit.setY(kugel.getY());
                kugel.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void publish_result(String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ergebniss.setText(msg);
            }
        });
    }


    public void doturn2(){
        if ((kugel.getY()<3*height/4) && (depth<leveldepth+toleranz) && nichtvonobengetroffen()){
            move_carlos(0,1,depth);
            counter = counter +1;
            if (counter == 5) {
                depth = (int) (depth + speed);
                counter = 0;
            }
        } else{
            phase = 7;
        }

    }

    public boolean nichtvonobengetroffen(){
        if (depth > leveldepth){
            landungx = kugel.getX() + kugel.getWidth()/2;
            landungy = kugel.getY() + kugel.getHeight()/2;
            if ((landungx>bier9.getX() && (landungx<bier9.getX()+bier9.getWidth()) && (landungy<bier9.getY()+bier9.getHeight()) && (landungy>bier9.getY()))) {
                return false;
            }
            if ((landungx>bier8.getX() && (landungx<bier8.getX()+bier8.getWidth()) && (landungy<bier8.getY()+bier8.getHeight()) && (landungy>bier8.getY()))) {
                return false;
            }
            if ((landungx>bier7.getX() && (landungx<bier7.getX()+bier7.getWidth()) && (landungy<bier7.getY()+bier7.getHeight()) && (landungy>bier7.getY()))) {
                return false;
            }
            if ((landungx>bier6.getX() && (landungx<bier6.getX()+bier6.getWidth()) && (landungy<bier6.getY()+bier6.getHeight()) && (landungy>bier6.getY()))) {
                return false;
            }
            if ((landungx>bier5.getX() && (landungx<bier5.getX()+bier5.getWidth()) && (landungy<bier5.getY()+bier5.getHeight()) && (landungy>bier5.getY()))) {
                return false;
            }
            if ((landungx>bier4.getX() && (landungx<bier4.getX()+bier4.getWidth()) && (landungy<bier4.getY()+bier4.getHeight()) && (landungy>bier4.getY()))) {
                return false;
            }
            if ((landungx>bier3.getX() && (landungx<bier3.getX()+bier3.getWidth()) && (landungy<bier3.getY()+bier3.getHeight()) && (landungy>bier3.getY()))) {
                return false;
            }
            if ((landungx>bier2.getX() && (landungx<bier2.getX()+bier2.getWidth()) && (landungy<bier2.getY()+bier2.getHeight()) && (landungy>bier2.getY()))) {
                return false;
            }
            if ((landungx>bier1.getX() && (landungx<bier1.getX()+bier1.getWidth()) && (landungy<bier1.getY()+bier1.getHeight()) && (landungy>bier1.getY()))) {
                return false;
            }
            return true;
        } else{
            return true;
            }
        }

    public void move_carlos(int x, int y, int z){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                kugel.setX(kugel.getX()+x);
                kugel.setY(kugel.getY()+y);
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

    public void hitdetection(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (nixgetroffen){
                    if ((landungx>bier9.getX() && (landungx<bier9.getX()+bier9.getWidth()) && (landungy<bier9.getY()+bier9.getHeight()) && (landungy>bier9.getY()))) {
                        bier9.setVisibility(View.INVISIBLE);
                        publish_result("Ergebniss: 1");
                        nixgetroffen = false;
                    }
                    if ((landungx>bier8.getX() && (landungx<bier8.getX()+bier8.getWidth()) && (landungy<bier8.getY()+bier8.getHeight()) && (landungy>bier8.getY()))) {
                        bier9.setVisibility(View.INVISIBLE);
                        bier8.setVisibility(View.INVISIBLE);
                        publish_result("Ergebniss: 2");
                        nixgetroffen = false;
                    }
                    if ((landungx>bier7.getX() && (landungx<bier7.getX()+bier7.getWidth()) && (landungy<bier7.getY()+bier7.getHeight()) && (landungy>bier7.getY()))) {
                        bier9.setVisibility(View.INVISIBLE);
                        bier7.setVisibility(View.INVISIBLE);
                        publish_result("Ergebniss: 2");
                        nixgetroffen = false;
                    }
                    if ((landungx>bier6.getX() && (landungx<bier6.getX()+bier6.getWidth()) && (landungy<bier6.getY()+bier6.getHeight()) && (landungy>bier6.getY()))) {
                        bier6.setVisibility(View.INVISIBLE);
                        publish_result("Ergebniss: 1");
                        nixgetroffen = false;
                    }
                    if ((landungx>bier5.getX() && (landungx<bier5.getX()+bier5.getWidth()) && (landungy<bier5.getY()+bier5.getHeight()) && (landungy>bier5.getY()))) {
                        bier5.setVisibility(View.INVISIBLE);
                        bier7.setVisibility(View.INVISIBLE);
                        bier8.setVisibility(View.INVISIBLE);
                        bier9.setVisibility(View.INVISIBLE);
                        publish_result("Ergebniss: 4");
                        nixgetroffen = false;
                    }
                    if ((landungx>bier4.getX() && (landungx<bier4.getX()+bier4.getWidth()) && (landungy<bier4.getY()+bier4.getHeight()) && (landungy>bier4.getY()))) {
                        bier4.setVisibility(View.INVISIBLE);
                        publish_result("Ergebniss: 1");
                        nixgetroffen = false;
                    }
                    if ((landungx>bier3.getX() && (landungx<bier3.getX()+bier3.getWidth()) && (landungy<bier3.getY()+bier3.getHeight()) && (landungy>bier3.getY()))) {
                        bier3.setVisibility(View.INVISIBLE);
                        bier5.setVisibility(View.INVISIBLE);
                        bier6.setVisibility(View.INVISIBLE);
                        bier7.setVisibility(View.INVISIBLE);
                        bier8.setVisibility(View.INVISIBLE);
                        bier9.setVisibility(View.INVISIBLE);
                        publish_result("Ergebniss: 6");
                        nixgetroffen = false;
                    }
                    if ((landungx>bier2.getX() && (landungx<bier2.getX()+bier2.getWidth()) && (landungy<bier2.getY()+bier2.getHeight()) && (landungy>bier2.getY()))) {
                        bier2.setVisibility(View.INVISIBLE);
                        bier4.setVisibility(View.INVISIBLE);
                        bier5.setVisibility(View.INVISIBLE);
                        bier7.setVisibility(View.INVISIBLE);
                        bier8.setVisibility(View.INVISIBLE);
                        bier9.setVisibility(View.INVISIBLE);
                        publish_result("Ergebniss: 6");
                        nixgetroffen = false;
                    }
                    if ((landungx>bier1.getX() && (landungx<bier1.getX()+bier1.getWidth()) && (landungy<bier1.getY()+bier1.getHeight()) && (landungy>bier1.getY()))) {
                        bier1.setVisibility(View.INVISIBLE);
                        bier2.setVisibility(View.INVISIBLE);
                        bier3.setVisibility(View.INVISIBLE);
                        bier4.setVisibility(View.INVISIBLE);
                        bier5.setVisibility(View.INVISIBLE);
                        bier6.setVisibility(View.INVISIBLE);
                        bier7.setVisibility(View.INVISIBLE);
                        bier8.setVisibility(View.INVISIBLE);
                        bier9.setVisibility(View.INVISIBLE);
                        publish_result("Ergebniss: 9");
                        nixgetroffen = false;
                    }
                    if(nixgetroffen){
                        publish_result("Leider daneben oder dazwischen :-(");
                    }
                } else {
                    //publish_result("mal wieder an allem vorbei, einfach schlecht");
                }
            }
        });
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
                    phase = 3;
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
                    //line.setVisibility(View.VISIBLE);
                    //line.setX(initalx + (zwischenstoppx - initalx)/2);
                    //line.setY(initaly + (zwischenstoppy - initaly)/2);
                    //line_lange = (int) Math.sqrt((zwischenstoppx - initalx) * (zwischenstoppx - initalx) + (zwischenstoppy - initaly) * (zwischenstoppy - initaly));
                    //line.getLayoutParams().width = line_lange;
                    //line.requestLayout();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (phase == 3){
                    blue_dot2.setVisibility(View.VISIBLE);
                    blue_dot2.setX(event.getX() - blue_dot2.getWidth());
                    blue_dot2.setY(event.getY() - blue_dot2.getHeight());
                    destx = event.getX();
                    desty = event.getY();
                    phase = 4;
                }
                break;
        }
        return true;
    }
    @Override
    public void onPause() {
        super.onPause();
        phase = -1;
    }

    @Override
    public void onResume() {
        super.onResume();
        phase = 1;
    }
}

