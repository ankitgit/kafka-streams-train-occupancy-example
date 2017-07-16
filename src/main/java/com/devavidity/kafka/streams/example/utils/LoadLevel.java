package com.devavidity.kafka.streams.example.utils;

public enum LoadLevel {

    HIGH("high"), MEDIUM("medium"), LOW("low");

    String loadLevel;

    LoadLevel(String loadLevel){
        this.loadLevel = loadLevel;
    }

    public String toString(){
        return loadLevel;
    }

}
