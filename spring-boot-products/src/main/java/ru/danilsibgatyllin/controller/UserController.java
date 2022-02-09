package ru.danilsibgatyllin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.danilsibgatyllin.models.User;
import ru.danilsibgatyllin.models.UserParams;
import ru.danilsibgatyllin.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listPage(Model model,
                           UserParams userParams) {
        logger.info("User list page requested");

        model.addAttribute("users", userService.findWithFilter(userParams));
        return "users";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        logger.info("New user page requested");
        model.addAttribute("user", new UserDto());
        return "user_form";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user",userService.findById(id));
        return "user_form";
    }

    @PostMapping("/add")
    public String update(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {

        if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            result.rejectValue("password", "", "Repeated password is not correct");
            return "user_form";
        }

        if(userDto.getId()==null){
            logger.info("Add user "+userDto);
            userService.save(userDto);
        } else {
            logger.info("Update user "+userDto);
            userService.save(userDto);
        }
        return "redirect:/user";
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") Long id) {
        logger.info("Delete user id "+id);
        userService.deleteById(id);
        return "redirect:/user";
    }
}
