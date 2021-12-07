package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.item.Item;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ItemRepository {
    private HashMap<String, Item> items = new HashMap<>();

    public ItemRepository() {
    }

    public Item save(Item item) {
        items.put(item.getId(), item);
        return item;
    }

    public List<Item> getAllItems() {
        return items.values().stream().toList();
    }

    public Item getItemById(String id) {
        return items.get(id);
    }

}
