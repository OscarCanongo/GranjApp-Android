package com.example.lagranjaapp.model;

import java.util.ArrayList;
import java.util.List;

public class DataTipoRecurso {

    private String name;
    private String id;
    private ArrayList<Recurso> results;

    public DataTipoRecurso(String id, String name, ArrayList<Recurso> results) {
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

    public ArrayList<Recurso> getResults() {
        return results;
    }

    public void setResults(ArrayList<Recurso> results) {
        this.results = results;
    }
}
