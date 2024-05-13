package com.example.demo.domain.repository;

import com.example.demo.domain.model.Item;

import java.util.List;

public interface ItemRepository {
    Item save(Item item);
    List<Item> findAll();
}
