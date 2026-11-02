package com.soporte.mapper;

import com.soporte.dto.TecnicoRequestDTO;
import com.soporte.dto.TecnicoResponseDTO;
import com.soporte.model.Tecnico;
import org.springframework.stereotype.Component;

@Component
public class TecnicoMapper {

    public Tecnico toEntity(TecnicoRequestDTO dto) {
        Tecnico t = new Tecnico();
        t.setNombre(dto.nombre());
        t.setEspecialidad(dto.especialidad());
        return t;
    }

    public TecnicoResponseDTO toDTO(Tecnico t) {
        return new TecnicoResponseDTO(t.getId(), t.getNombre(), t.getEspecialidad());
    }
}
