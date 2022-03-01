package com.example.inet_test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    public boolean einmal;
    final ArrayList<spiel> Spiele= new ArrayList<spiel>();
    //final ArrayList<String> names = new ArrayList<String>();

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
        pgb.setProgress(80,true);
        but1.setOnClickListener(this);
        but2.setOnClickListener(this);
        but3.setOnClickListener(this);
        pgb.setProgress(100,true);
        einmal =true;
        if (einmal) {
            einmal=false;
            //Toast.makeText(getApplicationContext(), "Laden erfolgreich!", Toast.LENGTH_LONG).show();
        }
        //pgb.setProgressDrawable("@drawable/bild1");
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
                Toast.makeText(getApplicationContext(),"ERROR", Toast.LENGTH_LONG).show();
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