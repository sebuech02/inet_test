package com.example.inet_test;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class crabby_over extends MainActivity implements View.OnClickListener {
    private TextView scoreboard, scores;
    private Button newgame, menu, send_score;
    private EditText name_score;
    private ArrayList<strintstr> besten = new ArrayList<strintstr>();
    private int i;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crabby_over);
        scoreboard = (TextView) findViewById(R.id.scoreboard2);
        newgame = (Button) findViewById(R.id.new_game2);
        menu = (Button) findViewById(R.id.menu2);
        scores = (TextView) findViewById(R.id.scores2);
        send_score = (Button) findViewById(R.id.send_score2);
        name_score = (EditText) findViewById(R.id.name_score2);
        //name_score.setInputType(InputType.TYPE_NULL);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(name_score.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        StringBuilder builder = new StringBuilder();
        builder.append("GAME OVER"+System.lineSeparator()+"*************"+System.lineSeparator()+System.lineSeparator());
        builder.append("Dein Score war eine: " + getIntent().getExtras().getInt("score")+System.lineSeparator()+"Gestorben durch "+getIntent().getExtras().getString("ursache"));
        scoreboard.setText(builder.toString());
        newgame.setOnClickListener(this);
        menu.setOnClickListener(this);
        send_score.setOnClickListener(this);
        besten = ranglisten_daten.get_scores();
        StringBuilder liste = new StringBuilder();
        //liste.append("test"+System.lineSeparator());
        if(besten != null) {
            i = 1;
            for (strintstr e : besten) {
                liste.append(i + ". " + e.text1 + "   " + e.zahl + "   " + e.text2);
                liste.append(System.lineSeparator());
                i = i + 1;
            }
            scores.setText(liste.toString());
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.new_game2:
                Toast.makeText(this,"Lade Assets ...", Toast.LENGTH_LONG).show();
                startActivity(new Intent(crabby_over.this, crabby_main.class));
                break;
            case R.id.menu2:
                startActivity(new Intent(crabby_over.this, zweiteSeite.class));
                break;
            case R.id.name_score2:
                name_score.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(name_score, InputMethodManager.SHOW_IMPLICIT);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                break;
            case R.id.send_score2:
                sendscores();
                break;
            default:
                break;
        }
    }

    private void sendscores(){
        StringBuilder build = new StringBuilder();
        build.append("<score>"+System.lineSeparator() +
                "        <name>"+name_score.getText()+"</name>"+System.lineSeparator() +
                "        <punkte>"+getIntent().getExtras().getInt("score")+"</punkte>"+System.lineSeparator() +
                "        <ursache>"+getIntent().getExtras().getString("ursache")+"</ursache>"+System.lineSeparator() +
                "    </score>");
        String message = build.toString();
        String mail = "sebastian.buechler@tutanota.de";
        javamailapi  mailapi = new javamailapi(this, mail, "Neuer Score", message);
        mailapi.execute();
    }
}
