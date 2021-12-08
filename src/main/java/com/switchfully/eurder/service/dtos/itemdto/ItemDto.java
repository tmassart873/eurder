package com.switchfully.eurder.service.dtos.itemdto;

import java.util.Objects;

public class ItemDto {
    private String id;
    private String name;
    private String description;
    private double price;
    private int amountInStock;

    public String getId() {
        return id;
    }

    public ItemDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ItemDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public ItemDto setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto itemDto = (ItemDto) o;
        return Objects.equals(id, itemDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
