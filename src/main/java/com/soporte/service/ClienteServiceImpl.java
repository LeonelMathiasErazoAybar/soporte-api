package com.soporte.service;

import com.soporte.dto.ClienteRequestDTO;
import com.soporte.dto.ClienteResponseDTO;
import com.soporte.mapper.ClienteMapper;
import com.soporte.model.Cliente;
import com.soporte.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// @Service: aqui vive la logica de negocio.
// @RequiredArgsConstructor: Lombok crea el constructor que Spring usa para
// inyectar el repository y el mapper (inyeccion de dependencias).
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    @Override
    public List<ClienteResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Override
    public ClienteResponseDTO buscarPorId(Long id) {
        return mapper.toDTO(obtener(id));
    }

    @Override
    public List<ClienteResponseDTO> buscarPorInicial(String inicio) {
        return repository.buscarPorInicial(inicio).stream().map(mapper::toDTO).toList();
    }

    @Override
    public ClienteResponseDTO registrar(ClienteRequestDTO dto) {
        Cliente guardado = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(guardado);
    }

    @Override
    public ClienteResponseDTO actualizar(Long id, ClienteRequestDTO dto) {
        Cliente c = obtener(id);       // si no existe, lanza 404
        c.setNombre(dto.nombre());
        c.setCorreo(dto.correo());
        return mapper.toDTO(repository.save(c));
    }

    @Override
    public void eliminar(Long id) {
        Cliente c = obtener(id);
        try {
            repository.delete(c);
        } catch (DataIntegrityViolationException e) {
            // si el cliente tiene solicitudes, la base no deja borrarlo
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "No se puede eliminar: el cliente tiene solicitudes asociadas");
        }
    }

    // Metodo auxiliar: busca por ID o responde 404 si no existe
    private Cliente obtener(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
    }
}
