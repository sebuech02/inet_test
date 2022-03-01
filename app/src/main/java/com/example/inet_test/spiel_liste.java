package com.example.inet_test;

import java.util.ArrayList;

public class spiel_liste {
    public static ArrayList<spiel> liste;
    public static void set(ArrayList<spiel> eingabe){
        liste=eingabe;
    }
    public static ArrayList<spiel> get(){
        return liste;
    }
}
