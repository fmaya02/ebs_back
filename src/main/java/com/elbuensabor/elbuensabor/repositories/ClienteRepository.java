package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {
}
