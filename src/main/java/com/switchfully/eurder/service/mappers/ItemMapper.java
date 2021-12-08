package com.switchfully.eurder.service.mappers;

import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.service.dtos.itemdto.CreateItemDto;
import com.switchfully.eurder.service.dtos.itemdto.ItemDto;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item mapCreateItemDtoToItem(CreateItemDto createItemDto) {
        return new Item(createItemDto.getName(), createItemDto.getDescription(), createItemDto.getPrice(), createItemDto.getAmountInStock());
    }

    public ItemDto mapItemToItemDto(Item item) {

        return new ItemDto()
                .setId(item.getId())
                .setName(item.getName())
                .setDescription(item.getDescription())
                .setPrice(item.getPrice())
                .setAmountInStock(item.getAmountInStock());
    }
}
