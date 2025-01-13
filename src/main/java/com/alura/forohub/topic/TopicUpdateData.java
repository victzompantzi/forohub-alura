package com.alura.forohub.topic;

import jakarta.validation.constraints.NotBlank;

public record TopicUpdateData(@NotBlank String title, @NotBlank String message) {}
