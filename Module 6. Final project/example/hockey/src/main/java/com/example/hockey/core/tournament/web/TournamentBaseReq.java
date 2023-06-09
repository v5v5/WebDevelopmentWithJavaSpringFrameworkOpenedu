package com.example.hockey.core.tournament.web;

import com.example.hockey.base.BaseRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

public class TournamentBaseReq extends BaseRequest implements Serializable {

    @NotEmpty
    private String name;

    @NotEmpty
    private String country;

    private List<@Valid Id> teams;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Id> getTeams() {
        return teams;
    }

    public void setTeams(List<Id> teams) {
        this.teams = teams;
    }
}
