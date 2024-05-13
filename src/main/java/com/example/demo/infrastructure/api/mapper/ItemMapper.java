package com.example.demo.infrastructure.api.mapper;

import com.example.demo.domain.model.Item;
import com.example.demo.infrastructure.api.model.ItemRequest;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;

@Service("itemFromItemRequestMapper")
public class ItemMapper implements Function<ItemRequest, Item> {
    @Override
    public Item apply(ItemRequest request) {
        return Item.builder().name(request.name()).price(request.price()).build();
    }
}
