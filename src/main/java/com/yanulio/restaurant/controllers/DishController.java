package com.yanulio.restaurant.controllers;

import com.yanulio.restaurant.models.dish.Dish;
import com.yanulio.restaurant.models.dish.DishRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yanulio.restaurant.services.DishService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @PostMapping("/add-dish")
    public ResponseEntity<Dish> createDish(@Valid @RequestBody DishRequest dishRequest) {
        Dish createdDish = dishService.createDish(dishRequest);
        return ResponseEntity.ok(createdDish);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Dish> getDishByName(@PathVariable String name) {
        Dish dish = dishService.getDishByName(name);
        return ResponseEntity.ok(dish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDish(@Size(min = 1, message = "You can't have the id be less than 1.")
                                                 @PathVariable Integer id) {
        dishService.deleteDish(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted the dish.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@Size(min = 1, message = "You can't have the id be less than 1.")
                                               @PathVariable Integer id, @Valid @RequestBody DishRequest dishRequest) {
        Dish updatedDish = dishService.updateDish(id, dishRequest);
        return ResponseEntity.ok(updatedDish);
    }
}
