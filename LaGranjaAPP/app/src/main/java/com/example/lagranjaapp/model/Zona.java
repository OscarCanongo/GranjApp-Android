package com.example.lagranjaapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Zona {

    private String id;
    private String name;
    private ArrayList<DataSubzone> subzones;

    public Zona(String id,String name, ArrayList<DataSubzone> results) {
        this.id = id;
        this.name = name;
        this.subzones = results;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<DataSubzone> getSubzones() {
        return subzones;
    }

    public void setSubzones (ArrayList<DataSubzone> subzones) {
        this.subzones = subzones;
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}
