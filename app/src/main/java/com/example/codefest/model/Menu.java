package com.example.codefest.model;

public class Menu {

    public String id, image, name, description, isOutOfStock;
    public int price;

    public Menu(String image, String name, String description, int price, String isOutOfStock) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isOutOfStock = isOutOfStock;
    }
}
