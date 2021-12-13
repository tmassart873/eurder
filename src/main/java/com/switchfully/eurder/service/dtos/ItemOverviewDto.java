package com.switchfully.eurder.service.dtos;

import com.switchfully.eurder.domain.item.StockUrgency;
import com.switchfully.eurder.service.dtos.itemdto.ItemDto;

public class ItemOverviewDto {
    private ItemDto item;
    private StockUrgency urgency;

    public ItemDto getItemDto() {
        return item;
    }

    public ItemOverviewDto setItemDto(ItemDto item) {
        this.item = item;
        return this;
    }

    public StockUrgency getUrgency() {
        return urgency;
    }

    public ItemOverviewDto setUrgency(StockUrgency urgency) {
        this.urgency = urgency;
        return this;
    }
}
