package com.example.electronicdictionary.data.model;

import java.io.Serializable;
import java.util.List;

public class CategoryModel implements Serializable {
    private String name;
    private String color;
    private List<VocabModel> vocabs;

    public CategoryModel(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<VocabModel> getVocabs() {
        return vocabs;
    }

    public CategoryModel(String name, String color, List<VocabModel> vocabs) {
        this.name = name;
        this.color = color;
        this.vocabs = vocabs;
    }
}
