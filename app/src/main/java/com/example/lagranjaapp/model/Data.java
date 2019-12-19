package com.example.lagranjaapp.model;



import java.util.ArrayList;
import java.util.List;

public class Data {

    private String id;
    private String name;
    private String fechaNacimiento;
    private String no_h;
    private String no_m;
    private String salud;
    private String etapa;
    private ArrayList<Animal> results;

    public Data(String id, String fechaNacimiento, String name, String no_h, String no_m, String salud, String etapa, ArrayList<Animal> results) {
        this.id = id;
        this.name = name;
        this.fechaNacimiento = fechaNacimiento;
        this.no_h = no_h;
        this.no_h = no_m;
        this.salud = salud;
        this.etapa = etapa;
        this.results = results;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNo_m() {
        return no_m;
    }

    public void setNo_m(String no_m) {
        this.no_m = no_m;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNo_h() {
        return no_h;
    }

    public void setNo_h(String no_h) {
        this.no_h = no_h;
    }

    public String getfechaNacimiento() {
        return fechaNacimiento;
    }

    public void setfechaNacimiento(String nacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getId() {
        return id;
    }

    public void setId(String name) {
        this.id = id;
    }

    public List<Animal> getResults() {
        return results;
    }

    public void setResults(ArrayList<Animal> results) {
        this.results = results;
    }

    public String getEtapa() {
        return etapa;
    }

    public String getSalud() {
        return salud;
    }

    public void setSalud(String salud) {
        this.salud = salud;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }
}
