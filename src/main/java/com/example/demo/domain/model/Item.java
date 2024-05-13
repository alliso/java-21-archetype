package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Item {
    private String name;
    private double price;
}
