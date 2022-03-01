package com.example.inet_test;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class k_alles extends MainActivity implements View.OnClickListener {
    LinearLayout parent;
    Button b1;
    Button sort;
    public String[] btn_name;
    public boolean bb;
    private ArrayList<spiel> ubergabe;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategorien);
        parent=(LinearLayout)findViewById(R.id.ll_parentlayout);
        setTitle("Wirklich alle Spiele");
        ubergabe=spiel_liste.get();
        int i=ubergabe.size();
        String btn_name[] = new String[i];
        int j=0;
        for (spiel current : ubergabe){
            btn_name[j]=current.name;
            b1=new Button(k_alles.this);
            b1.setId(j+1);
            b1.setText(btn_name[j]);
            b1.setTag(Integer.toString(current.id));
            b1.setBackground(this.getResources().getDrawable(R.color.black));
            b1.setTextColor(getResources().getColor(R.color.white));
            parent.addView(b1);
            b1.setOnClickListener(this);
            j=j+1;
        }
    }
    @Override
    public void onClick(View v) {
        String str = v.getTag().toString();
        if(!(str.equals("null"))) {
            Intent actvar = new Intent(k_alles.this, anzeige.class);
            actvar.putExtra("id", str);
            startActivity(actvar);
        }
    }
}
