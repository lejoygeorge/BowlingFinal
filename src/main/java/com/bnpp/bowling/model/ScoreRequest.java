package com.bnpp.bowling.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ScoreRequest(
        @NotBlank(message = "Bowling sequence cannot be null or empty")
        @Pattern(
                regexp = "^[xX/\\-0-9\\s]+$",
                message = "Sequence contains invalid characters. Only digits (0-9), 'X', '/', '-', and spaces are allowed."
        )
        String sequence
) {}