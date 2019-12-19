package com.example.lagranjaapp.model;

import com.google.gson.annotations.SerializedName;

public class Usuario {
    @SerializedName("name")
    private String name;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("correo")
    private String correo;

    @SerializedName("estrellas")
    private int estrellas;

    @SerializedName("admin")
    private int admin;

    @SerializedName("password")
    private String password;

    @SerializedName("id")
    private int id;

    public Usuario(String name, String lastname, String correo, int estrellas, int admin, String password) {
        this.name = name;
        this.lastname = lastname;
        this.correo = correo;
        this.estrellas = estrellas;
        this.admin = admin;
        this.password = password;
        //this.equipos = equipos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
