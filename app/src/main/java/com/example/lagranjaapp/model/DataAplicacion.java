package com.example.lagranjaapp.model;

import java.util.ArrayList;
import java.util.List;

public class DataAplicacion {
    private String id;
    private String recurso;
    private String fecha;
    private String siembra;
    private ArrayList<Aplicacion> results;

    public DataAplicacion(String id, String recurso, String fecha, String siembra, ArrayList<Aplicacion> results) {
        this.id = id;
        this.recurso = recurso;
        this.fecha = fecha;
        this.siembra = siembra;
        this.results = results;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Aplicacion> getResults() {
        return results;
    }

    public void setResults(ArrayList<Aplicacion> results) {
        this.results = results;
    }

    public String getSiembra() {
        return siembra;
    }

    public void setSiembra(String siembra) {
        this.siembra = siembra;
    }
}
