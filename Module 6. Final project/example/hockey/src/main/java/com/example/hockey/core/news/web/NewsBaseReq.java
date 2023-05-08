package com.example.hockey.core.news.web;

import com.example.hockey.base.BaseRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class NewsBaseReq extends BaseRequest {

    @NotNull
    private String name;

    @NotNull
    private String content;

    @NotEmpty
    private List<@Valid Id> teams;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Id> getTeams() {
        return teams;
    }

    public void setTeams(List<Id> teams) {
        this.teams = teams;
    }
}
