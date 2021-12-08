package com.switchfully.eurder.domain.item;

import java.util.UUID;

public class Item {
    private final String id;
    private String name;
    private String description;
    private double price;
    private int amountInStock;

    public Item(String name, String description, double price, int amountInStock) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountInStock = amountInStock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public boolean isAvailable(){
        return amountInStock > 0;
    }

    public Item setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
        return this;
    }

    public Item setPrice(double price) {
        this.price = price;
        return this;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }
}
