package ru.danilsibgatyllin.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512,nullable = false)
    private String title;

    @Column(nullable = false)
    private BigDecimal cost;

    public Product(String title, BigDecimal cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product() {
    }

}
