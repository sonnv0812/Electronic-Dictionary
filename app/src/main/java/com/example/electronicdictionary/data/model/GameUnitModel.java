package com.example.electronicdictionary.data.model;

import java.util.List;

public class GameUnitModel {
    private String name;
    private String color;
    private List<GameModel> games;

    public GameUnitModel(String name, String color, List<GameModel> games) {
        this.name = name;
        this.color = color;
        this.games = games;
    }

    public String getName() {
        return name;
    }

    public List<GameModel> getGames() {
        return games;
    }

    public String getColor() {
        return color;
    }
}
