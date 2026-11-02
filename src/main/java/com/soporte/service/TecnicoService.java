package com.soporte.service;

import com.soporte.dto.TecnicoRequestDTO;
import com.soporte.dto.TecnicoResponseDTO;

import java.util.List;

public interface TecnicoService {
    List<TecnicoResponseDTO> listar();
    TecnicoResponseDTO buscarPorId(Long id);
    List<TecnicoResponseDTO> buscarPorInicial(String inicio);
    TecnicoResponseDTO registrar(TecnicoRequestDTO dto);
    TecnicoResponseDTO actualizar(Long id, TecnicoRequestDTO dto);
    void eliminar(Long id);
}
