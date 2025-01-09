package com.example.alura.forohub.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationData(@NotBlank String username, @NotBlank @Email String email,
    @NotBlank String password, @NotNull Profiles profile) {

}
