package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Domicilio;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.DomicilioRepository;
@Repository
public interface DomicilioRepository extends BaseRepository<Domicilio, Long> {
    List<Domicilio> findByNumero(Integer numero);
}
