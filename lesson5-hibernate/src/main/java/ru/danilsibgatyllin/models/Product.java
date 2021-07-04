package ru.danilsibgatyllin.models;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="products")
public class Product {

    @Id //primarykey
    @GeneratedValue(strategy = GenerationType.IDENTITY) // encriment
    private Long id;

    @Column(length = 512, nullable = false)
    private String title;

    @Getter
    @Column(nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "product")
    private List<Order> whoByThisProduct;

    public Product(Long id,String title, Integer price) {
        this.id =id;
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }

    public Product() {
    }
}
