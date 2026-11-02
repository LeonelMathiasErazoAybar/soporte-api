package com.soporte.mapper;

import com.soporte.dto.SolicitudRequestDTO;
import com.soporte.dto.SolicitudResponseDTO;
import com.soporte.model.Cliente;
import com.soporte.model.Solicitud;
import com.soporte.model.Tecnico;
import org.springframework.stereotype.Component;

@Component
public class SolicitudMapper {

    // Necesita el cliente y el tecnico ya buscados en la base (los busca el service)
    public Solicitud toEntity(SolicitudRequestDTO dto, Cliente cliente, Tecnico tecnico) {
        Solicitud s = new Solicitud();
        s.setDescripcion(dto.descripcion());
        s.setEstado(dto.estado());
        s.setCliente(cliente);
        s.setTecnico(tecnico);
        return s;
    }

    public SolicitudResponseDTO toDTO(Solicitud s) {
        return new SolicitudResponseDTO(
                s.getId(),
                s.getDescripcion(),
                s.getEstado(),
                s.getCliente().getId(),
                s.getCliente().getNombre(),
                s.getTecnico().getId(),
                s.getTecnico().getNombre());
    }
}
