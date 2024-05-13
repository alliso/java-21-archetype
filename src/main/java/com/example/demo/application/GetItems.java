package com.example.demo.application;

import com.example.demo.domain.model.Item;
import com.example.demo.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetItems {
    private final ItemRepository itemRepository;

    public List<Item> apply() {
        return itemRepository.findAll();
    }
}
