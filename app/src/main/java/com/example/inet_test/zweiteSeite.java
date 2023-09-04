package com.example.inet_test;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Comparator;
import java.util.Random;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class zweiteSeite extends MainActivity implements View.OnClickListener{
    private Button alles;
    private Button free;
    private Button tdm;
    private Button duoq;
    private Button bonus;
    private Button atf;
    private Button random;
    private Button rang;
    private Button bier;
    private Button pumpen;
    private Button spielmachen;
    private Button kegeln;
    private Button crabby;
    private Button mario;
    private Button helfer;
    private ArrayList<spiel> ubergabe;
    private TextView tv;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zweiteseite);
        setTitle("Menü");
        alles=(Button) findViewById(R.id.button1);
        free=(Button) findViewById(R.id.button2);
        tdm=(Button) findViewById(R.id.button3);
        duoq=(Button) findViewById(R.id.button4);
        bonus=(Button) findViewById(R.id.button5);
        atf=(Button) findViewById(R.id.button6);
        random=(Button) findViewById(R.id.button7);
        rang=(Button) findViewById(R.id.button8);
        //bier=(Button) findViewById(R.id.button9);
        pumpen=(Button) findViewById(R.id.button9);
        spielmachen=(Button) findViewById(R.id.button10);
        kegeln =(Button) findViewById(R.id.button11);
        crabby = (Button) findViewById(R.id.button12);
        mario = (Button) findViewById(R.id.button13);
        helfer = (Button) findViewById(R.id.buttonh);
        ubergabe=spiel_liste.get();
        alles.setOnClickListener(this);
        free.setOnClickListener(this);
        tdm.setOnClickListener(this);
        duoq.setOnClickListener(this);
        bonus.setOnClickListener(this);
        atf.setOnClickListener(this);
        random.setOnClickListener(this);
        rang.setOnClickListener(this);
        pumpen.setOnClickListener(this);
        //bier.setOnClickListener(this);
        spielmachen.setOnClickListener(this);
        kegeln.setOnClickListener(this);
        crabby.setOnClickListener(this);
        mario.setOnClickListener(this);
        helfer.setOnClickListener(this);
        bier_local.init();

    }
        @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button1:
                startActivity(new Intent(zweiteSeite.this, k_alles.class));
                break;
            case R.id.button2:
                startActivity(new Intent(zweiteSeite.this, k_free.class));
                break;
            case R.id.button3:
                startActivity(new Intent(zweiteSeite.this, k_tdm.class));
                break;
            case R.id.button4:
                startActivity(new Intent(zweiteSeite.this, k_duoq.class));
                break;
            case R.id.button5:
                startActivity(new Intent(zweiteSeite.this, k_bonus.class));
                break;
            case R.id.button6:
                startActivity(new Intent(zweiteSeite.this, k_atf.class));
                break;
            case R.id.button7:
                int l=Spiele.size();
                Random rand=new Random();
                int n=rand.nextInt(l)+1;
                Intent actvar = new Intent(zweiteSeite.this, anzeige.class);
                actvar.putExtra("id", Integer.toString(n));
                startActivity(actvar);
                break;
            case R.id.button8:
                startActivity(new Intent(zweiteSeite.this, bestenliste.class));
                break;
            case R.id.button9:
                startActivity(new Intent(zweiteSeite.this, pumpen_hub.class));
                break;
            case R.id.button10:
                startActivity(new Intent(zweiteSeite.this, spielerstellen.class));
                break;
            case R.id.button11:
                startActivity(new Intent(zweiteSeite.this, game_start.class));
                break;
            case R.id.button12:
                startActivity(new Intent(zweiteSeite.this, crabby_splash.class));
                break;
            case R.id.button13:
                startActivity(new Intent(zweiteSeite.this, mario_splash.class));
                break;
            case R.id.buttonh:
                startActivity(new Intent(zweiteSeite.this, sp_helfer.class));
                break;
            default:
                break;
            }
        }
    }
