package ru.danilsibgatyllin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.danilsibgatyllin.controller.UserDto;
import ru.danilsibgatyllin.exceptions.BadRequestException;
import ru.danilsibgatyllin.exceptions.NotFoundException;
import ru.danilsibgatyllin.models.UserParams;
import ru.danilsibgatyllin.service.UserService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = "/filter", produces = "application/json")
    public Page<UserDto> findWithFilter(UserParams userParams) {
        return userService.findWithFilter(userParams);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public UserDto findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @PostMapping(produces = "application/json")
    public UserDto create(@RequestBody UserDto userDto) {
        if (userDto.getId() != null) {
            throw new BadRequestException("User Id should be null");
        }
        userService.save(userDto);
        return userDto;
    }

    @PutMapping(produces = "application/json")
    public void update(@RequestBody UserDto userDto) {
        if (userDto.getId() == null) {
            throw new BadRequestException("User Id shouldn't be null");
        }
        userService.save(userDto);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

}