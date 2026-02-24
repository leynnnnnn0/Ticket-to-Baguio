package com.example.codefest.model;

public class Menu {

    public String image, name, description;
    public int price, stock, id;

    public Menu(int id, String name, String description, int price, int stock, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }
    public Menu(int id, String name, String description, int price, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }
}
