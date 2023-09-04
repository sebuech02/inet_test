package com.example.inet_test;

import android.graphics.Color;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helfer_schnapps);
        setTitle("Schnappskarten-ziehen");

        parent=findViewById(R.id.verlauf_karten);
        ziehen=findViewById(R.id.anzahl_karten);
        shuffle=findViewById(R.id.misch_karten);
        karten=this.getResources().getStringArray(R.array.cards);

        ziehen.setOnClickListener(this);
        shuffle.setOnClickListener(this);
        reset_schnapps();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.anzahl_karten:{
                karte_ziehen();
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
        parent.removeAllViews();
        parent.setOrientation(LinearLayout.VERTICAL);
        ziehen.setText(String.valueOf(karten.length-gezogens.size()));
        if(karten.length-gezogens.size()<10){
            ziehen.setText("0"+String.valueOf(karten.length-gezogens.size()));
        }
        ziehen.setBackground(getDrawable(R.drawable.shape_cad));
        //ziehen.setBackgroundColor(Color.parseColor("#757575"));
        int i = 0;
        while (i<gezogens.size()){
            zahl=new TextView(this);
            zahl.setTextColor(Color.parseColor("#ffffff"));
            zahl.setTextSize(22);
            //System.out.println(gezogens.size()-1-i);
            //System.out.println(gezogens.get(gezogens.size()-1-i));
            //System.out.println(karten[gezogens.get(gezogens.size()-1-i)]);
            //zahl.setText("TREST");
            zahl.setText(karten[gezogens.get(gezogens.size()-1-i)]);
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
            gezogens.add(rn);
            show_schnapps();
        }
    }
}
