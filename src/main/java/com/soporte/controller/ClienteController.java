package com.soporte.controller;

import com.soporte.dto.ClienteRequestDTO;
import com.soporte.dto.ClienteResponseDTO;
import com.soporte.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: recibe peticiones HTTP y responde JSON
// @RequestMapping: ruta base siguiendo el estandar /api/entidad
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    @Operation(summary = "Listar clientes")
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());                       // 200
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe")
    })
    public ResponseEntity<ClienteResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // GET con parametro: /api/clientes/buscar?inicio=A  (usa el metodo @Query)
    @GetMapping("/buscar")
    @Operation(summary = "Filtrar clientes por la inicial del nombre")
    public ResponseEntity<List<ClienteResponseDTO>> buscarPorInicial(@RequestParam String inicio) {
        return ResponseEntity.ok(service.buscarPorInicial(inicio));
    }

    @PostMapping
    @Operation(summary = "Registrar cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente registrado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    public ResponseEntity<ClienteResponseDTO> registrar(@Valid @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));   // 201
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente")
    public ResponseEntity<ClienteResponseDTO> actualizar(@PathVariable Long id,
                                                         @Valid @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente")
    @ApiResponse(responseCode = "204", description = "Cliente eliminado")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();                        // 204
    }
}
