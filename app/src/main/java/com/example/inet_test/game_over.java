package com.example.inet_test;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class game_over extends MainActivity implements View.OnClickListener {
    private TextView scoreboard, scores;
    private Button newgame, menu, send_score;
    private EditText name_score;
    private ArrayList<strintstr> besten = new ArrayList<strintstr>();
    private int i;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        scoreboard = (TextView) findViewById(R.id.scoreboard);
        newgame = (Button) findViewById(R.id.new_game);
        menu = (Button) findViewById(R.id.menu);
        scores = (TextView) findViewById(R.id.scores);
        send_score = (Button) findViewById(R.id.send_score);
        name_score = (EditText) findViewById(R.id.name_score);
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
        i = 1;
        for (strintstr e: besten){
            liste.append(i+". "+e.text1+"   "+e.zahl+"   "+e.text2);
            liste.append(System.lineSeparator());
            i = i + 1;
        }
        scores.setText(liste.toString());
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.new_game:
                startActivity(new Intent(game_over.this, main_game.class));
                break;
            case R.id.menu:
                startActivity(new Intent(game_over.this, zweiteSeite.class));
                break;
            case R.id.name_score:
                name_score.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(name_score, InputMethodManager.SHOW_IMPLICIT);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                break;
            case R.id.send_score:
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
                "        <ursche>"+getIntent().getExtras().getString("ursache")+"</ursache>"+System.lineSeparator() +
                "    </score>");
        String message = build.toString();
        String mail = "sebastian.buechler@tutanota.de";
        javamailapi  mailapi = new javamailapi(this, mail, "Neuer Score", message);
        mailapi.execute();
    }
}
