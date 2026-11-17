package com.example.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record OrderCreateRequest(
        @NotBlank String productName,
        @Min(1) int quantity
) {
}
