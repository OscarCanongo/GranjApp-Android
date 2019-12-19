package com.example.lagranjaapp.model;

import java.util.ArrayList;
import java.util.List;

public class DataActividades {
    private String id;
    private String name;
    private String usuario;
    private String fechaInicio, descripcion;
    private ArrayList<Actividad> results;
    private String terminado;
    private String complejidad;

    public DataActividades(String name, ArrayList<Actividad> results, String terminado) {
        this.name = name;
        this.results = results;
        this.terminado = terminado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerminado() {
        return terminado;
    }

    public void setTerminado(String terminado) {
        this.terminado = terminado;
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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComplejidad() {
        return complejidad;
    }

}