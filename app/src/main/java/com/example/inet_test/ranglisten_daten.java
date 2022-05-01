package com.example.inet_test;

import java.util.ArrayList;

public class ranglisten_daten {
    public static ArrayList<stringinteger> prangliste;
    public static String pumpenk;
    public static String kegelk;
    public static ArrayList<stringstring> nhaus;
    public static ArrayList<stringinteger> bier;
    public static ArrayList<String> misc;

    public static void set_prangliste(ArrayList<stringinteger> a){
        prangliste=a;
    }
    public static void set_pumpenk(String b){
        pumpenk=b;
    }
    public static void set_kegelk(String c){
        kegelk=c;
    }
    public static void set_nhaus(ArrayList<stringstring> d){
        nhaus=d;
    }
    public static void set_bier(ArrayList<stringinteger> e){
        bier=e;
    }
    public static void set_misc(ArrayList<String> f){
        misc=f;
    }
    public static ArrayList<stringinteger> get_prangliste(){
        return prangliste;
    }
    public static String get_pumpenk(){
        return pumpenk;
    }
    public static String get_kegelk(){
        return kegelk;
    }
    public static ArrayList<stringstring> get_nhaus(){
        return nhaus;
    }
    public static ArrayList<stringinteger> get_bier(){
        return bier;
    }
    public static ArrayList<String> get_misc(){
        return misc;
    }
}
