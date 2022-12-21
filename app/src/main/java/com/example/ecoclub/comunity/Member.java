package com.example.ecoclub.comunity;

public class Member {
    private int id;
    private String nameMember;
    private String rango;
    private int logros;

    public Member(int id, String nameMember, String rango, int logros) {
        this.id = id;
        this.nameMember = nameMember;
        this.rango = rango;
        this.logros = logros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameMember() {
        return nameMember;
    }

    public void setNameMember(String nameMember) {
        this.nameMember = nameMember;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public int getLogros() {
        return logros;
    }

    public void setLogros(int logros) {
        this.logros = logros;
    }
}
