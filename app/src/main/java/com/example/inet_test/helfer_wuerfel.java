package com.example.inet_test;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class helfer_wuerfel extends sp_helfer implements View.OnClickListener {
    private Button wuerfel;
    private EditText et;
    private LinearLayout parent;
    private TextView tv;
    private ImageView iv;
    private LinearLayout zeile;
    private tinydb db;
    private int anzahl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helfer_wuerfel);
        setTitle("Würfel");

        zeile = new LinearLayout(this);
        zeile.setOrientation(LinearLayout.HORIZONTAL);

        et = findViewById(R.id.et_wuerf);
        wuerfel=findViewById(R.id.do_wurf);
        parent=findViewById(R.id.erg_parent);

        wuerfel.setOnClickListener(this);

        db=new tinydb(this);
        try {
            anzahl=db.getInt("wurfel_helfer");
        } catch (Exception e){
            System.out.println(e.toString());
            anzahl=11;
            db.putInt("wurfel_helfer", anzahl);
        }
        et.setText(String.valueOf(anzahl));
    }
    @Override
    public void onClick(View v){
        if (v.getId()==R.id.do_wurf){
            parent.removeAllViews();
            zeile.removeAllViews();
            do_wuerfel();
        }
    }

    private void do_wuerfel(){
        int input = 1;
        int zahl = 1;
        final int min = 1;
        final int max = 6;
        int erg = 0;
        String temp = et.getText().toString();
        try {
            input=Integer.parseInt(temp);
        } catch (Exception e){
            input=0;
        }
        if(input<1){
            input=1;
            et.setText("1");
        }
        db.putInt("wurfel_helfer", input);
        int i = 0;
        while (i<input){
            if (i%5==4){
                parent.addView(zeile);
                zeile = new LinearLayout(this);
                zeile.setOrientation(LinearLayout.HORIZONTAL);
            }
            zahl = new Random().nextInt((max - min) + 1) + min;
            erg = erg+zahl;
            iv = new ImageView(this);
            switch (zahl){
                case 1:{
                    iv.setImageDrawable(getDrawable(R.drawable.die_1));
                    break;
                }
                case 2:{
                    iv.setImageDrawable(getDrawable(R.drawable.die_2));
                    break;
                }
                case 3:{
                    iv.setImageDrawable(getDrawable(R.drawable.die_3));
                    break;
                }
                case 4:{
                    iv.setImageDrawable(getDrawable(R.drawable.die_4));
                    break;
                }
                case 5:{
                    iv.setImageDrawable(getDrawable(R.drawable.die_5));
                    break;
                }
                case 6:{
                    iv.setImageDrawable(getDrawable(R.drawable.die_6));
                    break;
                }
            }
            iv.setBackgroundColor(Color.parseColor("#ffffff"));
            if (zahl==6){
                iv.setBackgroundColor(Color.parseColor("#ff0000"));
            }
            iv.requestLayout();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100,100);
            iv.setLayoutParams(lp);
            zeile.addView(iv);
            if (i!=input-1) {
                tv = new TextView(this);
                tv.setText(" + ");
                tv.setTextColor(Color.parseColor("#ffffff"));
                tv.setTextSize(22);
                zeile.addView(tv);

            }
            i++;
        }
        tv = new TextView(this);
        tv.setText(" = ");
        tv.setTextColor(Color.parseColor("#ffffff"));
        tv.setTextSize(22);
        zeile.addView(tv);
        tv = new TextView(this);
        tv.setText(String.valueOf(erg));
        tv.setTextColor(Color.parseColor("#ffffff"));
        tv.setTextSize(33);
        tv.setTextColor(Color.parseColor("#ff0000"));
        zeile.addView(tv);
        parent.addView(zeile);
    }
}
