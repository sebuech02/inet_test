package com.example.inet_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView result;
    private Button but;
    final ArrayList<spiel> Spiele= new ArrayList<spiel>();
    //final ArrayList<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but=(Button) findViewById(R.id.but1);
        but.setOnClickListener(this);
        result=(TextView) findViewById(R.id.text);
        getWebsite();
    }
    @Override
    public void onClick(View v){
        if (v.getId() == R.id.but1){
            startActivity(new Intent(MainActivity.this, zweiteSeite.class));
        }
    }
    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                spiel current = null;
                final StringBuilder builder=new StringBuilder();
            try {
                Document doc = Jsoup.connect("https://raw.githubusercontent.com/sebuech02/kegliege_xml/main/data.xml").get();
                Elements elemente = doc.select("spiel");
                for (Element element : elemente){
                    current = new spiel();
                    String temp_id=element.select("id").text();
                    String temp_name=element.select("name").text();
                    builder.append(element.select("id").text());
                    current.id=Integer.parseInt(temp_id);
                    current.name=temp_name;
                    current.kategorie=element.select("kategorie").text();
                    current.beschreibung=element.select("beschreibung").text();
                    current.atf=element.select("atf").text();
                    builder.append("\n");
                    builder.append(element.select("name").text());
                    builder.append("\n");
                    builder.append(element.select("kategorie").text());
                    builder.append("\n");
                    builder.append(element.select("atf").text());
                    builder.append("\n");
                    builder.append(element.select("beschreibung").text());
                    builder.append("\n");
                    builder.append("\n");
                    Spiele.add(current);
                }
                spiel_liste.set(Spiele);
            } catch(IOException e){
                builder.append("ERROR du PENNER");
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    result.setText(builder.toString());
                }
            });
            }
        }).start();
    }

}