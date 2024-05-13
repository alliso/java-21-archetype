package com.example.demo.application;

import com.example.demo.domain.model.Item;
import com.example.demo.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateItem {
    private final ItemRepository itemRepository;

    public Item apply(Item item) {
        return itemRepository.save(item);
    }
}
