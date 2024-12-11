package com.moises.springboot.jwt.repositories;

import java.util.Optional;

import com.moises.springboot.jwt.models.entities.User;
import com.moises.springboot.jwt.models.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de datos para la entidad User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
