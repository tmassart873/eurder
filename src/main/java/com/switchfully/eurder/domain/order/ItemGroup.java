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

    public UUID getItemId() {
        return itemId;
    }

    public ItemGroup setItemId(UUID itemId) {
        this.itemId = itemId;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public ItemGroup setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public ItemGroup setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    public ItemRepository getItemRepository() {
        return itemRepository;
    }

    public ItemGroup setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        return this;
    }
}
