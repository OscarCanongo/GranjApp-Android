package com.example.lagranjaapp.model;

import com.google.gson.annotations.SerializedName;

public class Aplicacion {
    @SerializedName("recurso")
    private int recurso;

    @SerializedName("fecha")
    private String fecha;

    @SerializedName("id")
    private String id;

    @SerializedName("siembra")
    private int siembra;

    public Aplicacion(String fecha, int siembra, int recurso) {
        this.siembra = siembra;
        this.fecha = fecha;
        this.recurso = recurso;
    }


}
