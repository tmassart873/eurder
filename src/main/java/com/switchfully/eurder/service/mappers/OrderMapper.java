package com.switchfully.eurder.service.mappers;

import com.switchfully.eurder.domain.order.Order;
import com.switchfully.eurder.service.dtos.TotalOrderDto;
import com.switchfully.eurder.service.dtos.orderdto.CreateOrderDto;
import com.switchfully.eurder.service.dtos.orderdto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
    public TotalOrderDto mapListOfOrderToTotalOrderDto(List<OrderDto> orders){
        double totalDueOrders = orders.stream().mapToDouble(OrderDto::getTotalDue).sum();
        return new TotalOrderDto()
                .setOrders(orders)
                .setTotalDueOrders(totalDueOrders);
    }
}
