package com.farmtech.challenge.api.dto;

import com.farmtech.challenge.domain.ProductStatus;
import java.time.LocalDateTime;

public record ProductResponseDTO(
        Long id,
        String name,
        ProductStatus status,
        FarmerDTO farmer,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}