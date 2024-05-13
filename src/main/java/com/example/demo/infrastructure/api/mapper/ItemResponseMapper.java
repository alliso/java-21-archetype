package com.example.demo.infrastructure.api.mapper;

import com.example.demo.domain.model.Item;
import com.example.demo.infrastructure.api.model.ItemResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ItemResponseMapper implements Function<Item, ItemResponse> {
    @Override
    public ItemResponse apply(Item item) {
        return new ItemResponse(item.getName(), item.getPrice());
    }
}
