package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.order.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepository {
    private HashMap<UUID, Order> orders = new HashMap<>();
    //HashMap<customerId,orderId>
    private HashMap<UUID, UUID> ordersByCustomerId = new HashMap<>();

    public OrderRepository() {
    }

    public Order save(Order order) {
        orders.put(order.getOrderId(), order);
        ordersByCustomerId.put(order.getCustomerID(),order.getOrderId());
        return order;
    }

    public List<Order> getAllOrders() {
        return orders.values().stream().toList();
    }
}
