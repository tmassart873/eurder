package com.switchfully.eurder.api;

import com.switchfully.eurder.service.OrderService;
import com.switchfully.eurder.service.dtos.TotalOrderDto;
import com.switchfully.eurder.service.dtos.orderdto.CreateOrderDto;
import com.switchfully.eurder.service.dtos.orderdto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final Logger myLogger = LoggerFactory.getLogger(UserController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto addNewOrder(@RequestBody CreateOrderDto createOrderDto) {
        return orderService.addNewOrder(createOrderDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TotalOrderDto getAllOrdersForCustomer(@PathVariable String id) {
       return orderService.getOrdersByUser(UUID.fromString(id));
    }
}
