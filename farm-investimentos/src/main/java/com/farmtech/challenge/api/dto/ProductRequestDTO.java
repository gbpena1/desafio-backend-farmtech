package com.farmtech.challenge.api.dto;

import com.farmtech.challenge.domain.ProductStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
        String name,

        @NotNull(message = "O status é obrigatório")
        ProductStatus status,

        @NotNull(message = "Os dados do fazendeiro são obrigatórios")
        @Valid // Diz pro Spring: "Valide também o que está dentro deste objeto"
        FarmerDTO farmer
) {}
