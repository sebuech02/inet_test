package com.example.inet_test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.View;

public class crabby_gameview extends View {
    private int width, height;
    public crabby_gameview(Context context) {
        super(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        //Hier wird das game Initialisiert, Assets laden und anzeigen.
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Hier findet das Spiel statt. Diese Funktion wird 66 mal pro sekunde aufgerufen
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

}
