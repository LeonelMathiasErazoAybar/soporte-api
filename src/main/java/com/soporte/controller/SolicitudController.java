package com.soporte.controller;

import com.soporte.dto.SolicitudRequestDTO;
import com.soporte.dto.SolicitudResponseDTO;
import com.soporte.service.SolicitudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService service;

    @GetMapping
    @Operation(summary = "Listar solicitudes")
    public ResponseEntity<List<SolicitudResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar solicitud por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
            @ApiResponse(responseCode = "404", description = "No existe")
    })
    public ResponseEntity<SolicitudResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Filtrar solicitudes por estado")
    public ResponseEntity<List<SolicitudResponseDTO>> buscarPorEstado(@RequestParam String estado) {
        return ResponseEntity.ok(service.buscarPorEstado(estado));
    }

    @PostMapping
    @Operation(summary = "Registrar solicitud")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Solicitud registrada"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "Cliente o tecnico no existe")
    })
    public ResponseEntity<SolicitudResponseDTO> registrar(@Valid @RequestBody SolicitudRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar solicitud")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Solicitud actualizada"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "No existe")
    })
    public ResponseEntity<SolicitudResponseDTO> actualizar(@PathVariable Long id,
                                                           @Valid @RequestBody SolicitudRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar solicitud")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Solicitud eliminada"),
            @ApiResponse(responseCode = "404", description = "No existe")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
