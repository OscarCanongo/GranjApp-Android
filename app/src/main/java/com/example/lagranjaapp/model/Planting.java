package com.example.lagranjaapp.model;

import com.google.gson.annotations.SerializedName;

public class    Planting {
    @SerializedName("salud")
    private int salud;

    @SerializedName("cultivo")
    private int cultivo;

    @SerializedName("subzona")
    private int subzona;

    public Planting(int salud, int cultivo, int subzona) {
        this.salud = salud;
        this.cultivo = cultivo;
        this.subzona = subzona;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getCultivo() {
        return cultivo;
    }

    public void setCultivo(int cultivo) {
        this.cultivo = cultivo;
    }

    public int getSubzona() {
        return subzona;
    }

    public void setSubzona(int subzona) {
        this.subzona = subzona;
    }
}
