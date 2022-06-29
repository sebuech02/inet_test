package com.example.inet_test;


import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.RequiresApi;


public class kegeln extends MainActivity implements View.OnClickListener, View.OnTouchListener  {
    float dX;
    float dY;
    int lastAction;
    LinearLayout floatingLayout;
    public VelocityTracker vt=null;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kegeln);

        floatingLayout = findViewById(R.id.floating_layout);
        floatingLayout.setOnTouchListener(this);
    }



        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    vt=VelocityTracker.obtain();
                    dX = view.getX() - event.getRawX();
                    dY = view.getY() - event.getRawY();
                    lastAction = MotionEvent.ACTION_DOWN;
                    vt.addMovement(event);
                    break;

                case MotionEvent.ACTION_MOVE:
                    vt.addMovement(event);
                    view.setY(event.getRawY() + dY);
                    view.setX(event.getRawX() + dX);
                    lastAction = MotionEvent.ACTION_MOVE;
                    vt.computeCurrentVelocity(1000);
                    break;

                case MotionEvent.ACTION_UP:
                    if (lastAction == MotionEvent.ACTION_DOWN) {
                        Toast.makeText(getApplicationContext(), "Clicked!", Toast.LENGTH_SHORT).show();
                        view.setX(0);
                        view.setY(0);
                    }
                    else {
                        view.setY(event.getRawY() + dY - vt.getXVelocity()/10);
                        view.setX(event.getRawX() + dX - vt.getYVelocity()/10);
                    }
                    break;

                default:
                    return false;
            }
            return true;
    }
}

