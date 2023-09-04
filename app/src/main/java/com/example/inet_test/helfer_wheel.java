package com.example.inet_test;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class helfer_wheel extends sp_helfer implements View.OnClickListener {
    private Button add, pick, del, team;
    private LinearLayout parent, zeile;
    private EditText input;
    private TextView tv;
    private ArrayList<String> objects = new ArrayList<>();
    private int current_pick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helfer_wheel);
        setTitle("Zufallspicker");

        add=findViewById(R.id.do_add);
        pick=findViewById(R.id.do_pick);
        parent=findViewById(R.id.choosable_list);
        input=findViewById(R.id.et_obj);
        team=findViewById(R.id.do_team);

        add.setOnClickListener(this);
        pick.setOnClickListener(this);
        team.setOnClickListener(this);
        current_pick=-1;
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
                break;
            }
            case R.id.do_pick:{
                pick_random();
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
            }
            lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(1,11,1,11);
            del=new Button(this);
            del.setTextColor(Color.parseColor("#ffffff"));
            del.setBackgroundColor(Color.parseColor("#000000"));
            //del.setBackground(getDrawable(R.drawable.shape_cad));
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
