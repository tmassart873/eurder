package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class ItemRepository {
    private HashMap<UUID, Item> items = new HashMap<>();

    public ItemRepository() {}

    public Item save(Item item) {
        items.put(item.getId(), item);
        return item;
    }

    public List<Item> getAllItems(){
        return items.values().stream().toList();
    }

}
