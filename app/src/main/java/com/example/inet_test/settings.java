package com.example.inet_test;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class settings extends zweiteSeite implements View.OnClickListener{
    private RadioButton rb_name, rb_id;
    private CheckBox reverse, carlmode, vibe;
    private boolean sort_name;
    private boolean sort_reverse, carlos_act, vibe_on=true;
    private tinydb db;
    private int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        setTitle("Einstellungen");

        rb_name=findViewById(R.id.sname);
        rb_id=findViewById(R.id.sid);
        reverse=findViewById(R.id.sreverse);
        carlmode=findViewById(R.id.carlmode);
        vibe=findViewById(R.id.vibe);

        reverse.setOnClickListener(this);
        carlmode.setOnClickListener(this);
        vibe.setOnClickListener(this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        sort_name=prefs.getBoolean("sname", false);
        sort_reverse=prefs.getBoolean("srev", false);
        if(sort_name){
            rb_name.setChecked(true);
        } else{
            rb_id.setChecked(true);
        }
        reverse.setChecked(sort_reverse);

        db = new tinydb(this);
        try {
            carlos_act=db.getBoolean("carlmode");
        }catch (Exception e) {
            System.out.println(e);
            carlos_act=false;
            db.putBoolean("carlmode", carlos_act);
        }
        carlmode.setChecked(carlos_act);

        vibe_on=db.getBoolean_true("vibe");
        db.putBoolean("vibe", vibe_on);

        vibe.setChecked(vibe_on);

        counter=0;
    }

    @Override
    public void onClick(View v){
        if (v.getId()==R.id.sreverse){
            update_saves();
        } else if (v.getId()==R.id.carlmode) {
            db.putBoolean("carlmode", carlmode.isChecked());
            if (carlmode.isChecked()){
                Toast.makeText(this, "Ich leide an AIDS.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Mir gehts gut.", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId()==R.id.vibe) {
            db.putBoolean("vibe", vibe.isChecked());
        }
    }
    @Override
    public void onBackPressed(){
        counter++;
        if (carlmode.isChecked()){
            if(counter==1){
                Toast.makeText(this, "Bist du sicher, das du Carlos aktiviert lassen willst?", Toast.LENGTH_LONG).show();
            } else {
                counter=0;
                Toast.makeText(this, "Das war eine Warnung...", Toast.LENGTH_LONG).show();
                this.finish();
            }
        } else {
            this.finish();
        }
    }

    public void onRadioButtonClicked(View v){
        if (v.getId()==R.id.sname){
            rb_name.setChecked(true);
            if(!sort_name){
                update_saves();
            }
        } else if (v.getId()==R.id.sid) {
            rb_id.setChecked(true);
            if(sort_name){
                update_saves();
            }
        }
    }

    public void update_saves(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putBoolean("sname", rb_name.isChecked()).apply();
        prefs.edit().putBoolean("srev", reverse.isChecked()).apply();
    }
}
