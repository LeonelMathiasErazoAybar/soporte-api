package com.soporte.service;

import com.soporte.dto.ClienteRequestDTO;
import com.soporte.dto.ClienteResponseDTO;

import java.util.List;

// Interfaz: define QUE se puede hacer. La logica esta en ClienteServiceImpl.
public interface ClienteService {
    List<ClienteResponseDTO> listar();
    ClienteResponseDTO buscarPorId(Long id);
    List<ClienteResponseDTO> buscarPorInicial(String inicio);
    ClienteResponseDTO registrar(ClienteRequestDTO dto);
    ClienteResponseDTO actualizar(Long id, ClienteRequestDTO dto);
    void eliminar(Long id);
}
