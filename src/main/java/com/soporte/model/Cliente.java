package com.soporte.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Entity: esta clase se convierte en una tabla "cliente" en MySQL
@Entity
@Getter @Setter                // Lombok crea los getters y setters
@NoArgsConstructor             // constructor vacio (JPA lo necesita)
@AllArgsConstructor            // constructor con todos los campos
public class Cliente {

    @Id                                                   // clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // MySQL genera el ID (autoincremental)
    private Long id;

    private String nombre;
    private String correo;
}
