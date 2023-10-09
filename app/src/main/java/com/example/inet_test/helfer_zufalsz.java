package com.example.inet_test;

import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class helfer_zufalsz extends sp_helfer implements View.OnClickListener {
    private Button eingabe;
    private EditText et_min, et_max;
    private TextView erg;
    private int zzahl, zmax, zmin;
    private tinydb db;
    private Vibrator vibe;
    private boolean vibe_on;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helfer_zzahl);
        setTitle("Zufallszahl");
        vibe=getApplicationContext().getSystemService(Vibrator.class);
        eingabe = findViewById(R.id.do_zz);
        et_min=findViewById(R.id.et_min);
        et_max=findViewById(R.id.et_max);
        erg=findViewById(R.id.erg_zz);

        eingabe.setOnClickListener(this);

        db=new tinydb(this);
        try {
            zzahl=db.getInt("zzahl_helfer");
            erg.setText(String.valueOf(zzahl));
        } catch (Exception e){
            System.out.println(e.toString());
            zzahl=0;
            db.putInt("zzahl_helfer", zzahl);
        }
        try {
            zmax=db.getInt("zmax_helfer");
        } catch (Exception e){
            System.out.println(e.toString());
            zmax=0;
            db.putInt("zmax_helfer", zmax);
        }
        et_max.setText(String.valueOf(zmax));
        try {
            zmin=db.getInt("zmin_helfer");
        } catch (Exception e){
            System.out.println(e.toString());
            zmin=9;
            db.putInt("zmin_helfer", zmin);
        }
        vibe_on=db.getBoolean_true("vibe");
        db.putBoolean("vibe", vibe_on);
        et_min.setText(String.valueOf(zmin));
    }

    @Override
    public void onClick(View v){
        if (v.getId()==R.id.do_zz){
            gen_zz();
            if (vibe_on){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    long[] temp = new long[] {0,100, 0, 100};
                    vibe.vibrate(VibrationEffect.createWaveform(temp, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibe.vibrate(200);
                }
            }
        }
    }

    private void gen_zz(){
        int min, max;
        String temp = et_min.getText().toString();
        try {
            min = Integer.parseInt(temp);
        } catch (Exception e){
            min=0;
            et_min.setText("0");
        }
        temp = et_max.getText().toString();
        try {
            max = Integer.parseInt(temp);
        } catch (Exception e){
            max=0;
            et_max.setText("0");
        }
        if (max<min){
            int tt = max;
            max=min;
            min=tt;
            //Toast.makeText(this, "Max kliener als Min.", Toast.LENGTH_SHORT).show();
        }
        et_min.setText(String.valueOf(min));
        et_max.setText(String.valueOf(max));
        db.putInt("zmin_helfer", min);
        db.putInt("zmax_helfer", max);
        int zahl = new Random().nextInt((max - min) + 1) + min;
        zzahl=zahl;
        db.putInt("zzahl_helfer", zzahl);
        erg.setText(String.valueOf(zzahl));
    }
}
