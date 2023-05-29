package com.yanulio.restaurant.services;

import com.yanulio.restaurant.models.dish.Dish;
import com.yanulio.restaurant.models.dish.DishRepo;
import com.yanulio.restaurant.models.dish.DishRequest;
import com.yanulio.restaurant.exceptions.DataAlreadyExistsException;
import com.yanulio.restaurant.exceptions.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepo repo;
    public Dish createDish(DishRequest dishRequest) {
        if (repo.existsByName(dishRequest.getName())) {
            throw new DataAlreadyExistsException("A dish with this name already exists.");
        }
        var dish = Dish.builder()
                        .name(dishRequest.getName())
                        .description(dishRequest.getDescription())
                        .price(dishRequest.getPrice())
                        .quantity(dishRequest.getQuantity())
                        .build();
        repo.save(dish);
        return dish;
    }

    public Dish getDishByName(String name) {
        Dish dish = repo.findByName(name).orElseThrow(() -> new DataNotFoundException("Dish was not found"));
        return dish;
    }

    public void deleteDish(Integer id) {
        repo.deleteById(id);
    }

    public Dish updateDish(Integer id, DishRequest dishRequest) {
        Dish dishToUpdate = repo.findById(id).orElseThrow(() -> new DataNotFoundException("Dish was not found"));

        dishToUpdate.setName(dishRequest.getName());
        dishToUpdate.setDescription(dishRequest.getDescription());
        dishToUpdate.setPrice(dishRequest.getPrice());
        dishToUpdate.setQuantity(dishRequest.getQuantity());

        return repo.save(dishToUpdate);
    }

    public Page<Dish> getAllMenuItemsWithQuantity(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAllByQuantityGreaterThan(0, pageable);
    }
}
