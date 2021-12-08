package com.switchfully.eurder.service.dtos.orderdto;

import com.switchfully.eurder.domain.order.ItemGroup;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderDto {
    private UUID orderId;
    private UUID customerId;
    private List<ItemGroup> items;
    private double totalDue;

    public double getTotalDue() {
        return totalDue;
    }

    public OrderDto setTotalDue(double totalDue) {
        this.totalDue = totalDue;
        return this;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public OrderDto setOrderId(UUID orderId) {
        this.orderId = orderId;
        return this;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public OrderDto setCustomerId(UUID customerId) {
        this.customerId = customerId;
        return this;
    }

    public List<ItemGroup> getItems() {
        return items;
    }

    public OrderDto setItems(List<ItemGroup> items) {
        this.items = items;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(orderId, orderDto.orderId);
    }
}
