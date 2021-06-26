package ru.danilsibgatyllin.models;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String username;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

}
