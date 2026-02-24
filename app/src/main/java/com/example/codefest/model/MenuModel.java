package com.example.codefest.model;

public class MenuModel {
    public String imagePath, name, price;

    public MenuModel(String imagePath, String name, String price) {
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
    }

    public MenuModel(String name, String price) {
        this.name = name;
        this.price = price;
    }
}
