package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Localidad;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface LocalidadRepository extends BaseRepository<Localidad, Long> {

}
