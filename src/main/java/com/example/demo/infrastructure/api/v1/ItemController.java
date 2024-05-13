package com.example.demo.infrastructure.api.v1;

import com.example.demo.application.CreateItem;
import com.example.demo.application.GetItems;
import com.example.demo.infrastructure.api.mapper.ItemMapper;
import com.example.demo.infrastructure.api.mapper.ItemResponseMapper;
import com.example.demo.infrastructure.api.model.ItemRequest;
import com.example.demo.infrastructure.api.model.ItemResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
  private final CreateItem createItem;
  private final GetItems getItems;

  private final ItemMapper itemMapper;
  private final ItemResponseMapper itemResponseMapper;

  @PostMapping
  public ResponseEntity<Void> createItem(@RequestBody ItemRequest request) {
    log.info("[ITEM_CONTROLLER] Creating new item: {}", request);
    createItem.apply(itemMapper.apply(request));

    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<ItemResponse>> getItems() {
    log.info("[ITEM_CONTROLLER] Get all items");

    return ResponseEntity.ok(getItems.apply().stream().map(itemResponseMapper).toList());
  }
}
