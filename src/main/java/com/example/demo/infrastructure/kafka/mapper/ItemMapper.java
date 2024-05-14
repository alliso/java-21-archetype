package com.example.demo.infrastructure.kafka.mapper;

import com.example.demo.domain.model.Item;
import com.example.demo.infrastructure.kafka.model.ItemPayload;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service("kafkaItemMapper")
public class ItemMapper implements Function<ItemPayload, Item> {
  @Override
  public Item apply(ItemPayload itemPayload) {
    return Item.builder().name(itemPayload.name()).price(itemPayload.price()).build();
  }
}
