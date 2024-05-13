package com.example.demo.infrastructure.mongo.mapper;

import com.example.demo.domain.model.Item;
import com.example.demo.infrastructure.mongo.model.ItemDBO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ItemDBOMapper implements Function<Item, ItemDBO> {
    @Override
    public ItemDBO apply(Item item) {
        return new ItemDBO(item.getName(), item.getPrice());
    }
}
