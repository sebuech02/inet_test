package com.example.inet_test;

import java.util.ArrayList;
import java.util.Collections;

public class spiel_liste {
    public static ArrayList<spiel> liste;
    public static void set(ArrayList<spiel> eingabe){
        liste=eingabe;
    }
    public static ArrayList<spiel> get(){
        return liste;
    }

    public static void sort_name(){
        Collections.sort(liste, new spiel_name_comp());
        System.out.println("Ich sortiere nach name");
    }
    public static void sort_id(){
        Collections.sort(liste, new spiel_id_comp());
        System.out.println("Ich sortiere nach IDDDDDDD");
    }
}
