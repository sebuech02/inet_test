package com.example.inet_test;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.w3c.dom.Text;

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
    private TextView liste2;
    private Button bezahlt;
    private Button abendvorbei;
    Connection connect;
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
        liste2=(TextView) findViewById(R.id.liste2);
        bezahlt=(Button) findViewById(R.id.bezahlt);
        abendvorbei=(Button) findViewById(R.id.abendvorbei);
        liste.setText("sql helper ausführen");
        sqlhelper helper = new sqlhelper();
        connect = helper.connectionclass();
        bierplus.setOnClickListener(this);
        bierminus.setOnClickListener(this);
        refresh.setOnClickListener(this);
        fertig.setOnClickListener(this);
        bezahlt.setOnClickListener(this);
        if (bier_local.getid()==20){
            abendvorbei.setOnClickListener(this);
            abendvorbei.setText("Alles hat bezahlt (löscht alle Geld und liter Statistiken)");
        }
        liste.setText("Lade daten");
        neuladen();
        bezahlt.setText(finde_name(bier_local.getid())+" hat bezahlt");
        if (bier_local.getid()==20){
            Toast.makeText(getApplicationContext(),"Kasse spendiert!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Guten Durst, "+finde_name(bier_local.getid())+"!",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        neuladen();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bierplus:
                startActivity(new Intent(bier.this, auswahl.class));
                break;
            case R.id.bierminus:
                wenigerbier();
                break;
            case R.id.refresh:
                neuladen();
                Toast.makeText(getApplicationContext(),"refresh",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fertig:
                bierbestellt();
                break;
            case R.id.bezahlt:
                bierbezahlt();
                break;
            case R.id.abendvorbei:
                bierbezahlt_alle();
                break;
            default:
                break;
        }
    }
    public void bierbestellt(){
        try {
            Statement st = connect.createStatement();
            st.execute("update strichliste set geld = geld + (bier_gr + cola_gr) * "+bier_local.ggrpreis+" + (bier_kl + cola_kl) * "+bier_local.gklpreis+" + weizen * "+bier_local.wpreis+";");
            st = connect.createStatement();
            st.execute("update strichliste set liter = liter + (bier_gr + cola_gr) * 0.4 + (bier_kl + cola_kl) * +0.2 + weizen * 0.5;");
            st = connect.createStatement();
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
            Toast.makeText(getApplicationContext(),"Danke Besteller!",Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            liste.setText("Ein schwerer Fehler (beim zurücksetzten)"+System.lineSeparator()+"App komplett schließen und neuladen bidde!");
        }
    }
    public void bierbezahlt(){
        try {
            Statement st = connect.createStatement();
            st.execute("update strichliste set geld = 0 where id = "+bier_local.getid()+";");
            st = connect.createStatement();
            st.execute("update strichliste set liter = 0 where id = "+bier_local.getid()+";");
        } catch (Exception ex) {
            liste.setText("Ein schwerer Fehler, konnte nicht abrechnen"+System.lineSeparator()+"App schließen und neuladen bidde!");
        }
        neuladen();
    }
    public void bierbezahlt_alle(){
        try {
            Statement st = connect.createStatement();
            st.execute("update strichliste set geld = 0;");
            st = connect.createStatement();
            st.execute("update strichliste set liter = 0 ;");
        } catch (Exception ex) {
            liste.setText("Ein schwerer Fehler, konnte nicht abrechnen"+System.lineSeparator()+"App schließen und neuladen bidde!");
        }
        Toast.makeText(getApplicationContext(),"Kegeln leider Vorbei",Toast.LENGTH_LONG).show();
        neuladen();
    }
    public void wenigerbier(){
        try {
            Statement st = connect.createStatement();
            st.execute("update strichliste set bier_gr = 0 where id = "+bier_local.getid()+";");
            st = connect.createStatement();
            st.execute("update strichliste set bier_kl = 0 where id = "+bier_local.getid()+";");
            st = connect.createStatement();
            st.execute("update strichliste set weizen = 0 where id = "+bier_local.getid()+";");
            st = connect.createStatement();
            st.execute("update strichliste set cola_gr = 0 where id = "+bier_local.getid()+";");
            st = connect.createStatement();
            st.execute("update strichliste set cola_kl = 0 where id = "+bier_local.getid()+";");
            st = connect.createStatement();
            st.execute("update strichliste set sonst = 0 where id = "+bier_local.getid()+";");
        } catch (Exception ex) {
            liste.setText("Ein schwerer Fehler, konnte nicht stornieren"+System.lineSeparator()+"App schließen und neuladen bidde!");
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
                ResultSet rs1 = st.executeQuery("select sum(bier_gr) from strichliste;");
                st = connect.createStatement();
                ResultSet rs2 = st.executeQuery("select sum(bier_kl) from strichliste;");
                st = connect.createStatement();
                ResultSet rs3 = st.executeQuery("select sum(weizen) from strichliste;");
                st = connect.createStatement();
                ResultSet rs4 = st.executeQuery("select sum(cola_gr) from strichliste;");
                st = connect.createStatement();
                ResultSet rs5 = st.executeQuery("select sum(cola_kl) from strichliste;");
                st = connect.createStatement();
                ResultSet rs6 = st.executeQuery("select sum(sonst) from strichliste;");
                liste.setText("erfolgreiche quary");
                builder.append("Bestellt werden gerade:"+System.lineSeparator());
                while (rs1.next()){
                    builder.append(rs1.getString(1)+" große Bier"+System.lineSeparator());
                }
                while (rs2.next()){
                    builder.append(rs2.getString(1)+" kleine Bier"+System.lineSeparator());
                }
                while (rs3.next()){
                    builder.append(rs3.getString(1)+" Weizen"+System.lineSeparator());
                }
                while (rs4.next()){
                    builder.append(rs4.getString(1)+" große Diabetis"+System.lineSeparator());
                }
                while (rs5.next()){
                    builder.append(rs5.getString(1)+" kleine Cola"+System.lineSeparator());
                }
                while (rs6.next()){
                    builder.append(rs6.getString(1)+" Sonstieges"+System.lineSeparator());
                }
            }
            else{
                builder.append("Hier ging nix, SQL-ERROR, quary_error"+System.lineSeparator()+"App komplett schließen und neuladen bidde!");
            }
            builder.append(System.lineSeparator()+System.lineSeparator()+"Deine Bestellung, "+finde_name(bier_local.getid())+":"+System.lineSeparator());
            Statement st = connect.createStatement();
            ResultSet temp = st.executeQuery("select * from strichliste where id = "+bier_local.getid()+";");
            StringBuilder builder2 = new StringBuilder();
            while (temp.next()) {
                builder.append(temp.getInt(1) + " große Bier" + System.lineSeparator());
                builder.append(temp.getInt(2) + " kleine Bier" + System.lineSeparator());
                builder.append(temp.getInt(3) + " Weizen" + System.lineSeparator());
                builder.append(temp.getInt(4) + " große Cola" + System.lineSeparator());
                builder.append(temp.getInt(5) + " kleine Kokain" + System.lineSeparator());
                builder.append(temp.getInt(6) + " Sonstiges" + System.lineSeparator());
                builder2.append("Heute ballerst du schon "+temp.getFloat(8)+"l zu einem Preis von "+temp.getFloat(9)+"€ (ohne Gewähr)");
            }
            anzeige = builder.toString();
            String anzeige2 = builder2.toString();
            liste.setText(anzeige);
            liste2.setText(anzeige2);
        }
        catch (Exception ex){
        liste.setText("Ein schwerer Fehler, keine Verbindung zur Datenbank");
        }
    }
    public String finde_name(int i){
        String result="";
        switch (i){
            case 1:
                result="Nummer 1";
                break;
            case 2:
                result="Anabol";
                break;
            case 3:
                result="Wiecklaß";
                break;
            case 4:
                result="Mexiko";
                break;
            case 5:
                result="Langer";
                break;
            case 6:
                result="Redabol";
                break;
            case 7:
                result="Errich";
                break;
            case 8:
                result="Zwerg";
                break;
            case 9:
                result="Spakko";
                break;
            case 10:
                result="Zugpferd";
                break;
            case 11:
                result="Tolky";
                break;
            case 12:
                result="Driver";
                break;
            case 13:
                result="Blome";
                break;
            case 14:
                result="Jeremias";
                break;
            case 15:
                result="Moritz";
                break;
            case 16:
                result="Pasckaal";
                break;
            case 17:
                result="Timm";
                break;
            case 18:
                result="Lion";
                break;
            case 19:
                result="Gäste";
                break;
            case 20:
                result="Kasse";
                break;
            default:
                result="FEHLER";
                break;
        }
        return result;
    }
}
