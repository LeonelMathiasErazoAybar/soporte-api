package com.soporte.dto;

import jakarta.validation.constraints.NotBlank;

// DTO de entrada: lo que se manda al crear o actualizar un tecnico (sin id)
public record TecnicoRequestDTO(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "La especialidad es obligatoria") String especialidad
) {}
