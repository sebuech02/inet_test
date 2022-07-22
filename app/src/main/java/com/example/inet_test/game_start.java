package com.example.inet_test;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

public class game_start  extends MainActivity implements View.OnClickListener {
    public Button start_game;
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_start);
        start_game = (Button) findViewById(R.id.game_start);
        start_game.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.game_start:
                startActivity(new Intent(game_start.this, main_game.class));
                break;
            default:
                break;
        }
    }
}
