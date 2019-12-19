package com.example.lagranjaapp.model;

import java.util.ArrayList;
import java.util.List;

public class DataActividades {
    private String name;
    private String usuario;
    private ArrayList<Actividad> results;

    public DataActividades(String name, ArrayList<Actividad> results) {
        this.name = name;
        this.results = results;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Actividad> getResults() {
        return results;
    }

    public void setResults(ArrayList<Actividad> results) {
        this.results = results;
    }
}
