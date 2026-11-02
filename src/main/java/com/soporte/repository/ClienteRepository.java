package com.soporte.repository;

import com.soporte.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// JpaRepository ya trae: findAll, findById, save, delete... sin escribir codigo
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Metodo query pedido por el profesor: busca clientes cuyo nombre empieza con "inicio"
    // JPQL: se escribe con el nombre de la CLASE (Cliente), no de la tabla
    @Query("SELECT c FROM Cliente c WHERE c.nombre LIKE CONCAT(:inicio, '%')")
    List<Cliente> buscarPorInicial(@Param("inicio") String inicio);
}
