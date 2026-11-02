package com.soporte.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Tabla "solicitud": una solicitud de soporte.
// Relaciones (muchos a uno): muchas solicitudes -> un cliente, muchas solicitudes -> un tecnico
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String estado;

    // @JoinColumn: crea la columna cliente_id (clave foranea) en la tabla solicitud
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;
}
