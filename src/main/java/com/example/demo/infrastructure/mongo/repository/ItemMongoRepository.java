package com.example.demo.infrastructure.mongo.repository;

import com.example.demo.domain.model.Item;
import com.example.demo.domain.repository.ItemRepository;
import com.example.demo.infrastructure.mongo.mapper.ItemDBOMapper;
import com.example.demo.infrastructure.mongo.mapper.ItemMapper;
import com.example.demo.infrastructure.mongo.model.ItemDBO;
import com.example.demo.infrastructure.mongo.repository.dbo.ItemMongoRepositoryDBO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemMongoRepository implements ItemRepository {
  private final ItemMongoRepositoryDBO itemMongoRepositoryDBO;
  private final ItemDBOMapper itemDBOMapper;
  private final ItemMapper itemMapper;

  @Override
  public Item save(Item item) {
    ItemDBO dbo = itemDBOMapper.apply(item);
    return itemMapper.apply(itemMongoRepositoryDBO.save(dbo));
  }

  @Override
  public List<Item> findAll() {
    return itemMongoRepositoryDBO.findAll().stream().map(itemMapper).toList();
  }
}
