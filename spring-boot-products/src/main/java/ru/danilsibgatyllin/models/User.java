package ru.danilsibgatyllin.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer age;

    @NotBlank
    @Column(length = 512,nullable = false)
    private String username;

    @Column
    private String password;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(Long id, String username, String password, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
    }
}
