package com.example.lagranjaapp.model;

import java.util.List;

public class DataUsuario {

    private String id;
    private String name;
    private String estrellas;
    private String lastname;
    private List<DataActividades> activities;
    private List<Usuario> usuarios;
    private String terminado;
    private String correo;
    private String password;
   // private String equipo;

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullName(){
        return name+" "+lastname;
    }

    public String getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(String estrellas) {
        this.estrellas = estrellas;
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

    public String getTerminado() {
        return terminado;
    }

    public void setTerminado(String terminado) {
        this.terminado = terminado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}