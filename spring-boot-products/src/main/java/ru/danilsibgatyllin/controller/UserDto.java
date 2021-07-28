package ru.danilsibgatyllin.controller;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

//Data transfer object

@Data
public class UserDto {

    private Long id;

    @Min(value=18)
    private Integer age;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String repeatPassword;

    public UserDto(Long id,  String username, Integer age, String password) {
        this.id = id;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public UserDto() {

    }
}
