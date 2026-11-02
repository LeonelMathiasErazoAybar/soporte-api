package com.soporte.repository;

import com.soporte.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    // Metodo query: solicitudes de un estado (ej. PENDIENTE, EN_PROCESO, ATENDIDA)
    @Query("SELECT s FROM Solicitud s WHERE s.estado = :estado")
    List<Solicitud> buscarPorEstado(@Param("estado") String estado);
}
