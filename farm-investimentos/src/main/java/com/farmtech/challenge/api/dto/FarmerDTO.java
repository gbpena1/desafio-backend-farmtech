package com.farmtech.challenge.api.dto;

import jakarta.validation.constraints.NotBlank;

public record FarmerDTO(
        @NotBlank(message = "O documento do fazendeiro é obrigatório")
        String document,

        @NotBlank(message = "O nome da fazenda é obrigatório")
        String farmName,

        @NotBlank(message = "O estado é obrigatório")
        String state
) {}