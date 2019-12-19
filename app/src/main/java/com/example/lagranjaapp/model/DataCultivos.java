package com.example.lagranjaapp.model;

import java.util.ArrayList;
import java.util.List;

public class DataCultivos {
    private String name;
    private String id;
    private ArrayList<Cultivo> results;

    public DataCultivos(String id,String name, ArrayList<Cultivo> results) {
        this.id = id;
        this.name = name;
        this.results = results;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Cultivo> getResults() {
        return results;
    }

    public void setResults(ArrayList<Cultivo> results) {
        this.results = results;
    }
}
