package com.example.inet_test;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class game_over extends MainActivity implements View.OnClickListener {
    private TextView scoreboard;
    private Button newgame, menu;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        scoreboard = (TextView) findViewById(R.id.scoreboard);
        newgame = (Button) findViewById(R.id.new_game);
        menu = (Button) findViewById(R.id.menu);
        StringBuilder builder = new StringBuilder();
        builder.append("GAME OVER"+System.lineSeparator()+"*************"+System.lineSeparator()+System.lineSeparator());
        builder.append("Dein Score war nur eine: " + getIntent().getExtras().getInt("score"));
        scoreboard.setText(builder.toString());
        newgame.setOnClickListener(this);
        menu.setOnClickListener(this);
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
            default:
                break;
        }
    }
}
