package com.example.inet_test;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class bier extends MainActivity{
    private Button bierplus;
    private Button bierminus;
    private Button refresh;
    private Button fertig;
    private TextView liste;
    Connection connect;
    String conresult="";
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bier);
        setTitle("Durst!");
        bierplus=(Button) findViewById(R.id.bierplus);
        bierminus=(Button) findViewById(R.id.bierminus);
        refresh=(Button) findViewById(R.id.refresh);
        fertig=(Button) findViewById(R.id.fertig);
        liste=(TextView) findViewById(R.id.liste);
        sqlhelper helper = new sqlhelper();
        connect = helper.connectionclass();
        bierplus.setOnClickListener(this);
        bierminus.setOnClickListener(this);
        refresh.setOnClickListener(this);
        fertig.setOnClickListener(this);
        neuladen();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bierplus:
                mehrbier();
                break;
            case R.id.bierminus:
                wenigerbier();
                break;
            case R.id.refresh:
                bier_local.init();
                neuladen();
                break;
            case R.id.fertig:
                bierbestellt();
                break;
            default:
                break;
        }
    }
    public void mehrbier(){
        try {
            Statement st = connect.createStatement();
            st.execute("update strichliste set bier_gr = bier_gr + 1;");
            bier_local.grbplus();
            neuladen();
        } catch (Exception ex) {
            liste.setText("Ein schwerer Fehler");
        }
    }
    public void bierbestellt(){
        try {
            Statement st = connect.createStatement();
            st.execute("update strichliste set bier_gr = 0;");
            st = connect.createStatement();
            st.execute("update strichliste set bier_kl = 0;");
            st = connect.createStatement();
            st.execute("update strichliste set weizen = 0;");
            st = connect.createStatement();
            st.execute("update strichliste set cola_gr = 0;");
            st = connect.createStatement();
            st.execute("update strichliste set cola_kl = 0;");
            st = connect.createStatement();
            st.execute("update strichliste set sonst = 0;");
            bier_local.init();
            neuladen();
        } catch (Exception ex) {
            liste.setText("Ein schwerer Fehler");
        }
    }
    public void wenigerbier(){
        while (bier_local.grbier>0){
            try {
                Statement st = connect.createStatement();
                st.execute("update strichliste set bier_gr = bier_gr - 1;");
                bier_local.grbminus();
            } catch (Exception ex) {
                liste.setText("Ein schwerer Fehler");
            }
        }
        neuladen();
    }
    public void neuladen(){
        try {
            String anzeige;
            StringBuilder builder =new StringBuilder();
            liste.setText("success helper");
            if (connect!=null){
                Statement st = connect.createStatement();
                liste.setText("create statement");
                ResultSet rs = st.executeQuery("select * from strichliste;");
                liste.setText("erfolgreiche quary");
                builder.append("Bestellt werden gerade:"+System.lineSeparator());
                while (rs.next()){
                    builder.append(rs.getString(1)+" große Bier"+System.lineSeparator());
                    builder.append(rs.getString(2)+" ehrenlose Bier"+System.lineSeparator());
                    builder.append(rs.getString(3)+" Weizen"+System.lineSeparator());
                    builder.append(rs.getString(4)+" großes Diabetis"+System.lineSeparator());
                    builder.append(rs.getString(5)+" kleine Cola"+System.lineSeparator());
                    builder.append(rs.getString(6)+" Sonstiges");
                }
            }
            else{
                builder.append("Hier ging nix, SQL-ERROR");
            }
            builder.append(System.lineSeparator()+System.lineSeparator()+"Follgende Bestellung in Warteschlange:"+System.lineSeparator());
            String deinebestellung = bier_local.get();
            builder.append(deinebestellung);
            anzeige = builder.toString();
            liste.setText(anzeige);
        }
        catch (Exception ex){
        liste.setText("Ein schwerer Fehler");
        }
    }
}
