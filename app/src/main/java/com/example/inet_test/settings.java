package com.example.inet_test;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class settings extends zweiteSeite implements View.OnClickListener{
    private RadioButton rb_name, rb_id;
    private CheckBox reverse;
    private boolean sort_name;
    private boolean sort_reverse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        setTitle("Einstellungen");

        rb_name=findViewById(R.id.sname);
        rb_id=findViewById(R.id.sid);
        reverse=findViewById(R.id.sreverse);

        reverse.setOnClickListener(this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        sort_name=prefs.getBoolean("sname", false);
        sort_reverse=prefs.getBoolean("srev", false);
        if(sort_name){
            rb_name.setChecked(true);
        } else{
            rb_id.setChecked(true);
        }
        reverse.setChecked(sort_reverse);
    }

    @Override
    public void onClick(View v){
        if (v.getId()==R.id.sreverse){
            update_saves();
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
