package com.example.alura.forohub.domain.user;

import jakarta.validation.constraints.NotNull;

public record UserUpdateData(@NotNull Long id, String password, String email, Profiles profile) {
}
