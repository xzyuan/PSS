package com.example.demo.model;


public class Staff {
    private int staff_id;
    private String name;
    private int division_id;
    private int card_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDivision_id() {
        return division_id;
    }

    public void setDivision_id(int division_id) {
        this.division_id = division_id;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }
}
