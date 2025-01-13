package com.alura.forohub.user;

import jakarta.validation.constraints.NotNull;

// may be empty because the update data is optional
public record UserUpdateData(@NotNull Long id, String password, String email, Profile profile) {}
