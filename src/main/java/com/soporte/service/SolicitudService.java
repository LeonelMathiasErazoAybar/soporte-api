package com.soporte.service;

import com.soporte.dto.SolicitudRequestDTO;
import com.soporte.dto.SolicitudResponseDTO;

import java.util.List;

public interface SolicitudService {
    List<SolicitudResponseDTO> listar();
    SolicitudResponseDTO buscarPorId(Long id);
    List<SolicitudResponseDTO> buscarPorEstado(String estado);
    SolicitudResponseDTO registrar(SolicitudRequestDTO dto);
    SolicitudResponseDTO actualizar(Long id, SolicitudRequestDTO dto);
    void eliminar(Long id);
}
