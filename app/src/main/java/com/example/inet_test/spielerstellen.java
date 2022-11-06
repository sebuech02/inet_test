package com.example.inet_test;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.RequiresApi;

public class spielerstellen extends MainActivity implements View.OnClickListener {
    private Button submit;
    private EditText name;
    private EditText beschreibung;
    private String input;
    private String input_name;
    private String input_kat;
    private String kat;
    private Spinner dropdown;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spielerstellen);
        setTitle("Neues Spiel erstellen");
        name=(EditText) findViewById(R.id.name);
        beschreibung=(EditText) findViewById(R.id.beschreibung);
        submit=(Button) findViewById(R.id.submit);
        dropdown=(Spinner) findViewById(R.id.spinner);

        String[] items = new String[]{"Jeder für sich oder alle gegen alle","2 Teams oder auch 3 große Teams","2er Teams, bzw recht kleine Teams","Bonusspiele (Trinkspiele)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setBackgroundColor(000000);
        dropdown.setAdapter(adapter);
        submit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.submit:
                String eingabe1 = name.getText().toString();
                String eingabe2 = beschreibung.getText().toString();
                if (eingabe1.matches("")){
                    Toast.makeText(this, "Der Name fehlt", Toast.LENGTH_LONG).show();
                    break;
                }
                else{
                    if (eingabe2.matches("")){
                        Toast.makeText(this, "Die Beschreibung fehlt", Toast.LENGTH_LONG).show();
                        break;
                    }
                    else{
                        spielhochladen();
                        name.getText().clear();
                        beschreibung.getText().clear();
                        break;
                    }
                }
            default:
                break;
        }
    }

    private void spielhochladen() {
        input_name=name.getText().toString();
        input=beschreibung.getText().toString();
        input_kat=dropdown.getSelectedItem().toString();
        switch (input_kat){
            case "Jeder für sich oder alle gegen alle":
                kat="ffa";
                break;
            case "2 Teams oder auch 3 große Teams":
                kat="tdm";
                break;
            case "2er Teams, bzw recht kleine Teams":
                kat="duoq";
                break;
            case "Bonusspiele (Trinkspiele)":
                kat="bonus";
                break;
            default:
                kat=input_kat;
        }

        StringBuilder build = new StringBuilder();
        build.append("Name: ");
        build.append(input_name);
        build.append(System.lineSeparator());
        build.append("Kategorie: ");
        build.append(kat);
        build.append(System.lineSeparator());
        build.append(System.lineSeparator());
        build.append(input);
        build.append(System.lineSeparator());
        build.append(System.lineSeparator());
        build.append(System.lineSeparator());
        build.append("XML-Version:");
        build.append(System.lineSeparator());
        build.append(System.lineSeparator());
        build.append("<spiel>"+System.lineSeparator() +
                "        <id>??</id>"+System.lineSeparator() +
                "        <name>"+input_name+"</name>"+System.lineSeparator() +
                "        <kategorie>"+kat+"</kategorie>"+System.lineSeparator() +
                "        <beschreibung>"+input+"</beschreibung>"+System.lineSeparator() +
                "        <atf>false</atf>"+System.lineSeparator() +
                "    </spiel>");
        build.append(System.lineSeparator());
        build.append(System.lineSeparator());
        build.append("Von Token:");
        build.append(System.lineSeparator());
        build.append(util.appid);
        String message = build.toString();
        String mail = "sebastian.buechler@tutanota.de";
        javamailapi  mailapi = new javamailapi(this, mail, "Neues Spiel für die Volle Pumpe", message);
        mailapi.execute();
    }
}
