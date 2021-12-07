package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.domain.order.ItemGroup;
import com.switchfully.eurder.domain.order.Order;
import com.switchfully.eurder.exceptions.NotEnoughStockException;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.service.dtos.itemdto.CreateItemDto;
import com.switchfully.eurder.service.dtos.itemdto.ItemDto;
import com.switchfully.eurder.service.mappers.ItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private ItemMapper itemMapper = new ItemMapper();

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemDto addNewItem(CreateItemDto createItemDto) {
        Item item = itemMapper.mapCreateItemDtoToItem(createItemDto);
        itemRepository.save(item);
        return itemMapper.mapItemToItemDto(item);
    }

    public List<ItemDto> getAllItems(){
        return itemRepository.getAllItems().stream().map(itemMapper::mapItemToItemDto).toList();
    }

    public void reduceAmountInStock(ItemGroup itemGroup){
        int currentStock = itemRepository.getItemById(itemGroup.getItemId()).getAmountInStock();
        int amountOrdered = itemGroup.getAmount();
        if(amountOrdered > currentStock){
            throw new NotEnoughStockException();
        }
        else
            itemRepository.getItemById(itemGroup.getItemId()).setAmountInStock(currentStock - amountOrdered);
    }
    public void updateStock(Order order){
        order.getItems().forEach(this::reduceAmountInStock);
    }

}
