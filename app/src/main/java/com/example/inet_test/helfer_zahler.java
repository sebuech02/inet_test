package com.example.inet_test;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class helfer_zahler extends sp_helfer implements View.OnClickListener{
    private LinearLayout container, zeile;
    private Button del_werte, del, add, add_tdm, add_ffa, selection, delete;
    private Button pl1, pl2, pl3, pl4, pl5, pl6, pl7, pl8, pl9, pl0, plp;
    private EditText to_add;
    private TextView t_name, t_score;
    private ScrollView scv;
    public score_helper scores;
    public tinydb db;
    private Vibrator vibe;
    private boolean vibe_on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helfer_zahler);
        setTitle("Punkte-tracken");

        vibe=getApplicationContext().getSystemService(Vibrator.class);


        container=findViewById(R.id.choosable_list);
        del_werte=findViewById(R.id.do_del_werte);
        del=findViewById(R.id.do_del_alles);
        add=findViewById(R.id.do_add);
        add_tdm=findViewById(R.id.do_team);
        add_ffa=findViewById(R.id.do_ffa);
        to_add=findViewById(R.id.et_obj);
        pl1=findViewById(R.id.plus1);
        pl2=findViewById(R.id.plus2);
        pl3=findViewById(R.id.plus3);
        pl4=findViewById(R.id.plus4);
        pl5=findViewById(R.id.plus5);
        pl6=findViewById(R.id.plus6);
        pl7=findViewById(R.id.plus7);
        pl8=findViewById(R.id.plus8);
        pl9=findViewById(R.id.plus9);
        pl0=findViewById(R.id.plus0);
        plp=findViewById(R.id.plusp);
        scv=findViewById(R.id.kasten_scroll);

        del_werte.setOnClickListener(this);
        del.setOnClickListener(this);
        add.setOnClickListener(this);
        add_tdm.setOnClickListener(this);
        add_ffa.setOnClickListener(this);
        pl1.setOnClickListener(this);
        pl2.setOnClickListener(this);
        pl3.setOnClickListener(this);
        pl4.setOnClickListener(this);
        pl5.setOnClickListener(this);
        pl6.setOnClickListener(this);
        pl7.setOnClickListener(this);
        pl8.setOnClickListener(this);
        pl9.setOnClickListener(this);
        pl0.setOnClickListener(this);
        plp.setOnClickListener(this);


        db=new tinydb(this);
        try {
            scores=db.getObject("zahler_helfer", score_helper.class);
        } catch (Exception e){
            System.out.println(e.toString());
            scores=new score_helper();
            scores.init();
            db.putObject("zahler_helfer", scores);
        }

        vibe_on=db.getBoolean_true("vibe");
        db.putBoolean("vibe", vibe_on);

        //scores.init();
        update_views();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.do_del_werte:{
                scores.reset_wurfe();
                update_views();
                break;
            }
            case R.id.do_del_alles:{
                scores.init();
                update_views();
                break;
            }
            case R.id.do_add:{
                if (to_add.getText().toString().equals("")) {
                    Toast.makeText(this, "Musst auch was eingeben...", Toast.LENGTH_SHORT).show();
                }
                else {
                    String temp = to_add.getText().toString();
                    if (!scores.names.contains(to_add.getText().toString())){
                        to_add.setText("");
                    } else{
                        Toast.makeText(this, "Der ist schon da", Toast.LENGTH_SHORT).show();
                    }
                    scores.add_name(temp);
                    update_views();
                    if (vibe_on){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            long[] tempp = new long[] {0,100, 0, 100};
                            vibe.vibrate(VibrationEffect.createWaveform(tempp, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            vibe.vibrate(200);
                        }
                    }
                }

                break;
            }
            case R.id.do_team:{
                scores.add_name("Wand");
                scores.add_name("Tafel");
                update_views();
                break;
            }
            case R.id.do_ffa:{
                String[] team = this.getResources().getStringArray(R.array.team);
                for (String el: team){
                    scores.add_name(el);
                }
                update_views();
                break;
            }
            case R.id.plus1:{
                scores.wurf("1");
                scrolltoplayer();
                update_views();
                break;
            }
            case R.id.plus2:{
                scores.wurf("2");
                scrolltoplayer();
                update_views();
                break;
            }
            case R.id.plus3:{
                scores.wurf("3");
                scrolltoplayer();
                update_views();
                break;
            }
            case R.id.plus4:{
                scores.wurf("4");
                scrolltoplayer();
                update_views();
                break;
            }
            case R.id.plus5:{
                scores.wurf("5");
                scrolltoplayer();
                update_views();
                break;
            }
            case R.id.plus6:{
                scores.wurf("6");
                scrolltoplayer();
                update_views();
                break;
            }
            case R.id.plus7:{
                scores.wurf("7");
                scrolltoplayer();
                update_views();
                break;
            }
            case R.id.plus8:{
                scores.wurf("8");
                scrolltoplayer();
                update_views();
                break;
            }
            case R.id.plus9:{
                scores.wurf("9");
                scrolltoplayer();
                update_views();
                break;
            }
            case R.id.plus0:{
                scores.wurf("0");
                scrolltoplayer();
                update_views();
                break;
            }
            case R.id.plusp:{
                scores.wurf("P");
                scrolltoplayer();
                update_views();
                break;
            }
            default:{
                determine_action(v.getId());
                break;
            }
        }
    }
    private void update_views(){
        db.putObject("zahler_helfer", scores);
        container.removeAllViews();
        int i=0;
        for(String name:scores.names){
            zeile= new LinearLayout(this);
            zeile.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            zeile.setLayoutParams(lp);

            lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(1,11,1,11);
            delete=new Button(this);
            delete.setText("DEL");
            delete.setTextColor(Color.parseColor("#ffffff"));
            delete.setBackgroundColor(Color.parseColor("#000000"));
            delete.setTextSize(18);
            delete.setGravity(Gravity.LEFT);
            delete.setLayoutParams(lp);
            delete.setId(1000+i);
            delete.setOnClickListener(this);

            lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(1,11,1,11);
            selection=new Button(this);
            selection.setText("Auswählen");
            selection.setBackgroundColor(Color.parseColor("#000000"));
            selection.setTextColor(Color.parseColor("#ffffff"));
            if (scores.auswahlstatus.get(i)){
                selection.setText("Abwählen");
                selection.setTextColor(Color.parseColor("#ff0000"));
                //selection.setBackgroundColor(Color.parseColor("#ff0000"));
            }

            selection.setTextSize(18);
            selection.setGravity(Gravity.RIGHT);
            selection.setLayoutParams(lp);
            selection.setId(2000+i);
            selection.setOnClickListener(this);

            lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10,11,10,11);
            t_name=new TextView(this);
            t_name.setText(name.toUpperCase());
            t_name.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t_name.setTextColor(Color.parseColor("#ffff00"));
            t_name.setTextSize(22);
            t_name.setGravity(Gravity.CENTER);
            t_name.setLayoutParams(lp);
            t_name.setPaintFlags(t_name.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            t_name.setPaintFlags(t_name.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
            //t_name.setBackground(getDrawable(R.drawable.shape_cad));
            if (scores.auswahlstatus.get(i)){
                t_name.setTextColor(Color.parseColor("#000000"));
                t_name.setBackgroundColor(Color.parseColor("#ff0000"));
            }

            zeile.addView(delete);
            zeile.addView(t_name);
            zeile.addView(selection);
            zeile.setId(3000+i);
            container.addView(zeile);

            zeile= new LinearLayout(this);
            zeile.setOrientation(LinearLayout.HORIZONTAL);
            lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            zeile.setLayoutParams(lp);

            lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10,0,10,11);
            t_name=new TextView(this);
            String temp="";
            int j=0;
            int tempsocre9=0;
            int tempsocre0=0;
            while (j<scores.wurfe.get(i).size()){
                if(j!=scores.wurfe.get(i).size()-1){
                    temp=temp+scores.wurfe.get(i).get(j)+" + ";
                } else {
                    temp=temp+scores.wurfe.get(i).get(j);
                }
                if (scores.wurfe.get(i).get(j).equals("P")){
                    tempsocre9=tempsocre9+9;
                } else{
                    tempsocre0=tempsocre0+Integer.parseInt(scores.wurfe.get(i).get(j));
                    tempsocre9=tempsocre9+Integer.parseInt(scores.wurfe.get(i).get(j));
                }
                j++;
            }
            temp=temp+" = "+String.valueOf(tempsocre0)+"/"+String.valueOf(tempsocre9);
            t_name.setText(temp);
            t_name.setTextColor(Color.parseColor("#ffffff"));
            t_name.setTextSize(22);
            t_name.setGravity(Gravity.CENTER);
            t_name.setLayoutParams(lp);

            zeile.addView(t_name);
            container.addView(zeile);
            i++;
        }
    }


    private void determine_action(int in){
        if(999<in && 2000>in){
            int id = in-1000;
            scores.remove_name(scores.names.get(id));
            update_views();
        } else if (1999<in && 3000>in) {
            int id = in-2000;
            if (scores.auswahlstatus.get(id)){
                scores.unselect_someone(scores.names.get(id));
            } else{
                scores.select_someone(scores.names.get(id));
            }
            update_views();
        }
    }

    private void scrolltoplayer(){
        //Vibrator vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        //System.out.println("Ich soll vibebn");
        if (vibe_on){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                long[] temp = new long[] {0,100, 0, 100};
                vibe.vibrate(VibrationEffect.createWaveform(temp, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibe.vibrate(200);
            }
        }

        ArrayList<Boolean> status = scores.getAuswahlstatus();
        int i = 0;
        View v;
        while (i<status.size()){
            if (status.get(i)){
                v=findViewById(3000+i);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    scv.scrollTo((int) v.getX(),(int) v.getY());
                }
            }
            i++;
        }
    }
}
