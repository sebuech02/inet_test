package com.example.inet_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class spieler_auswahl_dummes extends MainActivity implements View.OnClickListener {
    private String[] alle_spieler={"#1","Anabol","Wiecklaß","Mexiko","Langer","Redabol","Errich","Zwerg","Spakko","Zugpferd","Tolky","Tolky","Driver","Blome","Jeremias","Moritz","Pasckaal","Timm","Lion", "Gast1","Gast2","Gast3","Gast4","Gast5","Weizen","Bier", "Schnapps","Cola"};
    private ArrayList<String> spieler;
    private LinearLayout parent;
    private Button add_player;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spieler_list);
        setTitle("Wer tat was Dummes?");
        LinearLayout parent = findViewById(R.id.player_list);
        spieler=new ArrayList<>(Arrays.asList(alle_spieler));
        load_pumpen();
        int i=1;
        for (String el: spieler){
            add_player = new Button(spieler_auswahl_dummes.this);
            add_player.setId(i);
            add_player.setText(el);
            add_player.setTag(el);
            add_player.setBackground(this.getResources().getDrawable(R.color.black));
            add_player.setTextColor(getResources().getColor(R.color.white));
            parent.addView(add_player);
            add_player.setOnClickListener(this);
            i++;
        }

    }
    public ArrayList<String> ermittle_delta(String[] spieler){
        ArrayList<String> temp = new ArrayList<String>();
        for (String el:spieler){
            if (!util.getSpieler().contains(el)){
                temp.add(el);
            }
        }
        return temp;
    }

    @Override
    public void onClick(View v) {
        save_pumpen();
        String str = v.getTag().toString();
        Log.i("Hinweis",str);
        if(!(str.equals("null"))) {
            if (!util.getSpieler().contains(str)){
                util.add_spieler_dumm(str);
            }
            util.add_dummes(str);
            save_pumpen();
            Log.i("Hinweis",util.getSpieler().toString());
            Intent actvar = new Intent(spieler_auswahl_dummes.this, pumpen_hub.class);
            startActivity(actvar);
        }
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
}
