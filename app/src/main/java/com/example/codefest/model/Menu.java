package com.example.codefest.model;

public class Menu {

    public String id, image, name, description;
    public int price, stock;

    public Menu(String name, String description, int price, int stock, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }
}
