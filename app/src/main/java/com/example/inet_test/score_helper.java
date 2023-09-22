package com.example.inet_test;

import java.util.ArrayList;

public class score_helper {
    public ArrayList<String> names;
    public ArrayList<ArrayList<String>> wurfe;
    public ArrayList<Boolean> auswahlstatus;

    public ArrayList<String> getNames(){
        return this.names;
    }
    public ArrayList<ArrayList<String>> getWurfe() {
        return this.wurfe;
    }

    public ArrayList<Boolean> getAuswahlstatus() {
        return auswahlstatus;
    }

    public void setAuswahlstatus(ArrayList<Boolean> auswahlstatus) {
        this.auswahlstatus = auswahlstatus;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public void setWurfe(ArrayList<ArrayList<String>> wurfe) {
        this.wurfe = wurfe;
    }
    public void add_name(String name){
        if (!this.names.contains(name)){
            this.names.add(name);
            this.wurfe.add(new ArrayList<>());
            this.auswahlstatus.add(false);
        }
    }
    public void remove_name(String name){
        int indey = this.names.indexOf(name);
        if (this.wurfe.get(indey).size()==0){
            this.names.remove(indey);
            this.wurfe.remove(indey);
            this.auswahlstatus.remove(indey);
        } else {
            this.wurfe.get(indey).remove(this.wurfe.get(indey).size()-1);
        }
    }
    public void wurf(String wurf){
        int i=0;
        while (i<this.names.size()){
            if(this.auswahlstatus.get(i)){
                this.wurfe.get(i).add(wurf);
                //this.auswahlstatus.set(i, false);
            }
            i++;
        }
    }
    public void init(){
        this.names=new ArrayList<String>();
        this.wurfe=new ArrayList<ArrayList<String>>();
        this.auswahlstatus=new ArrayList<Boolean>();
    }
    public void reset_wurfe(){
        this.wurfe=new ArrayList<ArrayList<String>>();
        for (String el: this.names){
            this.wurfe.add(new ArrayList<String>());
        }
    }
    public void select_someone(String name){
        for (String el: this.names) {
            this.unselect_someone(el);
        }
        this.auswahlstatus.set(this.names.indexOf(name), true);
    }
    public void unselect_someone(String name){
        this.auswahlstatus.set(this.names.indexOf(name), false);
    }
}
