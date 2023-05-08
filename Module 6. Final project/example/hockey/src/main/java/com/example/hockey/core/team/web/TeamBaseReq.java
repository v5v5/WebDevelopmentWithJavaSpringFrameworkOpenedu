package com.example.hockey.core.team.web;

import com.example.hockey.base.BaseRequest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TeamBaseReq extends BaseRequest {

    @NotEmpty
    private String name;

    @NotNull
    private int numPlayers;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }
}
