package com.alura.forohub.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRegistrationData(@NotBlank String name, @NotNull Category category) {}
