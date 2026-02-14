package com.example.codefest.model;

public class Order {
    public String orderNumber, price, createdAt, status;

    public Order(){}

    public Order(String orderNumber, String price, String createdAt, String status) {
        this.orderNumber = orderNumber;
        this.price = price;
        this.createdAt = createdAt;
        this.status = status;
    }
}
