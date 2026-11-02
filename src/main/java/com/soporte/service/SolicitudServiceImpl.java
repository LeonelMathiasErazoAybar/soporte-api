package com.soporte.service;

import com.soporte.dto.SolicitudRequestDTO;
import com.soporte.dto.SolicitudResponseDTO;
import com.soporte.mapper.SolicitudMapper;
import com.soporte.model.Cliente;
import com.soporte.model.Solicitud;
import com.soporte.model.Tecnico;
import com.soporte.repository.ClienteRepository;
import com.soporte.repository.SolicitudRepository;
import com.soporte.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository repository;
    private final ClienteRepository clienteRepository;
    private final TecnicoRepository tecnicoRepository;
    private final SolicitudMapper mapper;

    @Override
    public List<SolicitudResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Override
    public SolicitudResponseDTO buscarPorId(Long id) {
        return mapper.toDTO(obtener(id));
    }

    @Override
    public List<SolicitudResponseDTO> buscarPorEstado(String estado) {
        return repository.buscarPorEstado(estado).stream().map(mapper::toDTO).toList();
    }

    @Override
    public SolicitudResponseDTO registrar(SolicitudRequestDTO dto) {
        Cliente cliente = buscarCliente(dto.clienteId());
        Tecnico tecnico = buscarTecnico(dto.tecnicoId());
        Solicitud guardada = repository.save(mapper.toEntity(dto, cliente, tecnico));
        return mapper.toDTO(guardada);
    }

    @Override
    public SolicitudResponseDTO actualizar(Long id, SolicitudRequestDTO dto) {
        Solicitud s = obtener(id);
        s.setDescripcion(dto.descripcion());
        s.setEstado(dto.estado());
        s.setCliente(buscarCliente(dto.clienteId()));
        s.setTecnico(buscarTecnico(dto.tecnicoId()));
        return mapper.toDTO(repository.save(s));
    }

    @Override
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }

    private Solicitud obtener(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitud no encontrada"));
    }

    private Cliente buscarCliente(Long id) {
        return clienteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
    }

    private Tecnico buscarTecnico(Long id) {
        return tecnicoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tecnico no encontrado"));
    }
}
