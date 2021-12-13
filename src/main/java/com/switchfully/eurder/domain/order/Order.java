package com.switchfully.eurder.domain.order;

import java.util.List;
import java.util.UUID;

public class Order {

    private final UUID orderId;
    private final UUID customerId;
    private final List<ItemGroup> items;
    private double totalDue;


    public Order(UUID customerId, List<ItemGroup> items) {
        this.orderId = UUID.randomUUID();
        this.customerId = customerId;
        this.items = items;
    }

    public double getTotalDue() {
        return totalDue;
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

    public Order setTotalDue(double totalDue) {
        this.totalDue = totalDue;
        return this;
    }
}
