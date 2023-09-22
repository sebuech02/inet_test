package com.example.inet_test;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class helfer_karte extends sp_helfer implements View.OnClickListener {
    private ArrayList<Integer> gezogenes;
    private LinearLayout card;
    private Button mischen, stapel;
    private RadioButton rb1, rb2, rb3;
    private int max;
    private ImageView farbe;
    private TextView zahl;
    private LinearLayout zeile;
    private tinydb db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helfer_karte);
        setTitle("Karten-ziehen");

        card=findViewById(R.id.karten);
        rb1=findViewById(R.id.k32);
        rb2=findViewById(R.id.k52);
        rb3=findViewById(R.id.k56);
        mischen=findViewById(R.id.do_shuffle);
        stapel=findViewById(R.id.rest_haufen);


        mischen.setOnClickListener(this);
        stapel.setOnClickListener(this);

        db=new tinydb(this);
        try {
            gezogenes=db.getListInt("karte_helfer");
        } catch (Exception e){
            System.out.println(e.toString());
            gezogenes=new ArrayList<Integer>();
            db.putListInt("karte_helfer", gezogenes);
        }
        try {
            max=db.getInt("karte_helfer_max");
            if(max==32){
                rb1.setChecked(true);
            } else if (max==52) {
                rb2.setChecked(true);
            } else if (max==56) {
                rb3.setChecked(true);
            }
        } catch (Exception e){
            System.out.println(e.toString());
            max=32;
            rb1.setChecked(true);
            db.putInt("karte_helfer_max", max);
        }

        update_views();
    }
    @Override
    public void onClick(View v){
        if (v.getId()==R.id.rest_haufen){
            ziehe_karten();
        } else if (v.getId()==R.id.do_shuffle) {
            reset_cards();
        }
    }
    public void onRadioButtonClicked(View v){
        if (v.getId()==R.id.k32){
            if (max==32) {
                return;
            } else {
                max=32;
                reset_cards();
            }
        } else if (v.getId()==R.id.k52){
            if (max==52){
                return;
            } else{
                max=52;
                reset_cards();
            }
        } else if (v.getId()==R.id.k56) {
            if (max==56){
                return;
            } else {
                max=56;
                reset_cards();
            }
        } else {
            reset_cards();
        }
    }
    public void reset_cards(){
        gezogenes = new ArrayList<Integer>();
        update_views();
    }
    public void ziehe_karten(){
        max = 32;
        int min = 1;
        if (rb1.isChecked()){
            max=32;
        } else if (rb2.isChecked()) {
            max=52;
        } else if (rb3.isChecked()) {
            max=56;
        } else{
            rb1.setChecked(true);
        }
        int zahl =  new Random().nextInt((max - min) + 1) + min;
        if (!gezogenes.contains(zahl)){
            gezogenes.add(zahl);
        } else if (gezogenes.size()==max) {
            Toast.makeText(this, "Deck ist alle ...", Toast.LENGTH_SHORT).show();
        } else {
            ziehe_karten();
        }
        update_views();
    }

    public void update_views(){
        db.putListInt("karte_helfer", gezogenes);
        db.putInt("karte_helfer_max", max);
        card.removeAllViews();
        int i = 0;
        if (max==32){
            while (i<gezogenes.size()){
                zeile=new LinearLayout(this);
                zeile.setOrientation(LinearLayout.HORIZONTAL);
                farbe=new ImageView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100,100);
                farbe.setLayoutParams(lp);
                farbe.setBackgroundColor(Color.parseColor("#ffffff"));
                zahl=new TextView(this);
                zahl.setTextColor(Color.parseColor("#ffffff"));
                zahl.setTextSize(22);
                int temp = gezogenes.get(gezogenes.size()-1-i);
                if (temp%4==0){
                    farbe.setImageDrawable(getDrawable(R.drawable.caro));
                    zahl.setTextColor(Color.parseColor("#ff0000"));
                } else if (temp%4==1) {
                    farbe.setImageDrawable(getDrawable(R.drawable.hearts));
                    zahl.setTextColor(Color.parseColor("#ff0000"));
                } else if (temp%4==2) {
                    farbe.setImageDrawable(getDrawable(R.drawable.pik));
                } else if (temp%4==3) {
                    farbe.setImageDrawable(getDrawable(R.drawable.kreuz));
                }
                int zz =(int) Math.floor(temp/4)+1+6;
                if (zz==11){
                    zahl.setText("Bube");
                } else if (zz==12) {
                    zahl.setText("Dame");
                } else if (zz==13) {
                    zahl.setText("König");
                } else if (zz==14) {
                    zahl.setText("Ass");
                } else {
                    zahl.setText(String.valueOf(zz));
                }

                zeile.addView(farbe);
                zeile.addView(zahl);
                card.addView(zeile);
                i++;
            }
        } else if (max==52) {
            while (i<gezogenes.size()){
                zeile=new LinearLayout(this);
                zeile.setOrientation(LinearLayout.HORIZONTAL);
                farbe=new ImageView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100,100);
                farbe.setLayoutParams(lp);
                farbe.setBackgroundColor(Color.parseColor("#ffffff"));
                zahl=new TextView(this);
                zahl.setTextColor(Color.parseColor("#ffffff"));
                zahl.setTextSize(22);
                int temp = gezogenes.get(gezogenes.size()-1-i);
                if (temp%4==0){
                    farbe.setImageDrawable(getDrawable(R.drawable.caro));
                    zahl.setTextColor(Color.parseColor("#ff0000"));
                } else if (temp%4==1) {
                    farbe.setImageDrawable(getDrawable(R.drawable.hearts));
                    zahl.setTextColor(Color.parseColor("#ff0000"));
                } else if (temp%4==2) {
                    farbe.setImageDrawable(getDrawable(R.drawable.pik));
                } else if (temp%4==3) {
                    farbe.setImageDrawable(getDrawable(R.drawable.kreuz));
                }
                int zz =(int) Math.floor(temp/4)+1+1;
                if (zz==11){
                    zahl.setText("Bube");
                } else if (zz==12) {
                    zahl.setText("Dame");
                } else if (zz==13) {
                    zahl.setText("König");
                } else if (zz==14) {
                    zahl.setText("Ass");
                } else {
                    zahl.setText(String.valueOf(zz));
                }

                zeile.addView(farbe);
                zeile.addView(zahl);
                card.addView(zeile);
                i++;
            }

        } else if (max==56) {
            while (i<gezogenes.size()){
                zeile=new LinearLayout(this);
                zeile.setOrientation(LinearLayout.HORIZONTAL);
                farbe=new ImageView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100,100);
                farbe.setLayoutParams(lp);
                farbe.setBackgroundColor(Color.parseColor("#ffffff"));
                zahl=new TextView(this);
                zahl.setTextColor(Color.parseColor("#ffffff"));
                zahl.setTextSize(22);
                int temp = gezogenes.get(gezogenes.size()-1-i);
                if (temp%4==0){
                    farbe.setImageDrawable(getDrawable(R.drawable.caro));
                    zahl.setTextColor(Color.parseColor("#ff0000"));
                } else if (temp%4==1) {
                    farbe.setImageDrawable(getDrawable(R.drawable.hearts));
                    zahl.setTextColor(Color.parseColor("#ff0000"));
                } else if (temp%4==2) {
                    farbe.setImageDrawable(getDrawable(R.drawable.pik));
                } else if (temp%4==3) {
                    farbe.setImageDrawable(getDrawable(R.drawable.kreuz));
                }
                int zz =(int) Math.floor(temp/4)+1+1;
                if (zz==11){
                    zahl.setText("Bube");
                } else if (zz==12) {
                    zahl.setText("Dame");
                } else if (zz==13) {
                    zahl.setText("König");
                } else if (zz==14) {
                    zahl.setText("Ass");
                } else if (zz==15) {
                    zahl.setText("Jocker");
                    zahl.setTextColor(Color.parseColor("#0000ff"));
                } else {
                    zahl.setText(String.valueOf(zz));
                }
                zeile.addView(farbe);
                zeile.addView(zahl);
                card.addView(zeile);
                i++;
            }
        }
        stapel.setText(String.valueOf(max-gezogenes.size()));
        if (max-gezogenes.size()<10){
            stapel.setText("0"+String.valueOf(max-gezogenes.size()));
        }
    }
}
