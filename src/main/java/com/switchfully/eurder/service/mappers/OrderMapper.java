package com.switchfully.eurder.service.mappers;

import com.switchfully.eurder.domain.order.Order;
import com.switchfully.eurder.service.dtos.orderdto.CreateOrderDto;
import com.switchfully.eurder.service.dtos.orderdto.OrderDto;

public class OrderMapper {
    public Order mapCreateOrderDtoToOrder(CreateOrderDto createOrderDto) {
        return new Order(createOrderDto.getCustomerId(),createOrderDto.getItems());
    }

    public OrderDto mapOrderToOrderDto(Order order) {
        return new OrderDto()
                .setOrderId(order.getOrderId())
                .setCustomerId(order.getCustomerID())
                .setItems(order.getItems())
                .setTotalDue(order.getTotalDue());
    }
}
