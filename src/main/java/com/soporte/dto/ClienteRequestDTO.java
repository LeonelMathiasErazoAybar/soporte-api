package com.soporte.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// DTO de ENTRADA: lo que el cliente de la API nos envia en el JSON.
// Aqui van las validaciones (se activan con @Valid en el controlador).
public record ClienteRequestDTO(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "El correo es obligatorio") @Email(message = "Correo invalido") String correo
) {}
