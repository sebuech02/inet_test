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
import java.lang.annotation.ElementType;
import java.sql.Connection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button but1;
    private ImageButton but2;
    private ImageButton but3;
    private ProgressBar pgb;
    final ArrayList<spiel> Spiele= new ArrayList<spiel>();
    final ArrayList<stringinteger> rangliste = new ArrayList<stringinteger>();
    final ArrayList<stringstring> nhaus = new ArrayList<stringstring>();
    final ArrayList<stringinteger> bier = new ArrayList<stringinteger>();
    final ArrayList<String> misc = new ArrayList<String>();
    final ArrayList<stringinteger> score = new ArrayList<stringinteger>();
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
                stringinteger current_rangliste = null;
                String pumpenk = null;
                String kegelk = null;
                stringstring current_nhaus = null;
                stringinteger current_bier = null;
                String current_misc = null;
                stringinteger current_scores = null;
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
                Document doc2 = Jsoup.connect("https://raw.githubusercontent.com/sebuech02/kegeliege_rangliste/main/rangliste.xml").get();
                Elements elemente2 = doc2.select("rangliste");
                for (Element element : elemente2){
                    current_rangliste = new stringinteger();
                    String name=element.select("name").text();
                    String zahl_s=element.select("zahl").text();
                    int zahl=Integer.parseInt(zahl_s);
                    current_rangliste.text=name;
                    current_rangliste.zahl=zahl;
                    rangliste.add(current_rangliste);
                }
                pumpenk=doc2.select("pumpenk").text();
                kegelk=doc2.select("kegelk").text();
                Elements elemente3 = doc2.select("nhaus");
                for (Element element : elemente3){
                    current_nhaus = new stringstring();
                    String werfer=element.select("werfer").text();
                    String nummer=element.select("nummer").text();
                    current_nhaus.werfer=werfer;
                    current_nhaus.zahl=nummer;
                    nhaus.add(current_nhaus);
                }
                Elements elemente4 = doc2.select("bier");
                for (Element element : elemente4){
                    current_bier = new stringinteger();
                    String name=element.select("kegler").text();
                    String zahl_s=element.select("striche").text();
                    int zahl=Integer.parseInt(zahl_s);
                    current_bier.text=name;
                    current_bier.zahl=zahl;
                    bier.add(current_bier);
                }
                Elements elemente5 = doc2.select("misc");
                for (Element element : elemente5){
                    current_misc = new String();
                    String eintrag=element.select("eintrag").text();
                    current_misc=eintrag;
                    misc.add(current_misc);
                }
                Elements elemente6 = doc2.select("score");
                for (Element element : elemente6){
                    current_scores = new stringinteger();
                    String name2=element.select("name").text();
                    String punkte=element.select("punkte").text();
                    int punkt=Integer.parseInt(punkte);
                    current_scores.text=name2;
                    current_scores.zahl=punkt;
                    score.add(current_scores);
                }
                ranglisten_daten.set_prangliste(rangliste);
                ranglisten_daten.set_pumpenk(pumpenk);
                ranglisten_daten.set_kegelk(kegelk);
                ranglisten_daten.set_nhaus(nhaus);
                ranglisten_daten.set_bier(bier);
                ranglisten_daten.set_misc(misc);
                ranglisten_daten.set_scores(score);
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
}