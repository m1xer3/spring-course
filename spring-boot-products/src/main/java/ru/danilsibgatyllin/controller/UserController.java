package ru.danilsibgatyllin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.danilsibgatyllin.models.User;
import ru.danilsibgatyllin.models.UserRepository;
import ru.danilsibgatyllin.models.UserSpecifications;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("usernameFilter") Optional<String> usernameFilter,
                           @RequestParam("minAge") Optional<Integer> minAge,
                           @RequestParam("maxAge") Optional<Integer> maxAge){
        logger.info("User list page requested");

        Specification<User> spec = Specification.where(null);

        if (usernameFilter.isPresent() && !usernameFilter.get().isBlank()) {
            spec = spec.and(UserSpecifications.usernamePrefix(usernameFilter.get()));
        }
        if (minAge.isPresent()) {
            spec = spec.and(UserSpecifications.minAge(minAge.get()));
        }
        if (maxAge.isPresent()) {
            spec = spec.and(UserSpecifications.maxAge(maxAge.get()));
        }

        model.addAttribute("users", userRepository.findAll(spec));
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
        model.addAttribute("user",userRepository.findById(id));
        return "user_form";
    }

    @PostMapping("/add")
    public String update(User user) {
        if(user.getId()==null){
            logger.info("Add user "+user);
            userRepository.save(user);
        } else {
            logger.info("Update user "+user);
            userRepository.save(user);
        }
        return "redirect:/user";
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") Long id) {
        logger.info("Delete user id "+id);
        userRepository.deleteById(id);
        return "redirect:/user";
    }



}
