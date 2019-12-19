package com.example.lagranjaapp.model;

import com.google.gson.annotations.SerializedName;

public class Animal {


    @SerializedName("fechaNacimiento")
    private String fechaNacimiento;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("no_h")
    private int no_h;

    @SerializedName("no_m")
    private int no_m;

    @SerializedName("etapa")
    private int etapa;

    @SerializedName("equipo")
    private int equipo;

    @SerializedName("especie")
    private int especie;

    @SerializedName("salud")
    private int salud;


    public Animal(String name, String fechaNacimiento, int equipo, int no_h, int no_m, int salud, int etapa,  int especie) {
        this.fechaNacimiento = fechaNacimiento;
        this.name = name;
        this.no_h = no_h;
        this.no_m = no_m;
        this.etapa = etapa;
        this.equipo = equipo;
        this.especie = especie;
        this.salud = salud;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
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

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNo_h() {
        return no_h;
    }

    public void setNo_h(int no_h) {
        this.no_h = no_h;
    }

    public int getNo_m() {
        return no_m;
    }

    public void setNo_m(int no_m) {
        this.no_m = no_m;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public int getEquipo() {
        return equipo;
    }

    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }

    public int getEspecie() {
        return especie;
    }

    public void setEspecie(int especie) {
        this.especie = especie;
    }
}