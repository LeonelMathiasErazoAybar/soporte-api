package com.soporte.service;

import com.soporte.dto.TecnicoRequestDTO;
import com.soporte.dto.TecnicoResponseDTO;
import com.soporte.mapper.TecnicoMapper;
import com.soporte.model.Tecnico;
import com.soporte.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TecnicoServiceImpl implements TecnicoService {

    private final TecnicoRepository repository;
    private final TecnicoMapper mapper;

    @Override
    public List<TecnicoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Override
    public TecnicoResponseDTO buscarPorId(Long id) {
        return mapper.toDTO(obtener(id));
    }

    @Override
    public List<TecnicoResponseDTO> buscarPorInicial(String inicio) {
        return repository.buscarPorInicial(inicio).stream().map(mapper::toDTO).toList();
    }

    @Override
    public TecnicoResponseDTO registrar(TecnicoRequestDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public TecnicoResponseDTO actualizar(Long id, TecnicoRequestDTO dto) {
        Tecnico t = obtener(id);
        t.setNombre(dto.nombre());
        t.setEspecialidad(dto.especialidad());
        return mapper.toDTO(repository.save(t));
    }

    @Override
    public void eliminar(Long id) {
        Tecnico t = obtener(id);
        try {
            repository.delete(t);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "No se puede eliminar: el tecnico tiene solicitudes asociadas");
        }
    }

    private Tecnico obtener(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tecnico no encontrado"));
    }
}
