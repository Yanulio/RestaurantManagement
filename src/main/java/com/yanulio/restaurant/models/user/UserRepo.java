package com.yanulio.restaurant.models.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
