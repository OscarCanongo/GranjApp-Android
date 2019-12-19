package com.example.lagranjaapp.model;

import java.util.ArrayList;
import java.util.List;

public class DataZonas {
    private String name;
    private String id;
    private ArrayList<Zona> results;

    public DataZonas(String id,String name, ArrayList<Zona> results) {
        this.id = id;
        this.name = name;
        this.results = results;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Zona> getResults() {
        return results;
    }

    public void setResults(ArrayList<Zona> results) {
        this.results = results;
    }

}
