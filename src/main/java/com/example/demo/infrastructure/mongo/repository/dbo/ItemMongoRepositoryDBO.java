package com.example.demo.infrastructure.mongo.repository.dbo;

import com.example.demo.infrastructure.mongo.model.ItemDBO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemMongoRepositoryDBO extends MongoRepository<ItemDBO, String> {
    Optional<ItemDBO> findByName(String name);
}

