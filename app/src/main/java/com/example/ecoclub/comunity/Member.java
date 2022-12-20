package com.example.ecoclub.comunity;

public class Member {
    private int id;
    private String nameMember;

    public Member(int id, String nameMember) {
        this.id = id;
        this.nameMember = nameMember;
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
}
