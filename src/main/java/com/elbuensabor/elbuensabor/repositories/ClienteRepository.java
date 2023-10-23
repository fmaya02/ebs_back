package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Persona;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends BaseRepository<Persona, Long> {
}
