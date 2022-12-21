package com.example.ecoclub.comunity;

//esta clase puede servir para el Comunity y MiComunity fragment
public class ComunityContent {
    private String name;
    private String description;
    private int id;

    public ComunityContent(int id, String name, String description) {
        this.name = name;
        this.description = description;
        this.id = id;
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

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
