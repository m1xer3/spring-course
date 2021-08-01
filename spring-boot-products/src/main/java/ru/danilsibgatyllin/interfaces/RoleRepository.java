package ru.danilsibgatyllin.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.danilsibgatyllin.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
