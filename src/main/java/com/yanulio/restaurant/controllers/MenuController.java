package com.yanulio.restaurant.controllers;

import com.yanulio.restaurant.models.dish.Dish;
import com.yanulio.restaurant.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {
    private final DishService dishService;
    @GetMapping("/menu-items")
    public ResponseEntity<Page<Dish>> getMenuItemsWithQuantity(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        Page<Dish> menuItems = dishService.getAllMenuItemsWithQuantity(page, size);
        return ResponseEntity.ok(menuItems);
    }
}
