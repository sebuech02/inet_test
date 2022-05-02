package com.example.inet_test;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class bestenliste extends MainActivity{
    private TextView text;
    private ArrayList<stringinteger> prangliste;
    private String pumpenk;
    private String kegelk;
    private ArrayList<stringstring> nhaus;
    private ArrayList<stringinteger> bier;
    private ArrayList<String> misc;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bestenliste);
        setTitle("Bestenliste");
        text=(TextView) findViewById(R.id.bestertext);
        prangliste=ranglisten_daten.get_prangliste();
        pumpenk=ranglisten_daten.get_pumpenk();
        kegelk=ranglisten_daten.get_kegelk();
        nhaus=ranglisten_daten.get_nhaus();
        bier=ranglisten_daten.get_bier();
        misc=ranglisten_daten.get_misc();
        StringBuilder builder = new StringBuilder();
        builder.append("Rangliste der Pumpen:");
        builder.append(System.lineSeparator());
        for (stringinteger hier:prangliste){
            builder.append(hier.text+"  "+hier.zahl+System.lineSeparator());
        }
        builder.append(System.lineSeparator());
        builder.append("Der Pumpenkönig ist "+pumpenk+" und der Kegelkönig "+kegelk);
        builder.append(System.lineSeparator());
        builder.append(System.lineSeparator());
        for (stringstring hier:nhaus){
            builder.append("Die niedrigste Hausnr war eine "+hier.zahl+" von "+hier.werfer);
            builder.append(System.lineSeparator());
        }
        builder.append(System.lineSeparator());
        builder.append("Die Bestelliste is:");
        builder.append(System.lineSeparator());
        for (stringinteger hier:bier){
            builder.append(hier.text+" mit "+hier.zahl+" Bier");
            builder.append(System.lineSeparator());
        }
        builder.append(System.lineSeparator());
        builder.append("zu den Nachrrichten:");
        builder.append(System.lineSeparator());
        for (String hier:misc){
            builder.append(hier);
            builder.append(System.lineSeparator());
        }
        text.setText(builder.toString());
    }
}
