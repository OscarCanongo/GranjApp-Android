package com.example.lagranjaapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Subzone {

    private String id;
    private String name;
    private ArrayList<DataPlanting> plantings;

    public Subzone(String id,String name, ArrayList<DataPlanting> plantings) {
        this.id = id;
        this.name = name;
        this.plantings = plantings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<DataPlanting> getPlantings() {
        return plantings;
    }

    public void setPlantings(ArrayList<DataPlanting> plantings) {
        this.plantings = plantings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
