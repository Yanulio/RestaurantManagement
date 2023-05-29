package com.yanulio.restaurant.models.dish;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishRequest {
    @NotBlank(message = "Name is required, it should not be blank.")
    private String name;
    @NotBlank(message = "Description is required, it should not be blank.")
    private String description;
    @NotNull(message = "Price is required, it should not be blank.")
    private float price;
    @NotNull(message = "Quantity is required, it should not be blank.")
    private int quantity;
}
