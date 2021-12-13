package com.switchfully.eurder.service.dtos;

import com.switchfully.eurder.service.dtos.orderdto.OrderDto;

import java.util.List;

public class TotalOrderDto {
    private List<OrderDto> orders;
    private double totalDueOrders;

    public List<OrderDto> getOrders() {
        return orders;
    }

    public TotalOrderDto setOrders(List<OrderDto> orders) {
        this.orders = orders;
        return this;
    }

    public double getTotalDue() {
        return totalDueOrders;
    }

    public TotalOrderDto setTotalDueOrders(double totalDueOrders) {
        this.totalDueOrders = totalDueOrders;
        return this;
    }

    @Override
    public String toString() {
        return "TotalOrderDto{" +
                "orders=" + orders +
                ", totalDueOrders=" + totalDueOrders +
                '}';
    }
}
