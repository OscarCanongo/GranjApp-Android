package com.example.lagranjaapp.model;

import com.google.gson.annotations.SerializedName;

public class Animal {


    @SerializedName("fechaNacimiento")
    private String fechaNacimiento;

    @SerializedName("id")
    private String id;

    @SerializedName("no_h")
    private String no_h;

    public String getNo_h() {
        return no_h;
    }

    public void setMachos(String no_h) {
        this.no_h = no_h;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}