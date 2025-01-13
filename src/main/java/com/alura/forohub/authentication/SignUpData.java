package com.alura.forohub.authentication;

import com.alura.forohub.user.Profile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignUpData(
    @NotBlank String username,
    @NotBlank @Email String email,
    @NotBlank String password,
    @NotNull Profile profile) {}
