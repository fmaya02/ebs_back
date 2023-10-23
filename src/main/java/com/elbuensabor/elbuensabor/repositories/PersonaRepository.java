package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {

    @Query(value = "SELECT * FROM persona p WHERE p.apellido LIKE %:apellido%",
    nativeQuery = true)
    Page<Persona> searchPersonaApellido(@Param("apellido") String apellido, Pageable pageable);

    @Query(value = "SELECT * FROM persona p WHERE p.nombre LIKE %:nombre%",
            nativeQuery = true)
    Page<Persona> searchPersonaNombre(@Param("nombre") String nombre, Pageable pageable);
}
