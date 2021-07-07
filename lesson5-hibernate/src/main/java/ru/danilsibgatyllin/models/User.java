package ru.danilsibgatyllin.models;

import lombok.Data;

import javax.persistence.*;

@Data //no final getter and setter
@Entity
@Table(name="users")
@NamedQueries({ // block of named querry
        @NamedQuery(name="allUsers",query = "select u from User u"),//named query
        @NamedQuery(name = "maxAge",query = "select max(u.age) from User u group by u.age")
})
//not abstract final
public class User {

    @Id //primarykey
    @GeneratedValue(strategy = GenerationType.IDENTITY) // encriment
    private Long id;

    @Column(length = 512, nullable = false)
    private String username;

    @Column(nullable = false)
    private Integer age;

//    @Transient //no create database
//    private boolean example;

    public User() {
    }

    public User(Long id,String username,Integer age) {
        this.id=id;
        this.username = username;
        this.age=age;
    }

}
