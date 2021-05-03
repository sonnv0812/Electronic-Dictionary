package com.example.electronicdictionary.data.model;

import java.io.Serializable;

public class VocabModel implements Serializable {
    private String id;
    private String word;
    private String mean;
    private String pronunciation;
    private String image;
    private String description;
    private int status;
    private int groupId;

    public VocabModel() {
        
    }

    public VocabModel(String id, String word, String mean, String pronunciation, String image, String description, int status, int groupId) {
        this.id = id;
        this.word = word;
        this.mean = mean;
        this.pronunciation = pronunciation;
        this.image = image;
        this.description = description;
        this.status = status;
        this.groupId = groupId;
    }

    public String getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getMean() {
        return mean;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    public int getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "VocabModel{" +
                "word='" + word + '\'' +
                '}';
    }
}
