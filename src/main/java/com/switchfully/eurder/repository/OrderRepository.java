package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.order.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    private HashMap<UUID, Order> orders = new HashMap<>();

    public OrderRepository() {
    }

    public Order save(Order order) {
        orders.put(order.getOrderId(), order);
        return order;
    }

    public List<Order> getAllOrders() {
        return orders.values().stream().toList();
    }

    public List<Order> getOrdersByCustomerId(UUID customerId){
        return orders.values().stream().filter(order -> order.getCustomerID().equals(customerId)).toList();
    }
}
