package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Factura;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends BaseRepository<Factura, Long> {
}
