package com.example.hockey.core.event.web;

import com.example.hockey.base.BaseRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class EventBaseReq extends BaseRequest {

    @NotNull
    private String name;

    @NotNull
    private String content;

    @NotEmpty
    private List<@Valid Id> players;


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

    public List<Id> getPlayers() {
        return players;
    }

    public void setPlayers(List<Id> players) {
        this.players = players;
    }
}
