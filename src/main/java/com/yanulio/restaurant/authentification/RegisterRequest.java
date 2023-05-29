package com.yanulio.restaurant.authentification;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Username is required, it should not be blank.")
    @Size(min = 3, message = "Username must be at least 3 characters long")
    private String username;
    @NotBlank(message = "Email is required, it should not be blank.")
    @Email(message = "Invalid email format.")
    private String email;
    @NotBlank(message = "Password is required, it should not be blank.")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;
}
