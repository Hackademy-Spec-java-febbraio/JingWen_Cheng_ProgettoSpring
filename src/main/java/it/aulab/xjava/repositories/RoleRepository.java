package it.aulab.xjava.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import it.aulab.xjava.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    Optional<Role> findByname(String defaultRole);
    
}
