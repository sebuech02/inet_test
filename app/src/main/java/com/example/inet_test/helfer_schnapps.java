package com.example.inet_test;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class helfer_schnapps extends sp_helfer implements View.OnClickListener {
    private LinearLayout parent;
    private Button ziehen, shuffle;
    private String[] karten;
    private ArrayList<Integer> gezogens;
    private TextView zahl;
    private tinydb db;
    private Vibrator vibe;
    private boolean vibe_on;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helfer_schnapps);
        setTitle("Schnappskarten-ziehen");
        vibe=getApplicationContext().getSystemService(Vibrator.class);

        parent=findViewById(R.id.verlauf_karten);
        ziehen=findViewById(R.id.anzahl_karten);
        shuffle=findViewById(R.id.misch_karten);
        karten=this.getResources().getStringArray(R.array.cards);

        ziehen.setOnClickListener(this);
        shuffle.setOnClickListener(this);

        db = new tinydb(this);
        try {
            gezogens=db.getListInt("schnapps_helfer");
        } catch (Exception e){
            System.out.println(e.toString());
            gezogens=new ArrayList<Integer>();
            db.putListInt("schnapps_helfer", gezogens);
        }
        vibe_on=db.getBoolean_true("vibe");
        db.putBoolean("vibe", vibe_on);
        show_schnapps();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.anzahl_karten:{
                karte_ziehen();
                if (vibe_on){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        long[] temp = new long[] {0,100, 0, 100};
                        vibe.vibrate(VibrationEffect.createWaveform(temp, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibe.vibrate(200);
                    }
                }
                break;
            }
            case R.id.misch_karten:{
                reset_schnapps();
                break;
            }
            default:{
                break;
            }
        }
    }

    private void reset_schnapps(){
        gezogens=new ArrayList<Integer>();
        show_schnapps();
    }

    private void show_schnapps(){
        db.putListInt("schnapps_helfer", gezogens);
        parent.removeAllViews();
        parent.setOrientation(LinearLayout.VERTICAL);
        ziehen.setText(String.valueOf(karten.length-gezogens.size()));
        if(karten.length-gezogens.size()<10){
            ziehen.setText("0"+String.valueOf(karten.length-gezogens.size()));
        }
        ziehen.setBackground(getDrawable(R.drawable.shape_cad));
        int i = 0;
        while (i<gezogens.size()){
            zahl=new TextView(this);
            zahl.setTextColor(Color.parseColor("#ffffff"));
            zahl.setTextSize(22);
            zahl.setText(karten[(int) (gezogens.get((int) (gezogens.size()-1-i)))]);
            if (i==0){
                zahl.setTextColor(Color.parseColor("#ff0000"));
            }
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(5,22,5,22);
            zahl.setLayoutParams(lp);
            parent.addView(zahl);
            i++;
        }
    }

    private void karte_ziehen(){
        int max = karten.length-1;
        int min = 0;
        int rn =  new Random().nextInt((max - min) + 1) + min;
        if (gezogens.contains(rn) && gezogens.size()!=karten.length){
            karte_ziehen();
        } else if (gezogens.size()==karten.length) {
            Toast.makeText(this, "Keine Karten übrig", Toast.LENGTH_SHORT).show();
        } else{
            gezogens.add((int) rn);
            show_schnapps();
        }
    }
}
