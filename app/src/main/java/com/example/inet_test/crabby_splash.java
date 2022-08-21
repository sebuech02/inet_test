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
        build.append("********************"+System.lineSeparator());
        build.append(System.lineSeparator());
        build.append("In diesem Spiel muss Niecklaß möglichst viele leckere Südfrüchte fressen, dafür steht ihm eine große Auswahl zur Verfügung. Aufpassen muss er jedoch auf die ungenießbaren Früchte. Isst er zuviel davon, so muss Dennemark leider ins Krankenhaus. Ab und zu gibt es noch die Chance auf **Placeholder**, dann dreht Niecklaß komplett durch und die bösen Früchte können ihm nichtmehr wehtun."+System.lineSeparator());
        build.append("Viel Erfolg Niecklaß möglichst satt zu machen!");
        build.append(System.lineSeparator());
        build.append("Dieses Spiel ist gespert, bis die dev-Version fertig ist.");
        beschreibung.setText(build.toString());
        //start_game.setOnClickListener(this);
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
