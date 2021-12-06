package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.domain.order.Order;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class OrderRepository {
    private HashMap<UUID, Order> orders = new HashMap<>();

    public OrderRepository() {}

    public Order save(Order order) {
        orders.put(order.getOrderId(), order);
        return order;
    }

    public List<Order> getAllItems(){
        return orders.values().stream().toList();
    }

}
