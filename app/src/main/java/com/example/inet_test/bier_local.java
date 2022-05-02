package com.example.inet_test;

public class bier_local {
    public static int grbier;
    public static int klbier;
    public static int weizen;
    public static int grcola;
    public static int klcola;
    public static int sonst;

    public static void init(){
        grbier=0;
        klbier=0;
        weizen=0;
        grcola=0;
        klcola=0;
        sonst=0;
    }
    public static void grbplus(){
        grbier=grbier + 1;
    }
    public static void grbminus(){
        if (grbier>0) {
            grbier = grbier - 1;
        }
    }
    public static void klbplus(){
        klbier=klbier + 1;
    }
    public static void klbminus(){
        if (klbier>0) {
            klbier = klbier - 1;
        }
    }
    public static void wplus(){
        weizen = weizen + 1;
    }
    public static void wminus(){
        if (weizen>0) {
            weizen = weizen - 1;
        }
    }
    public static void grcplus(){
        grcola = grcola + 1;
    }
    public static void grcminus(){
        if (grcola>0) {
            grcola = grcola - 1;
        }
    }
    public static void klcplus(){
        klcola = klcola + 1;
    }
    public static void klcminus(){
        if (klcola>0) {
            klcola = klcola - 1;
        }
    }
    public static void splus(){
        sonst = sonst + 1;
    }
    public static void sminus(){
        if (sonst>0) {
            sonst = sonst - 1;
        }
    }
    public static String get(){
        return( grbier+" Große Bier"+System.lineSeparator()+
                klbier+" kleine Bier"+System.lineSeparator()+
                weizen+" Weizen"+System.lineSeparator()+
                grcola+" große Diabetis"+System.lineSeparator()+
                klcola+" kleine Kokaine"+System.lineSeparator()+
                sonst+" irgendwas"+System.lineSeparator());
    }
}
