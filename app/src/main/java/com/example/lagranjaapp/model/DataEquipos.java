package com.example.lagranjaapp.model;

import java.util.ArrayList;

public class DataEquipos {
    private String name;
    private String description;
    private ArrayList<Equipo> results;

    public DataEquipos(String name, String description, ArrayList<Equipo> results) {
        this.description = description;
        this.name = name;
        this.results = results;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Equipo> getResults() {
        return results;
    }

    public void setResults(ArrayList<Equipo> results) {
        this.results = results;
    }
}
