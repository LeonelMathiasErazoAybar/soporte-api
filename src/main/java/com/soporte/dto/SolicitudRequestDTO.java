package com.soporte.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// DTO de entrada: en vez de mandar el cliente completo, se manda solo su id
public record SolicitudRequestDTO(
        @NotBlank(message = "La descripcion es obligatoria") String descripcion,
        @NotBlank(message = "El estado es obligatorio") String estado,
        @NotNull(message = "El clienteId es obligatorio") Long clienteId,
        @NotNull(message = "El tecnicoId es obligatorio") Long tecnicoId
) {}
