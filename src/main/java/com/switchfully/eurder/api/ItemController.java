package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.Feature;
import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.service.ItemService;
import com.switchfully.eurder.service.SecurityService;
import com.switchfully.eurder.service.dtos.itemdto.CreateItemDto;
import com.switchfully.eurder.service.dtos.itemdto.ItemDto;
import com.switchfully.eurder.service.dtos.itemdto.UpdateItemDto;
import com.switchfully.eurder.service.mappers.ItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private ItemMapper itemMapper = new ItemMapper();
    private final SecurityService securityService;
    private final Logger myLogger = LoggerFactory.getLogger(UserController.class);

    public ItemController(ItemService itemService, SecurityService securityService,ItemRepository itemRepository) {
        this.itemService = itemService;
        this.securityService = securityService;
        this.itemRepository = itemRepository;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> getAllItems(){
        return itemService.getAllItems();
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addNewItem(@RequestBody CreateItemDto createItemDto,@RequestHeader String authorization){
        securityService.validateAccess(authorization, Feature.ADD_NEW_ITEM);
        return itemService.addNewItem(createItemDto);
    }
    @PutMapping(path = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ItemDto updateItem(@PathVariable String id, @RequestBody UpdateItemDto updateItemDto, @RequestHeader String authorization){
        Item item = itemRepository.getItemById(id);
        securityService.validateAccess(authorization, Feature.UPDATE_ITEM);
        item
                .setAmountInStock(updateItemDto.getAmountInStock())
                .setName(updateItemDto.getName())
                .setDescription(updateItemDto.getDescription())
                .setPrice(updateItemDto.getPrice());
        return itemMapper.mapItemToItemDto(item);

    }
}
