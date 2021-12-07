package com.switchfully.eurder.service.dtos.orderdto;

import com.switchfully.eurder.domain.order.ItemGroup;

import java.util.List;
import java.util.UUID;

public class CreateOrderDto {

    private String customerId;
    private List<ItemGroup> items;

    public UUID getCustomerId() {
        return UUID.fromString(customerId);
    }

    public CreateOrderDto setCustomerId(UUID customerId) {
        this.customerId = customerId.toString();
        return this;
    }

    public List<ItemGroup> getItems() {
        return items;
    }

    public CreateOrderDto setItems(List<ItemGroup> items) {
        this.items = items;
        return this;
    }
}
