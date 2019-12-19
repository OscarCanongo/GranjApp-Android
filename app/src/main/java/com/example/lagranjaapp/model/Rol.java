package com.example.lagranjaapp.model;

import com.google.gson.annotations.SerializedName;

public class Rol {
    @SerializedName("user")
    private int user;

    @SerializedName("team")
    private int team;

    public Rol(int user, int team) {
        this.user = user;
        this.team = team;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }
}
