package ru.danilsibgatyllin.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.danilsibgatyllin.interfaces.UserInterface;
import ru.danilsibgatyllin.models.User;
import ru.danilsibgatyllin.models.UserParams;
import ru.danilsibgatyllin.interfaces.UserRepository;
import ru.danilsibgatyllin.models.UserSpecifications;


import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findWithFilter(UserParams userParams) {
        Specification<User> spec = Specification.where(null);

        if (userParams.getUsernameFilter() != null && !userParams.getUsernameFilter().isBlank()) {
            spec = spec.and(UserSpecifications.usernamePrefix(userParams.getUsernameFilter()));
        }
        if (userParams.getMinAge() != null) {
            spec = spec.and(UserSpecifications.minAge(userParams.getMinAge()));
        }
        if (userParams.getMaxAge() != null ) {
            spec = spec.and(UserSpecifications.maxAge(userParams.getMaxAge()));
        }

        if(userParams.getSortIsDown()==null || userParams.getSortIsDown()) {
            return userRepository.findAll(spec,
                    PageRequest.of(
                            Optional.ofNullable(userParams.getPage()).orElse(1) - 1,
                            Optional.ofNullable(userParams.getSize()).orElse(3),
                            Sort.by(Sort.Direction.ASC,Optional.ofNullable(userParams.getSortField())
                                    .filter(c -> !c.isBlank())
                                    .orElse("id"))));
        }else{
            return userRepository.findAll(spec,
                    PageRequest.of(
                            Optional.ofNullable(userParams.getPage()).orElse(1) - 1,
                            Optional.ofNullable(userParams.getSize()).orElse(3),
                            Sort.by(Sort.Direction.DESC,Optional.ofNullable(userParams.getSortField())
                                    .filter(c -> !c.isBlank())
                                    .orElse("id"))));
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}