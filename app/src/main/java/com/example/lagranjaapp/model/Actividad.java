package com.example.lagranjaapp.model;

import com.google.gson.annotations.SerializedName;

public class Actividad {
    @SerializedName("name")
    private String name;

    @SerializedName("fechaInicio")
    private String fechaInicio;

    @SerializedName("terminado")
    private int terminado;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("complejidad")
    private int complejidad;

    @SerializedName("usuario")
    private int usuario;

    @SerializedName("calificacion")
    private int calificacion;

    @SerializedName("equipo")
    private int equipo;

    @SerializedName("body")
    private String text;

    public Actividad(String name, String fechaInicio, int terminado, String descripcion, int complejidad, int usuario, int calificacion, int equipo) {
        this.name = name;
        this.fechaInicio = fechaInicio;
        this.terminado = terminado;
        this.descripcion = descripcion;
        this.complejidad = complejidad;
        this.usuario = usuario;
        this.calificacion = calificacion;
        this.equipo = equipo;
    }

    public String toString(){
        return "name: " + name + ", fechaInicio: " + fechaInicio + ", terminado: " + terminado +
                ", descripcion: " + descripcion + ", complejidad: " + complejidad + ", usuario: "
                + usuario + ", calificacion: " + calificacion + ", equipo: " + equipo;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getTerminado() {
        return terminado;
    }

    public void setTerminado(int terminado) {
        this.terminado = terminado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(int complejidad) {
        this.complejidad = complejidad;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}