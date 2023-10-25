package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Factura;
import com.elbuensabor.elbuensabor.entities.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends BaseRepository<Factura, Long> {

    @Query(value = "SELECT * FROM factura f WHERE pedido LIKE %:filtro%",
    nativeQuery = true)
    Page<Factura> searchFacturaPedido (@Param("filtro") Long filtro, Pageable pageable);
}
