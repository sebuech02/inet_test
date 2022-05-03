package com.example.inet_test;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.sql.Connection;
import java.sql.Statement;

public class auswahl extends MainActivity {
    private Button getr1;
    private Button getr2;
    private Button getr3;
    private Button getr4;
    private Button getr5;
    private Button getr6;
    Connection connect;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auswahl);
        setTitle("Wonach dürsted es?");
        getr1=(Button) findViewById(R.id.getr1);
        getr2=(Button) findViewById(R.id.getr2);
        getr3=(Button) findViewById(R.id.getr3);
        getr4=(Button) findViewById(R.id.getr4);
        getr5=(Button) findViewById(R.id.getr5);
        getr6=(Button) findViewById(R.id.getr6);
        getr1.setOnClickListener(this);
        getr2.setOnClickListener(this);
        getr3.setOnClickListener(this);
        getr4.setOnClickListener(this);
        getr5.setOnClickListener(this);
        getr6.setOnClickListener(this);
        sqlhelper helper = new sqlhelper();
        connect = helper.connectionclass();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getr1:
                try {
                    Statement st = connect.createStatement();
                    st.execute("update strichliste set bier_gr = bier_gr + 1 where id = "+bier_local.getid()+";");
                    Toast.makeText(getApplicationContext(),"Striche nicht vergessen!",Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Fehler, nix bestellt",Toast.LENGTH_LONG).show();
                }
                finish();
                break;
            case R.id.getr2:
                try {
                    Statement st = connect.createStatement();
                    st.execute("update strichliste set bier_kl = bier_kl + 1 where id = "+bier_local.getid()+";");
                    Toast.makeText(getApplicationContext(),"Striche nicht vergessen!",Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Fehler, nix bestellt",Toast.LENGTH_LONG).show();
                }
                finish();
                break;
            case R.id.getr3:
                try {
                    Statement st = connect.createStatement();
                    st.execute("update strichliste set weizen = weizen + 1 where id = "+bier_local.getid()+";");
                    Toast.makeText(getApplicationContext(),"Striche nicht vergessen!",Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Fehler, nix bestellt",Toast.LENGTH_LONG).show();
                }
                finish();
                break;
            case R.id.getr4:
                try {
                    Statement st = connect.createStatement();
                    st.execute("update strichliste set cola_gr = cola_gr + 1 where id = "+bier_local.getid()+";");
                    Toast.makeText(getApplicationContext(),"Striche nicht vergessen!",Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Fehler, nix bestellt",Toast.LENGTH_LONG).show();
                }
                finish();
                break;
            case R.id.getr5:
                try {
                    Statement st = connect.createStatement();
                    st.execute("update strichliste set cola_kl = cola_kl + 1 where id = "+bier_local.getid()+";");
                    Toast.makeText(getApplicationContext(),"Striche nicht vergessen!",Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Fehler, nix bestellt",Toast.LENGTH_LONG).show();
                }
                finish();
                break;
            case R.id.getr6:
                try {
                    Statement st = connect.createStatement();
                    st.execute("update strichliste set sonst = sonst + 1 where id = "+bier_local.getid()+";");
                    Toast.makeText(getApplicationContext(),"Striche nicht vergessen!",Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Fehler, nix bestellt",Toast.LENGTH_LONG).show();
                }
                finish();
                break;
            default:
                break;
        }
    }
}
