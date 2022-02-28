package com.example.inet_test;
import org.jsoup.*;
import org.w3c.dom.Document;

import java.io.IOException;
import java.net.URL;

public class test_liste {
    public static String inhalt;
    public void setInhalt() throws IOException {
        this.inhalt=init();
    }
    public static String getInhalt(){
        return test_liste.inhalt;
    }
    public static String init() throws IOException {
        String url="https://raw.githubusercontent.com/sebuech02/kegliege_xml/main/data.xml";
        org.jsoup.nodes.Document doc=Jsoup.connect(url).get();
        return doc.text();
    }
}
