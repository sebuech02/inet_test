package com.example.inet_test;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class util {
        public static final String EMAIL = "volle.pumpe@gmail.com";
        public static final String PASSWORD = "tyclkyakolnuvjpr";
        public static int versuche = 2;
        public static int levelid = 1;
        public static int heighscore = 0;
        public static String appid;

        public static ArrayList<String> spieler;
        public static ArrayList<Integer> pumpen;
        public static ArrayList<Integer> dummes;

        public static ArrayList<String> getSpieler(){
                return spieler;
        }
        public static ArrayList<Integer> getPumpen(){
                return pumpen;
        }
        public static ArrayList<Integer> getDummes(){
                return dummes;
        }
        public static void setSpieler(ArrayList<String> in){
                util.spieler= in;
        }
        public static void setPumpen(ArrayList<Integer> in){
                util.pumpen= in;
        }
        public static void setDummes(ArrayList<Integer> in){
                util.dummes= in;
        }
        public SharedPreferences sharedPref;

        public static void init_pumpen(){
                spieler= new ArrayList<String>();
                pumpen= new ArrayList<Integer>();
                dummes= new ArrayList<Integer>();
        }

        public static void add_spieler(String name){
                util.spieler.add(name);
                util.pumpen.add(1);
                util.dummes.add(0);
                sort_pumpen();
        }
        public static void add_spieler_dumm(String name){
                util.spieler.add(name);
                util.pumpen.add(0);
                util.dummes.add(0);
                sort_pumpen();
        }
        public static void add_pumpe(String name){
                int index=util.spieler.indexOf(name);
                util.pumpen.set(index, pumpen.get(index)+1);
                sort_pumpen();
        }
        public static void minus_pumpe(String name){
                int index=util.spieler.indexOf(name);
                util.pumpen.set(index, pumpen.get(index)-1);
                sort_pumpen();
        }

        public static void add_dummes(String name){
                int index=util.spieler.indexOf(name);
                util.dummes.set(index, dummes.get(index)+1);
                sort_pumpen();
        }

        public static void sort_pumpen(){
                concurrentSort(pumpen, pumpen, dummes, spieler);
        }

        public static <T extends Comparable<T>> void concurrentSort(
                final List<T> key, List<?>... lists){
                // Create a List of indices
                List<Integer> indices = new ArrayList<Integer>();
                for(int i = 0; i < key.size(); i++)
                        indices.add(i);

                // Sort the indices list based on the key
                Collections.sort(indices, new Comparator<Integer>(){
                        @Override public int compare(Integer i, Integer j) {
                                return (-1)*key.get(i).compareTo(key.get(j));
                        }
                });
                //Collections.reverse(indices);

                // Create a mapping that allows sorting of the List by N swaps.
                // Only swaps can be used since we do not know the type of the lists
                Map<Integer,Integer> swapMap = new HashMap<Integer, Integer>(indices.size());
                List<Integer> swapFrom = new ArrayList<Integer>(indices.size()),
                        swapTo   = new ArrayList<Integer>(indices.size());
                for(int i = 0; i < key.size(); i++){
                        int k = indices.get(i);
                        while(i != k && swapMap.containsKey(k))
                                k = swapMap.get(k);

                        swapFrom.add(i);
                        swapTo.add(k);
                        swapMap.put(i, k);
                }

                // use the swap order to sort each list by swapping elements
                for(List<?> list : lists)
                        for(int i = 0; i < list.size(); i++)
                                Collections.swap(list, swapFrom.get(i), swapTo.get(i));
        }

        public static void remove_player(String name){
                int i=0;
                Log.i("ende",getSpieler().toString());
                ArrayList<String> in1 = new ArrayList<String>();
                ArrayList<Integer> in2 = new ArrayList<Integer>();
                ArrayList<Integer> in3 = new ArrayList<Integer>();
                while (i<getSpieler().size()){
                        if (getSpieler().get(i)!=name) {
                                in1.add(getSpieler().get(i));
                                in2.add(getPumpen().get(i));
                                in3.add(getDummes().get(i));
                        }
                        i++;
                }
                setSpieler(in1);
                setPumpen(in2);
                setDummes(in3);
                Log.i("ende",getSpieler().toString());
        }
}
