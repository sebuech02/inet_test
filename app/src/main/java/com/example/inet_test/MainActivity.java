package com.example.inet_test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button but1;
    private ImageButton but2;
    private ImageButton but3;
    private ProgressBar pgb;
    final ArrayList<spiel> Spiele= new ArrayList<spiel>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Volle Pumpe");
        but1=(Button) findViewById(R.id.but1);
        but2=(ImageButton) findViewById(R.id.but2);
        but3=(ImageButton) findViewById(R.id.but3);
        pgb=(ProgressBar) findViewById(R.id.pb);
        pgb.setProgress(20,true);
        //pgb.setProgressDrawable(@drawable/carlrot);
        getWebsite();
        pgb.setProgress(66, true);
        getRangliste();
        pgb.setProgress(92,true);
        but1.setOnClickListener(this);
        but2.setOnClickListener(this);
        but3.setOnClickListener(this);
        pgb.setProgress(100,true);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.but2:
                startActivity(new Intent(MainActivity.this, settings.class));
                break;
            case R.id.but3:
                Uri spotifyAppUri = Uri.parse("https://open.spotify.com/user/fycli4oe1ts6i1fjcxy382940/playlist/2Wimw4FsAikla7fOMwguqh?si=W1FBLwS4SHGsWUWvPh48CA");
                Intent intent = new Intent(Intent.ACTION_VIEW, spotifyAppUri);
                startActivity(intent);
                break;
            case R.id.but1:
                startActivity(new Intent(MainActivity.this, zweiteSeite.class));
                break;
            default:
                break;
        }
    }
    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                spiel current = null;
            try {
                Document doc = Jsoup.connect("https://raw.githubusercontent.com/sebuech02/kegliege_xml/main/data.xml").get();
                Elements elemente = doc.select("spiel");
                for (Element element : elemente){
                    current = new spiel();
                    String temp_id=element.select("id").text();
                    String temp_name=element.select("name").text();
                    current.id=Integer.parseInt(temp_id);
                    current.name=temp_name;
                    current.kategorie=element.select("kategorie").text();
                    current.beschreibung=element.select("beschreibung").text();
                    current.atf=element.select("atf").text();
                    Spiele.add(current);
                }
                spiel_liste.set(Spiele);
            } catch(IOException e){
                Toast.makeText(getApplicationContext(),"ERROR, keine Spiele", Toast.LENGTH_LONG).show();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                }
            });
            }
        }).start();
    }
    private void getRangliste() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<stringinteger> rangliste = null;
                stringinteger current_rangliste = null;
                String pumpenk = null;
                String kegelk = null;
                ArrayList<stringstring> nhaus = null;
                stringstring current_nhaus = null;
                ArrayList<stringinteger> bier = null;
                stringinteger current_bier = null;
                ArrayList<String> misc = null;
                String current_misc=null;
                try {
                    Document doc = Jsoup.connect("https://raw.githubusercontent.com/sebuech02/kegliege_xml/main/data.xml").get();
                    Elements elemente = doc.select("rangliste");
                    for (Element element : elemente){
                        current_rangliste = new stringinteger();
                        String name=element.select("name").text();
                        String zahl_s=element.select("zahl").text();
                        int zahl=Integer.parseInt(zahl_s);
                        current_rangliste.text=name;
                        current_rangliste.zahl=zahl;
                        rangliste.add(current_rangliste);
                    }
                    pumpenk=doc.select("pumpenk").text();
                    kegelk=doc.select("kegelk").text();
                    elemente = doc.select("nhaus");
                    for (Element element : elemente){
                        current_nhaus = new stringstring();
                        String werfer=element.select("werfer").text();
                        String nummer=element.select("nummer").text();
                        current_nhaus.werfer=werfer;
                        current_nhaus.zahl=nummer;
                        nhaus.add(current_nhaus);
                    }
                    elemente = doc.select("bier");
                    for (Element element : elemente){
                        current_bier = new stringinteger();
                        String name=element.select("kegler").text();
                        String zahl_s=element.select("striche").text();
                        int zahl=Integer.parseInt(zahl_s);
                        current_bier.text=name;
                        current_bier.zahl=zahl;
                        bier.add(current_bier);
                    }
                    elemente = doc.select("misc");
                    for (Element element : elemente){
                        current_misc = new String();
                        String eintrag=element.select("eintrag").text();
                        current_misc=eintrag;
                        misc.add(current_misc);
                    }
                    ranglisten_daten.set_prangliste(rangliste);
                    ranglisten_daten.set_pumpenk(pumpenk);
                    ranglisten_daten.set_kegelk(kegelk);
                    ranglisten_daten.set_nhaus(nhaus);
                    ranglisten_daten.set_bier(bier);
                    ranglisten_daten.set_misc(misc);
                } catch(IOException e){
                    Toast.makeText(getApplicationContext(),"ERROR, keine Rangliste", Toast.LENGTH_LONG).show();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }
        }).start();
    }
}