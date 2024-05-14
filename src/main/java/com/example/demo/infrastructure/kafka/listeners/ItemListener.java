package com.example.demo.infrastructure.kafka.listeners;

import com.example.demo.application.CreateItem;
import com.example.demo.infrastructure.kafka.mapper.ItemMapper;
import com.example.demo.infrastructure.kafka.model.ItemPayload;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ItemListener {
  private final ItemMapper itemMapper;
  private final CreateItem createItem;
  private final ObjectMapper mapper;

  public ItemListener(ItemMapper itemMapper, CreateItem createItem, ObjectMapper mapper) {
    this.itemMapper = itemMapper;
    this.createItem = createItem;
    this.mapper =
        mapper
            .findAndRegisterModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
  }

  @KafkaListener(topics = "items.topic", groupId = "items.group")
  public void listen(ConsumerRecord<String, String> consumerRecord) throws Exception {
    ItemPayload payload = mapper.readValue(consumerRecord.value(), ItemPayload.class);
    createItem.apply(itemMapper.apply(payload));
  }
}
