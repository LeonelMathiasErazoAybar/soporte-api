package com.soporte.controller;

import com.soporte.dto.TecnicoRequestDTO;
import com.soporte.dto.TecnicoResponseDTO;
import com.soporte.service.TecnicoService;
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
@RequestMapping("/api/tecnicos")
@RequiredArgsConstructor
public class TecnicoController {

    private final TecnicoService service;

    @GetMapping
    @Operation(summary = "Listar tecnicos")
    public ResponseEntity<List<TecnicoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tecnico por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tecnico encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe")
    })
    public ResponseEntity<TecnicoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Filtrar tecnicos por la inicial del nombre")
    public ResponseEntity<List<TecnicoResponseDTO>> buscarPorInicial(@RequestParam String inicio) {
        return ResponseEntity.ok(service.buscarPorInicial(inicio));
    }

    @PostMapping
    @Operation(summary = "Registrar tecnico")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tecnico registrado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    public ResponseEntity<TecnicoResponseDTO> registrar(@Valid @RequestBody TecnicoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tecnico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tecnico actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "No existe")
    })
    public ResponseEntity<TecnicoResponseDTO> actualizar(@PathVariable Long id,
                                                         @Valid @RequestBody TecnicoRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tecnico")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tecnico eliminado"),
            @ApiResponse(responseCode = "404", description = "No existe"),
            @ApiResponse(responseCode = "409", description = "Tiene solicitudes asociadas")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
