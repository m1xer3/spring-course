package ru.danilsibgatyllin.interfaces;

import org.springframework.data.domain.Page;
import ru.danilsibgatyllin.models.User;
import ru.danilsibgatyllin.models.UserParams;

import java.util.List;
import java.util.Optional;

public interface UserInterface {
    List<User> findAll();

    Page<User> findWithFilter(UserParams userParams);

    Optional<User> findById(Long id);

    void save(User user);

    void deleteById(Long id);
}
