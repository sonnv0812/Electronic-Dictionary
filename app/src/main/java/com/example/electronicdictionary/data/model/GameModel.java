package com.example.electronicdictionary.data.model;

public class GameModel {
    private String name;
    private String gameUrl;

    public GameModel(String name, String gameUrl) {
        this.name = name;
        this.gameUrl = gameUrl;
    }

    public String getName() {
        return name;
    }

    public String getGameUrl() {
        return gameUrl;
    }
}
