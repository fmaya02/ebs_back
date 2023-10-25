package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Factura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FacturaRepository extends BaseRepository<Factura, Long> {

    @Query(value="SELECT f FROM Factura f WHERE YEAR(f.fechaComprobante) >= YEAR(:fechaDesde) AND MONTH(f.fechaComprobante) >= MONTH(:fechaDesde) AND DAY(f.fechaComprobante) >= DAY(:fechaDesde)" +
            "AND YEAR(f.fechaComprobante) <= YEAR(:fechaHasta) AND MONTH(f.fechaComprobante) <= MONTH(:fechaHasta) AND DAY(f.fechaComprobante) <= DAY(:fechaHasta)")
    List<Factura> getFacturasByFecha(@Param("fechaDesde")Date fechaDesde, @Param("fechaHasta") Date fechaHasta);

    @Query(value = "SELECT * FROM factura WHERE pedido_id = :pedidoid",
        nativeQuery = true)
    Page<Factura> searchFacturaPedido(@Param("pedidoid") Long pedidoId, Pageable pageable);
}
