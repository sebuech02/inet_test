package com.example.inet_test;

import java.util.Comparator;

public class strintstrComparator implements Comparator<strintstr> {
    public int compare(strintstr left, strintstr right){
        return -left.zahl + right.zahl;
    }
}
