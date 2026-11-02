-- Base de datos del proyecto Soporte (MySQL / MariaDB)
-- Ejecutar completo en MySQL Workbench (primer rayo amarillo).
-- OJO: borra y vuelve a crear la base. Ejecutar solo al instalar.

DROP DATABASE IF EXISTS soporte_db;
CREATE DATABASE soporte_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE soporte_db;

CREATE TABLE cliente (
    id     BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255),
    correo VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE tecnico (
    id           BIGINT NOT NULL AUTO_INCREMENT,
    nombre       VARCHAR(255),
    especialidad VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE solicitud (
    id          BIGINT NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(255),
    estado      VARCHAR(255),
    cliente_id  BIGINT NOT NULL,
    tecnico_id  BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_solicitud_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id),
    CONSTRAINT fk_solicitud_tecnico FOREIGN KEY (tecnico_id) REFERENCES tecnico (id)
);

-- Datos de prueba
INSERT INTO cliente (nombre, correo) VALUES
    ('Ana Torres',   'ana.torres@correo.com'),
    ('Carlos Ruiz',  'carlos.ruiz@correo.com'),
    ('Alberto Diaz', 'alberto.diaz@correo.com');

INSERT INTO tecnico (nombre, especialidad) VALUES
    ('Luis Paredes', 'Redes'),
    ('Marta Salas',  'Software');

INSERT INTO solicitud (descripcion, estado, cliente_id, tecnico_id) VALUES
    ('No hay internet en la oficina', 'PENDIENTE',  1, 1),
    ('Error al abrir el sistema',     'EN_PROCESO', 2, 2),
    ('Cambio de contrasena',          'ATENDIDA',   3, 2);

SELECT * FROM solicitud;
