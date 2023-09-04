package com.example.inet_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sp_helfer extends zweiteSeite implements View.OnClickListener{
    private Button wuerf, zz, karte, schnapps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_helfer);
        setTitle("Hilfsmittel für Spiele");

        wuerf=findViewById(R.id.wuerfel);
        zz=findViewById(R.id.z_zahl);
        karte=findViewById(R.id.karte);
        schnapps=findViewById(R.id.schnapps);

        wuerf.setOnClickListener(this);
        zz.setOnClickListener(this);
        karte.setOnClickListener(this);
        schnapps.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.wuerfel:{
                startActivity(new Intent(sp_helfer.this, helfer_wuerfel.class));
                break;
            }
            case R.id.z_zahl:{
                startActivity(new Intent(sp_helfer.this, helfer_zufalsz.class));
                break;
            }
            case R.id.karte:{
                startActivity(new Intent(sp_helfer.this, helfer_karte.class));
                break;
            }
            case R.id.schnapps:{
                startActivity(new Intent(sp_helfer.this, helfer_schnapps.class));
                break;
            }
            default:{
                break;
            }

        }
    }

}
