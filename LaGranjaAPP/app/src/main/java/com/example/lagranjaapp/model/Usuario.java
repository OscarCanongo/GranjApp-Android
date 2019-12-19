package com.example.lagranjaapp.model;

import com.google.gson.annotations.SerializedName;

public class Usuario {
    @SerializedName("name")
    private String name;

    @SerializedName("estrellas")
    private String stars;

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
}
