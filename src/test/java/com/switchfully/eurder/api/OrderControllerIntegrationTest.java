package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.address.Address;
import com.switchfully.eurder.domain.email.EmailAddress;
import com.switchfully.eurder.domain.order.ItemGroup;
import com.switchfully.eurder.domain.order.Order;
import com.switchfully.eurder.domain.user.Role;
import com.switchfully.eurder.domain.user.User;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.OrderRepository;
import com.switchfully.eurder.service.dtos.itemdto.CreateItemDto;
import com.switchfully.eurder.service.dtos.itemdto.ItemDto;
import com.switchfully.eurder.service.dtos.orderdto.CreateOrderDto;
import com.switchfully.eurder.service.dtos.orderdto.OrderDto;
import com.switchfully.eurder.service.mappers.ItemMapper;
import com.switchfully.eurder.service.mappers.OrderMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = TestApplication.class)
public class OrderControllerIntegrationTest {

    @Autowired
    private ItemController itemController;
    @Autowired
    private ItemMapper itemMapper = new ItemMapper();
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderController orderController;
    @Autowired
    private OrderMapper orderMapper = new OrderMapper();
    @Autowired
    private OrderRepository orderRepository;

    CreateOrderDto createOrderDto1;
    CreateOrderDto createOrderDto2;
    User customerThatOrders;
    Address address;
    CreateItemDto itemToOrder;
    ItemDto itemDto;
    OrderDto orderDto;
    List<ItemGroup> itemsToOrder;

    private void initOrders() {
        address = Address.AddressBuilder.addressBuilder()
                .withStreetName("Veldstraat")
                .withStreetNumber("68")
                .withPostCode("9000")
                .withCity("Ghent")
                .build();
        customerThatOrders = User.UserBuilder.userBuilder()
                .withFirstName("Tommy")
                .withLastName("Wilkins")
                .withAddress(address)
                .withEmailAddress(new EmailAddress("tommy.w", "order.com"))
                .withPhoneNumber("1234567890")
                .withPassword("newMember")
                .withRole(Role.CUSTOMER)
                .build();

        itemToOrder = new CreateItemDto()
                .setName("Chips")
                .setDescription("Lays BBQ")
                .setPrice(3)
                .setAmountInStock(100);

        itemDto = itemController.addNewItem(itemToOrder,"Basic YWRtaW4udG1Ab3JkZXIuY29tOmFkbWluX3Rt");

        ItemGroup itemGroup = new ItemGroup(itemDto.getId(),9);

        itemsToOrder = List.of(itemGroup);

        createOrderDto1 = new CreateOrderDto()
                .setCustomerId(customerThatOrders.getId())
                .setItems(itemsToOrder);
        createOrderDto2 = new CreateOrderDto()
                .setCustomerId(customerThatOrders.getId())
                .setItems(itemsToOrder);
    }

    @BeforeEach
    void setUp() {
        initOrders();
    }

    @Test
    void givenCreateOrderDto_whenAddingAnOrder_thenOrderIsAddedToRepository() {
        //Given

        //When
        orderDto = orderController.addNewOrder(createOrderDto1);
        List<OrderDto> actual = orderRepository.getAllOrders().stream().map(order -> orderMapper.mapOrderToOrderDto(order)).toList();

        //Then
        assertTrue(actual.contains(orderDto));
    }

    @Test
    void givenCreateOrderDto_whenAddingAnOrder_thenTheAmountInStockIsChanged() {
        //Given

        //When
         orderController.addNewOrder(createOrderDto1);
        Order order = orderRepository.getAllOrders().get(0);
         int amountInStock = itemRepository.getItemById(order.getItems().get(0).getItemId()).getAmountInStock();

        //Then
        //2 orders of 9 items where ordered from the stock of 100 ( 100 - 9 - 9 = 82)
        assertEquals(82, amountInStock);
    }


    @Test
    void givenCreateOrderDto_whenAddingAnOrder_thenTheShippingDateIsSet() {
        //Given

        //When
        orderController.addNewOrder(createOrderDto1);
        Order order = orderRepository.getAllOrders().get(0);
        LocalDate shippingDate = order.getItems().get(0).getShippingDate();

        //Then
        assertEquals(shippingDate, LocalDate.now().plusDays(1));
    }

    @Test
    void givenACustomerThatDidSeveralOrders_whenGettingAllOrderByCustomerId_thenTheOrderOverviewIsReturned() {
        //Given
        UUID customerId = customerThatOrders.getId();

        //When
        orderController.addNewOrder(createOrderDto1);
        orderController.addNewOrder(createOrderDto2);

        //Then
        Assertions.assertEquals(orderController.getAllOrdersForCustomer(customerId.toString()).getTotalDue(),54.0);
    }

}
