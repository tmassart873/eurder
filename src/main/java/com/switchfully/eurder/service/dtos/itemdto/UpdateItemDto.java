package com.switchfully.eurder.service.dtos.itemdto;

public class UpdateItemDto {

    private String name;
    private String description;
    private double price;
    private int amountInStock;

    public String getName() {
        return name;
    }

    public UpdateItemDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UpdateItemDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public UpdateItemDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public UpdateItemDto setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
        return this;
    }
}
