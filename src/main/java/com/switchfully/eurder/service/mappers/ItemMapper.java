package com.switchfully.eurder.service.mappers;

import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.domain.item.StockUrgency;
import com.switchfully.eurder.service.dtos.ItemOverviewDto;
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

    public ItemOverviewDto mapItemDToItemOverviewDto(ItemDto itemDto) {
        StockUrgency urgency = StockUrgency.STOCK_HIGH;
        int currentStock = itemDto.getAmountInStock();
        if (currentStock < 5) {
            urgency = StockUrgency.STOCK_LOW;
        } else if (currentStock < 10) {
            urgency = StockUrgency.STOCK_MEDIUM;
        }
        return new ItemOverviewDto()
                .setItemDto(itemDto)
                .setUrgency(urgency);
    }
}
