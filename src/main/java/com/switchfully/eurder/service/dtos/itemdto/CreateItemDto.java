package com.switchfully.eurder.service.dtos.itemdto;

public class CreateItemDto {
    private String name;
    private String description;
    private double price;
    private int amountInStock;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public CreateItemDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public CreateItemDto setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
        return this;
    }
}
