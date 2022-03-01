package com.example.inet_test;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class zweiteSeite extends MainActivity{
    private ArrayList<spiel> ubergabe;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zweiteseite);
        tv=(TextView) findViewById(R.id.texts2);
        ubergabe=spiel_liste.get();
        for (spiel penner : ubergabe){
            tv.setText(penner.name);
            break;
        }
    }
}
