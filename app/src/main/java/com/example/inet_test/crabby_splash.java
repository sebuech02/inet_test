package com.example.inet_test;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class crabby_splash extends MainActivity implements View.OnClickListener {
    public Button start_game;
    private TextView beschreibung;
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crabby_splash);
        beschreibung = (TextView) findViewById(R.id.dummyid2);
        start_game = (Button) findViewById(R.id.game_start2);
        StringBuilder build = new StringBuilder();
        build.append("CRABBY NIECKLAß"+System.lineSeparator());
        build.append("******************"+System.lineSeparator());
        build.append(System.lineSeparator());
        build.append("In diesem Spiel muss Carlos möglichst betrunken werden, dafür stehen ihm Bier und Jägermeister zur Verfügung. Aufpassen muss er jedoch auf die Cola und das ekliege Wasser. Trinkt er zuviel davon, so muss Carlos leider ins Krankenhaus. Ab und zu gibt es noch die Chance auf Vodka-E, dann dreht Carlos durch und die Cola kann ihm nichtmehr wehtun."+System.lineSeparator());
        build.append("Viel Erfolg Carlos möglichst betrunken zu machen!");
        beschreibung.setText(build.toString());
        start_game.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.game_start2:
                Toast.makeText(this, "Lade Assets ...", Toast.LENGTH_LONG).show();
                startActivity(new Intent(crabby_splash.this, crabby_main.class));
                break;
            default:
                break;
        }
    }
}
