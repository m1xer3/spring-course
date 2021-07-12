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

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

}
