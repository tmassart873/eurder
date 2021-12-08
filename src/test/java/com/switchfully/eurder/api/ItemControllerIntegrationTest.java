package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.service.dtos.itemdto.CreateItemDto;
import com.switchfully.eurder.service.dtos.itemdto.ItemDto;
import com.switchfully.eurder.service.mappers.ItemMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.SchemaOutputResolver;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = TestApplication.class)
public class ItemControllerIntegrationTest {
    @Autowired
    private ItemController itemController;
    @Autowired
    private ItemMapper itemMapper = new ItemMapper();
    @Autowired
    private ItemRepository itemRepository;

    CreateItemDto createItemDto1;
    CreateItemDto createItemDto2;
    ItemDto itemDto1;
    ItemDto itemDto2;
    List<ItemDto> actual;
    static boolean isSetupDone = false;

    @BeforeEach
    void setUp() {
        initItems();
    }

    private void initItems() {
        createItemDto1 = new CreateItemDto()
                .setName("Chips")
                .setDescription("Lays Paprika")
                .setPrice(3.99)
                .setAmountInStock(10);
        createItemDto2 = new CreateItemDto()
                .setName("Cola")
                .setDescription("EveryDay")
                .setPrice(1.09)
                .setAmountInStock(96);
    }

    @Test
    void givenCreateItemDto_whenAddingAnItem_thenItemIsAddedToRepository() {
        //Given

        //When
        itemDto1 = itemController.addNewItem(createItemDto1, "Basic YWRtaW4udG1Ab3JkZXIuY29tOmFkbWluX3Rt");
        actual = itemRepository.getAllItems().stream().map(item -> itemMapper.mapItemToItemDto(item)).toList();

        //Then
        assertTrue(actual.contains(itemDto1));

    }

    @Test
    void givenAnItemInTheRepository_whenGettingAllTheItems_thenAllItemsInTheRepository() {
        //Given

        //When
        itemDto1 = itemController.addNewItem(createItemDto1, "Basic YWRtaW4udG1Ab3JkZXIuY29tOmFkbWluX3Rt");
        itemDto2 = itemController.addNewItem(createItemDto2, "Basic YWRtaW4udG1Ab3JkZXIuY29tOmFkbWluX3Rt");
        List<ItemDto> expectedItems = List.of(itemDto1, itemDto2);
        actual = itemRepository.getAllItems().stream().map(item -> itemMapper.mapItemToItemDto(item)).toList();
        //Then
        assertTrue(actual.containsAll(expectedItems));

    }
}
