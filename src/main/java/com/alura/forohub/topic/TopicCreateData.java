package com.alura.forohub.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicCreateData(
    @NotBlank String title, @NotBlank String message, @NotNull Long courseId) {}
