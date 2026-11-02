package com.soporte.dto;

// DTO de SALIDA: lo que la API devuelve (incluye el id generado)
public record ClienteResponseDTO(Long id, String nombre, String correo) {}
