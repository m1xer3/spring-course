package ru.danilsibgatyllin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.danilsibgatyllin.models.User;
import ru.danilsibgatyllin.models.UserParams;
import ru.danilsibgatyllin.service.UserService;

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
        model.addAttribute("user", new User());
        return "user_form";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user",userService.findById(id));
        return "user_form";
    }

    @PostMapping("/add")
    public String update(User user) {
        if(user.getId()==null){
            logger.info("Add user "+user);
            userService.save(user);
        } else {
            logger.info("Update user "+user);
            userService.save(user);
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
