package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.dtos.DTOClienteRanking;
import com.elbuensabor.elbuensabor.entities.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {

    @Query(value = "SELECT * FROM persona p WHERE p.email LIKE :email",
            nativeQuery = true)
    List<Persona> findPersonaByEmail(@Param("email") String email);

    @Query(value = "SELECT P.* FROM PERSONA P INNER JOIN USUARIO U ON P.USUARIO_ID = U.Id WHERE U.ROL = 'CLIENTE'",
            nativeQuery = true)
    List<Persona> getAllClientes();

    @Query(value = "SELECT P.* FROM PERSONA P INNER JOIN USUARIO U ON P.USUARIO_ID = U.Id WHERE U.ROL != 'CLIENTE'",
            nativeQuery = true)
    List<Persona> getAllEmpleados();
}
