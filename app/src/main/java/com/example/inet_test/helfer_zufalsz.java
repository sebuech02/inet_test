package com.example.inet_test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class helfer_zufalsz extends sp_helfer implements View.OnClickListener {
    private Button eingabe;
    private EditText et_min, et_max;
    private TextView erg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helfer_zzahl);
        setTitle("Zufallszahl");
        eingabe = findViewById(R.id.do_zz);
        et_min=findViewById(R.id.et_min);
        et_max=findViewById(R.id.et_max);
        erg=findViewById(R.id.erg_zz);

        eingabe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v.getId()==R.id.do_zz){
            gen_zz();
        }
    }

    private void gen_zz(){
        int min, max;
        String temp = et_min.getText().toString();
        min = Integer.parseInt(temp);
        temp = et_max.getText().toString();
        max = Integer.parseInt(temp);
        int zahl = new Random().nextInt((max - min) + 1) + min;
        erg.setText(String.valueOf(zahl));
    }
}
