package com.example.inet_test;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

public class anmeldung extends MainActivity{
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b10;
    private Button b11;
    private Button b12;
    private Button b13;
    private Button b14;
    private Button b15;
    private Button b16;
    private Button b17;
    private Button b18;
    private Button b19;
    private Button b20;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anmeldung);
        setTitle("Wer bist du?!");
        b1=(Button) findViewById(R.id.kegler1);
        b2=(Button) findViewById(R.id.kegler2);
        b3=(Button) findViewById(R.id.kegler3);
        b4=(Button) findViewById(R.id.kegler4);
        b5=(Button) findViewById(R.id.kegler5);
        b6=(Button) findViewById(R.id.kegler6);
        b7=(Button) findViewById(R.id.kegler7);
        b8=(Button) findViewById(R.id.kegler8);
        b9=(Button) findViewById(R.id.kegler9);
        b10=(Button) findViewById(R.id.kegler10);
        b11=(Button) findViewById(R.id.kegler11);
        b12=(Button) findViewById(R.id.kegler12);
        b13=(Button) findViewById(R.id.kegler13);
        b14=(Button) findViewById(R.id.kegler14);
        b15=(Button) findViewById(R.id.kegler15);
        b16=(Button) findViewById(R.id.kegler16);
        b17=(Button) findViewById(R.id.kegler17);
        b18=(Button) findViewById(R.id.kegler18);
        b19=(Button) findViewById(R.id.kegler19);
        b20=(Button) findViewById(R.id.kegler20);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);
        b13.setOnClickListener(this);
        b14.setOnClickListener(this);
        b15.setOnClickListener(this);
        b16.setOnClickListener(this);
        b17.setOnClickListener(this);
        b18.setOnClickListener(this);
        b19.setOnClickListener(this);
        b20.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kegler1:
                bier_local.setid(1);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler2:
                bier_local.setid(2);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler3:
                bier_local.setid(3);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler4:
                bier_local.setid(4);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler5:
                bier_local.setid(5);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler6:
                bier_local.setid(6);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler7:
                bier_local.setid(7);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler8:
                bier_local.setid(8);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler9:
                bier_local.setid(9);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler10:
                bier_local.setid(10);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler11:
                bier_local.setid(11);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler12:
                bier_local.setid(12);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler13:
                bier_local.setid(13);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler14:
                bier_local.setid(14);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler15:
                bier_local.setid(15);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler16:
                bier_local.setid(16);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler17:
                bier_local.setid(17);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler18:
                bier_local.setid(18);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler19:
                bier_local.setid(19);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
            case R.id.kegler20:
                bier_local.setid(20);
                startActivity(new Intent(anmeldung.this, bier.class));
                break;
        }
    }
}
