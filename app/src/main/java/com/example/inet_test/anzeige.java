package com.example.inet_test;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class anzeige extends MainActivity{
    private TextView txtall;
    private int nummer;
    private ArrayList<spiel> ubergabe;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anzeige);
        nummer = Integer.parseInt(getIntent().getExtras().getString("id"));
        txtall = (TextView) findViewById(R.id.hierhin);
        ubergabe=spiel_liste.get();
        StringBuilder build1=new StringBuilder();
        StringBuilder build2=new StringBuilder();
        for (spiel penner:ubergabe){
            if(penner.id==nummer){
                build1.append(penner.name);
                build2.append(penner.beschreibung);
            }
        }
        txtall.setText(build2.toString());
        setTitle(build1.toString());
    }
}
