package com.example.inet_test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.text.DateTimePatternGenerator;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class pumpen_hub extends MainActivity implements View.OnClickListener {
    public LinearLayout temp;
    public TextView tv;
    public Button bi, bd;

    public LinearLayout parent;
    private boolean vibe_on;
    private tinydb db;
    public Button ep, dum_von, share, reset;
    private static long LAST_CLICK_TIME = 0;
    private final int mDoubleClickInterval = 400;
    private Vibrator vibe;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pumpen_hub);
        setTitle("Pumpen-Übersicht");
        vibe=getApplicationContext().getSystemService(Vibrator.class);
        parent = findViewById(R.id.edit_list);
        util.init_pumpen();
        util.add_spieler("#1");
        load_pumpen();
        build_list(parent);
        ep = (Button) findViewById(R.id.ep);
        dum_von = (Button) findViewById(R.id.dum_von);
        share = (Button) findViewById(R.id.share);
        reset = (Button) findViewById(R.id.reset);

        ep.setOnClickListener(this);
        dum_von.setOnClickListener(this);
        share.setOnClickListener(this);
        reset.setOnClickListener(this);

        db = new tinydb(this);
        vibe_on=db.getBoolean_true("vibe");
        db.putBoolean("vibe", vibe_on);
    }


    //@android.support.annotation.RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void build_list(LinearLayout parent) {
        //TODO: Hier vielleicht ein Grid raus machen. So passen die allignments leider nicht ganz perfekt

        parent.removeAllViews();
        load_pumpen();
        int i = 1;
        Log.i("Hinweis", util.getSpieler().toString());
        for (String name: util.getSpieler()){
            if (util.getPumpen().get(util.getSpieler().indexOf(name))==0 && util.getDummes().get(util.getSpieler().indexOf(name))==0){
                remove_player(name);
            }
        }
        load_pumpen();
        for (String name : util.getSpieler()) {
            temp = new LinearLayout(pumpen_hub.this);
            temp.setOrientation(LinearLayout.HORIZONTAL);
            temp.setBackground(this.getResources().getDrawable(R.color.black));
            //temp.setTextColor(getResources().getColor(R.color.white));
            //temp.setId(i);
            tv = new TextView(pumpen_hub.this);
            tv.setBackground(this.getResources().getDrawable(R.color.black));
            tv.setTextColor(getResources().getColor(R.color.white));
            int lenge=30-2*util.getSpieler().get(util.getSpieler().indexOf(name)).length();
            int j=0;
            String split="";
            while (j<lenge){
                split=split+" ";
                j++;
            }
            tv.setText("  "+name + ":" +split+ String.valueOf(util.getPumpen().get(util.getSpieler().indexOf(name))) + "   Dummes: " + String.valueOf(util.getDummes().get(util.getSpieler().indexOf(name))));
            tv.setTextSize(16);
            bi = new Button(pumpen_hub.this);
            bi.setBackground(this.getResources().getDrawable(R.color.black));
            bi.setTextColor(getResources().getColor(R.color.white));
            bi.setText("+");
            bi.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            bi.setTextSize(16);
            bi.setMaxWidth(20);
            bi.setId(i);
            bi.setTag(Integer.toString(i));
            bi.setOnClickListener(this);
            i++;
            bd = new Button(pumpen_hub.this);
            bd.setBackground(this.getResources().getDrawable(R.color.black));
            bd.setTextColor(getResources().getColor(R.color.white));
            bd.setText("-");
            bd.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            bd.setTextSize(16);
            bd.setMaxWidth(20);
            bd.setId(i);
            bd.setTag(Integer.toString(i));
            bd.setOnClickListener(this);
            i++;
            temp.addView(tv);
            temp.addView(bi);
            temp.addView(bd);
            parent.addView(temp);
        }
    }

    @Override
    public void onClick(View v) {
        //save_pumpen();
        switch (v.getId()) {
            case R.id.ep: {
                save_pumpen();
                build_list(parent);
                startActivity(new Intent(pumpen_hub.this, spieler_auswahl.class));
                break;
            }
            case R.id.dum_von: {
                save_pumpen();
                build_list(parent);
                startActivity(new Intent(pumpen_hub.this, spieler_auswahl_dummes.class));
                break;
            }
            case R.id.share: {
                share_pumpen();
                save_pumpen();
                build_list(parent);
                break;
            }
            case R.id.reset: {
                long doubleClickCurrentTime = System.currentTimeMillis();
                long currentClickTime = System.currentTimeMillis();
                if (currentClickTime - LAST_CLICK_TIME <= mDoubleClickInterval)
                {
                    reset_pumpen();
                }
                else
                {
                    Toast.makeText(this, "Doppel-Tap für Reset", Toast.LENGTH_SHORT).show();
                    LAST_CLICK_TIME = System.currentTimeMillis();
                    // !Warning, Single click action problem
                }
                save_pumpen();
                build_list(parent);
                break;
            }
            default:{
                Log.i("hine","wir waren hier");
                if (1==1){
                    determine_action(v.getId());
                }
                break;
            }
        }
    }

    public void determine_action(int str) {
        int input = str;
        load_pumpen();
        if (input % 2 == 0) {
            int index = (input - 2) / 2;
            Log.i("Minus",util.getSpieler().get(index));
            if (util.getPumpen().get(index)>0){
                util.minus_pumpe(util.getSpieler().get(index));
                if (vibe_on){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        long[] temp = new long[] {0,100, 0, 100};
                        vibe.vibrate(VibrationEffect.createWaveform(temp, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibe.vibrate(200);
                    }
                }
            }
        } else {
            int index = (input - 1) / 2;
            Log.i("Plus",util.getSpieler().get(index));
            Log.i("hinweis",Integer.toString(index));
            Log.i("hinweis",util.getSpieler().toString());
            util.add_pumpe(util.getSpieler().get(index));
            if (vibe_on){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    long[] temp = new long[] {0,100, 0, 100};
                    vibe.vibrate(VibrationEffect.createWaveform(temp, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibe.vibrate(200);
                }
            }
        }
        save_pumpen();
        build_list(parent);
    }

    public void save_pumpen() {
        tinydb.putListString("spieler", util.getSpieler());
        tinydb.putListInt("pumpen", util.getPumpen());
        tinydb.putListInt("dummes", util.getDummes());
    }

    public void load_pumpen(){
        util.setSpieler(tinydb.getListString("spieler"));
        util.setPumpen(tinydb.getListInt("pumpen"));
        util.setDummes(tinydb.getListInt("dummes"));
    }

    public void remove_player(String name){
        if (name!="#1"){
            util.remove_player(name);
            save_pumpen();
        }
    }
    public void reset_pumpen(){
        util.setPumpen(new ArrayList<Integer>());
        util.setDummes(new ArrayList<Integer>());
        util.setSpieler(new ArrayList<String>());
    }
    public void share_pumpen(){
        load_pumpen();
        String share = share_builder();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, share);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
    public String share_builder(){
        int i=0;
        String output="";
        ArrayList<String> spieler=util.getSpieler();
        ArrayList<Integer> pumpen=util.getPumpen();
        ArrayList<Integer> dummes=util.getDummes();
        while(i<spieler.size()){
            output=output+spieler.get(i)+": "+pumpen.get(i)+"  Dummes: "+dummes.get(i);
            output=output+System.lineSeparator();
            i++;
        }
        output=output+"Glückwunsch an "+spieler.get(0)+" mit seinen "+pumpen.get(0)+" Pumpen."+System.lineSeparator()+"Starke Leistung!";
        return output;
    }

    public void onBackPressed() {
        save_pumpen();
        startActivity(new Intent(pumpen_hub.this, zweiteSeite.class));
    }
}

