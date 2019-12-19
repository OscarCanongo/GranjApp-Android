package com.example.lagranjaapp.model;

import java.util.ArrayList;
import java.util.List;

public class DataSubzone {

    private String name;
    private String id;
    private ArrayList<Subzone> results;

    public DataSubzone(String id, String name, ArrayList<Subzone> results) {
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

    public ArrayList<Subzone> getResults() {
        return results;
    }

    public void setResults(ArrayList<Subzone> results) {
        this.results = results;
    }
}
