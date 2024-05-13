package com.example.demo.infrastructure.mongo.mapper;

import com.example.demo.domain.model.Item;
import com.example.demo.infrastructure.mongo.model.ItemDBO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service("itemFromDBOMapper")
public class ItemMapper implements Function<ItemDBO, Item> {
  @Override
  public Item apply(ItemDBO dbo) {
    return Item.builder().name(dbo.name()).price(dbo.price()).build();
  }
}
