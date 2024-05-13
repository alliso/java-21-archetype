package com.example.demo.infrastructure.mongo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("items")
public record ItemDBO (String name, Double price){}
