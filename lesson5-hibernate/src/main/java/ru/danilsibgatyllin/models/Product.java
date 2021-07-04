package ru.danilsibgatyllin.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="products")
public class Product {

    @Id //primarykey
    @GeneratedValue(strategy = GenerationType.IDENTITY) // encriment
    private Long id;

    @Column(length = 512, nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer price;

    public Product(Long id,String title, Integer price) {
        this.id =id;
        this.title = title;
        this.price = price;
    }

    public Product() {
    }
}
