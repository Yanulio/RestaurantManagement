package com.yanulio.restaurant.models.dish;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepo extends JpaRepository<Dish, Integer> {
    Optional<Dish> findByName(String name);
    boolean existsByName(String name);

    @Override
    void deleteById(Integer integer);
    Page<Dish> findAllByQuantityGreaterThan(Integer quantity, Pageable pageable);
}
