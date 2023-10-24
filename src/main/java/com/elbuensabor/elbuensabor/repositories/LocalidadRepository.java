package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Localidad;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface LocalidadRepository extends BaseRepository<Localidad, Long> {
    List<Localidad> findByDenominacion(String denominacion);

    @Query("SELECT l FROM Localidad l WHERE l.fechaBaja IS NULL")
    List<Localidad> findActiveLocalidades();
}
