package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.domain.order.ItemGroup;
import com.switchfully.eurder.domain.order.Order;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.OrderRepository;
import com.switchfully.eurder.service.dtos.TotalOrderDto;
import com.switchfully.eurder.service.dtos.orderdto.CreateOrderDto;
import com.switchfully.eurder.service.dtos.orderdto.OrderDto;
import com.switchfully.eurder.service.mappers.OrderMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private OrderMapper orderMapper = new OrderMapper();

    public OrderService(OrderRepository orderRepository,ItemRepository itemRepository,ItemService itemService) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    public OrderDto addNewOrder(CreateOrderDto createOrderDto) {
        Order order = orderMapper.mapCreateOrderDtoToOrder(createOrderDto);
        setShippingDates(order);
        order.setTotalDue(calculateTotalDue(order));
        itemService.updateStock(order);
        orderRepository.save(order);
        return orderMapper.mapOrderToOrderDto(order);
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.getAllOrders().stream().map(order -> orderMapper.mapOrderToOrderDto(order)).toList();
    }

    private LocalDate calculateShippingDate(ItemGroup itemGroup) {
        Item item = itemRepository.getItemById(itemGroup.getItemId());
        if(item.isAvailable())
            return LocalDate.now().plusDays(1);
        return LocalDate.now().plusDays(7);
    }

    public void setShippingDates(Order order){
        List<ItemGroup> itemsInCart = order.getItems();
        itemsInCart.forEach(itemGroup -> itemGroup.setShippingDate(calculateShippingDate(itemGroup)));
    }

    public double calculateItemTotalCost(ItemGroup itemGroup){
        double price = itemRepository.getItemById(itemGroup.getItemId()).getPrice();
        int amount = itemGroup.getAmount();
        return amount * price;
    }

    public double calculateTotalDue(Order order){
        return order.getItems().stream().mapToDouble(this::calculateItemTotalCost).sum();
    }

    public TotalOrderDto getOrdersByUser(UUID customerId){
        return orderMapper.mapListOfOrderToTotalOrderDto(orderRepository.getOrdersByCustomerId(customerId).stream().map(order -> orderMapper.mapOrderToOrderDto(order)).toList());
    }





}
