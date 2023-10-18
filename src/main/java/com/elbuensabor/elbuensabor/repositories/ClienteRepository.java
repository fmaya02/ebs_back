package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {

    @Query(value = "SELECT c FROM Cliente c WHERE c.apellido LIKE %:apellido%")
    Page<Cliente> searchClienteApellido(@Param("apellido") String apellido, Pageable pageable);

    @Query(value = "SELECT c FROM Cliente c WHERE c.nombre LIKE %:nombre%")
    Page<Cliente> searchClienteNombre(@Param("apellido") String nombre, Pageable pageable);
}
