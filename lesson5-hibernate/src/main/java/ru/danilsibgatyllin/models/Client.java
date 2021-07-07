package ru.danilsibgatyllin.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="clients")
public class Client {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(length = 522,nullable = false)
    private String clientName;

    @OneToMany(mappedBy = "client")
    private List<Order> orderList;

    public Client(){
    }

    public Client(Long id, String name){
        this.id=id;
        this.clientName=name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
