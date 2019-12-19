package com.example.lagranjaapp.model;

import java.util.ArrayList;
import java.util.List;

public class DataPlanting {
    private String cultivo;
    private String salud;
    private String id;
    private ArrayList<Planting> results;

    public DataPlanting(String id,String cultivo, String salud, ArrayList<Planting> results) {
        this.id = id;
        this.cultivo = cultivo;
        this.salud = salud;
        this.results = results;
    }

    public String getSalud() {
        return salud;
    }

    public void setSalud(String salud) {
        this.salud = salud;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String name) {
        this.cultivo = name;
    }

    public List<Planting> getResults() {
        return results;
    }

    public void setResults(ArrayList<Planting> results) {
        this.results = results;
    }
}
