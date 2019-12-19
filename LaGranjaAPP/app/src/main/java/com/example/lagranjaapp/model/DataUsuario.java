package com.example.lagranjaapp.model;

import java.util.List;

public class DataUsuario {

    private String id;
    private String name;
    private String stars;
    private List<DataActividades> activities;
    private List<Usuario> usuarios;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<DataActividades> getActivities() {
        return activities;
    }

    public void setActivities(List<DataActividades> activities) {
        this.activities = activities;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}