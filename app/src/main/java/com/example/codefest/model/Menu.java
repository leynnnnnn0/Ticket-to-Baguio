package com.example.codefest.model;

public class Menu {

    public String id, image, name, description, price, isOutOfStock;

    public Menu(String image, String name, String description, String price, String isOutOfStock) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isOutOfStock = isOutOfStock;
    }
}
