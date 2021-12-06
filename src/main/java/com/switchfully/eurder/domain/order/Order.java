package com.switchfully.eurder.domain.order;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Order {

    private final UUID orderId;
    private final UUID customerId;
    private final List<ItemGroup> items;


    public Order(UUID customerId, List<ItemGroup> items) {
        this.orderId = UUID.randomUUID();
        this.customerId = customerId;
        this.items = items;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getCustomerID() {
        return customerId;
    }

    public List<ItemGroup> getItems() {
        return items;
    }

    public double calculateTotalDue(){
        return items.stream().mapToDouble(ItemGroup::calculateItemTotalCost).sum();
    }

}
