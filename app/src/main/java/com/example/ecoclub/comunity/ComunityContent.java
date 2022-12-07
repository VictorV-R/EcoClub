package com.example.ecoclub.comunity;

public class ComunityContent {
    private String name;
    private int id;

    public ComunityContent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
