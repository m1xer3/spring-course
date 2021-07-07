package ru.danilsibgatyllin.models;

import javax.persistence.*;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "idclient")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;

    public Order(){
    }

    public Order(Long id,Client client, Product product) {
        this.id=id;
        this.client=client;
        this.product=product;
    }
}
