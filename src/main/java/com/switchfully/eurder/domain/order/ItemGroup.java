package com.switchfully.eurder.domain.order;

import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.repository.ItemRepository;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.UUID;

public class ItemGroup {
    private UUID itemId;
    private int amount;
    private LocalDate shippingDate;
    private ItemRepository itemRepository;

    public ItemGroup(UUID itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
        this.shippingDate = calculateShippingDate(itemId);
    }

    private LocalDate calculateShippingDate(UUID itemId) {
        Item item = itemRepository.getItemById(itemId);
        if(item.isAvailable())
            return LocalDate.now().plusDays(1);
        return LocalDate.now().plusDays(7);
    }
    public double calculateItemTotalCost(){
        double price = itemRepository.getItemById(itemId).getPrice();
        return amount * price;
    }
}
