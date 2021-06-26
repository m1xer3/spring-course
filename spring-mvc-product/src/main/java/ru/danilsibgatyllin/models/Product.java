package ru.danilsibgatyllin.models;

import lombok.Data;

@Data
public class Product {

    private Long id;
    private String title;
    private Integer cost;

    public Product(String title, Integer cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product() {
    }
}
