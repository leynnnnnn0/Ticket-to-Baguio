package com.example.codefest.model;

public class Cart {

    public int id, price, quantity;
    public String name, description, image;
    public Cart(int id, String name, int price, int quantity, String image){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }
}
