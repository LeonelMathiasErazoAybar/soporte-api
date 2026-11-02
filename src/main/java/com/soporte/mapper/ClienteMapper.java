package com.soporte.mapper;

import com.soporte.dto.ClienteRequestDTO;
import com.soporte.dto.ClienteResponseDTO;
import com.soporte.model.Cliente;
import org.springframework.stereotype.Component;

// Convierte DTO <-> Entidad, para no exponer la entidad directamente
@Component
public class ClienteMapper {

    // DTO de entrada -> entidad (para guardar en la base)
    public Cliente toEntity(ClienteRequestDTO dto) {
        Cliente c = new Cliente();
        c.setNombre(dto.nombre());
        c.setCorreo(dto.correo());
        return c;
    }

    // Entidad -> DTO de salida (para responder)
    public ClienteResponseDTO toDTO(Cliente c) {
        return new ClienteResponseDTO(c.getId(), c.getNombre(), c.getCorreo());
    }
}
