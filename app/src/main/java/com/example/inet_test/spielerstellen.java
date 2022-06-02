package com.example.inet_test;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;

public class spielerstellen extends MainActivity implements View.OnClickListener {
    private Button submit;
    private EditText name;
    private EditText beschreibung;
    private String input;
    private String input_name;
    private String input_kat;
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
        dropdown.setAdapter(adapter);
        submit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.submit:
                spielhochladen();
                break;
            default:
                break;
        }
    }

    private void spielhochladen() {
        input_name=name.getText().toString();
        input=beschreibung.getText().toString();
        input_kat=dropdown.getSelectedItem().toString();
    }
}
