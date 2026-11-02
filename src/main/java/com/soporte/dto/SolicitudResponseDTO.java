package com.soporte.dto;

// DTO de salida: incluye el nombre del cliente y del tecnico para que se entienda mejor
public record SolicitudResponseDTO(
        Long id,
        String descripcion,
        String estado,
        Long clienteId,
        String clienteNombre,
        Long tecnicoId,
        String tecnicoNombre
) {}
