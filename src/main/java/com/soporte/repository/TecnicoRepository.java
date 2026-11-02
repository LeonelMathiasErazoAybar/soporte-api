package com.soporte.repository;

import com.soporte.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

    // Metodo query: tecnicos cuyo nombre empieza con la letra recibida
    @Query("SELECT t FROM Tecnico t WHERE t.nombre LIKE CONCAT(:inicio, '%')")
    List<Tecnico> buscarPorInicial(@Param("inicio") String inicio);
}
