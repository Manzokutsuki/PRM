package com.example.customlv;

public class Fruit {
    private int avatar;
    private String name;
    private String description;

    public Fruit (int avatar, String name, String description){
        this.avatar = avatar;
        this.name = name;
        this.description = description;
    }

    public int getAvatar() { return avatar;}
    public String getName() { return name; }
    public String getDescription() {return description;}
}
