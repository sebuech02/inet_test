package com.example.inet_test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        try {
            min = Integer.parseInt(temp);
        } catch (Exception e){
            min=0;
            et_min.setText("0");
        }
        temp = et_max.getText().toString();
        try {
            max = Integer.parseInt(temp);
        } catch (Exception e){
            max=0;
            et_max.setText("0");
        }
        if (max<min){
            int tt = max;
            max=min;
            min=tt;
            //Toast.makeText(this, "Max kliener als Min.", Toast.LENGTH_SHORT).show();
            et_min.setText(String.valueOf(min));
            et_max.setText(String.valueOf(max));
        }
        int zahl = new Random().nextInt((max - min) + 1) + min;
        erg.setText(String.valueOf(zahl));
    }
}
