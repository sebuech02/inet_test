package com.example.inet_test;

import java.util.Comparator;

public class spiel_id_comp implements Comparator<spiel> {
    public int compare(spiel eins, spiel zwei){
        return eins.id-zwei.id;
    }
}
