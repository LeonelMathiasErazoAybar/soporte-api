package com.soporte.dto;

// DTO de salida: lo que devuelve la API (con id)
public record TecnicoResponseDTO(Long id, String nombre, String especialidad) {}
