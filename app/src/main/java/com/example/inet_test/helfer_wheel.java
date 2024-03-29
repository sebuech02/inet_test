package com.example.inet_test;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class helfer_wheel extends sp_helfer implements View.OnClickListener {
    private Button add, pick, del, team;
    private LinearLayout parent, zeile;
    private EditText input;
    private TextView tv;
    private ArrayList<String> objects = new ArrayList<>();
    private int current_pick;
    private tinydb db;
    private Vibrator vibe;
    private boolean vibe_on;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helfer_wheel);
        setTitle("Zufallspicker");
        vibe=getApplicationContext().getSystemService(Vibrator.class);

        add=findViewById(R.id.do_add);
        pick=findViewById(R.id.do_pick);
        parent=findViewById(R.id.choosable_list);
        input=findViewById(R.id.et_obj);
        team=findViewById(R.id.do_team);

        add.setOnClickListener(this);
        pick.setOnClickListener(this);
        team.setOnClickListener(this);

        db=new tinydb(this);
        try {
            current_pick=db.getObject("wheel_helfer_pick", int.class);
        } catch (Exception e){
            System.out.println(e.toString());
            current_pick=-1;
            db.putObject("wheel_helfer_pick", current_pick);
        }
        try {
            objects=db.getObject("wheel_helfer_obj", ArrayList.class);
        } catch (Exception e){
            System.out.println(e.toString());
            objects=new ArrayList<>();
            db.putObject("wheel_helfer_obj", objects);
        }
        vibe_on=db.getBoolean_true("vibe");
        db.putBoolean("vibe", vibe_on);
        update_views();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.do_add:{
                if(input.getText().toString().equals("")){
                    input.setText("Platzhalter");
                }
                objects.add(input.getText().toString());
                update_views();
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
            case R.id.do_pick:{
                pick_random();
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
            case R.id.do_team:{
                add_all();
                break;
            }
            default:{
                determin_action(v);
                break;
            }
        }
    }
    private void pick_random(){
        if(objects.size()>0) {
            int min = 0;
            int max = objects.size() - 1;
            current_pick = new Random().nextInt((max - min) + 1) + min;
            update_views();
        }
    }
    private void update_views(){
        db.putObject("wheel_helfer_pick", current_pick);
        db.putObject("wheel_helfer_obj", objects);
        int i = 0;
        parent.removeAllViews();
        while (i<objects.size()){
            zeile= new LinearLayout(this);
            zeile.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            zeile.setLayoutParams(lp);
            lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(1,11,1,11);
            tv=new TextView(this);
            tv.setText(objects.get(i));
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setTextSize(22);
            tv.setGravity(Gravity.LEFT);
            tv.setLayoutParams(lp);
            if (current_pick==i){
                tv.setTextColor(Color.parseColor("#ff0000"));
                Toast.makeText(this, objects.get(i), Toast.LENGTH_SHORT).show();
            }
            lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(1,11,1,11);
            del=new Button(this);
            del.setTextColor(Color.parseColor("#ffff00"));
            del.setBackgroundColor(Color.parseColor("#000000"));
            del.setLayoutParams(lp);
            del.setTextSize(22);
            del.setText("Löschen");
            del.setId(i);
            del.setGravity(Gravity.RIGHT);
            del.setOnClickListener(this);
            zeile.addView(tv);
            zeile.addView(del);
            parent.addView(zeile);
            i++;
        }
    }
    private void determin_action(View v){
        System.out.println(v.getId());
        try {
            if (v.getId()<objects.size()){
                objects.remove(v.getId());
                current_pick=-1;
                update_views();
                if (vibe_on){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        long[] temp = new long[] {0,100, 0, 100};
                        vibe.vibrate(VibrationEffect.createWaveform(temp, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibe.vibrate(200);
                    }
                }
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
    private void add_all(){
        String[] team = this.getResources().getStringArray(R.array.team);
        for (String el: team){
            objects.add(el);
        }
        update_views();
    }
}
