package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Factura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends BaseRepository<Factura, Long> {
    @Query(value = "SELECT f FROM Factura WHERE f.fechaFacturacion LIKE %:fechaFacturacion%")
    Page<Factura> searchFacturaFecha(@Param("fechaFacturacion")String fechaFactiracion, Pageable pageable);
    @Query(value = "SELECT f FROM Factura WHERE f.mpMerchantOrderId LIKE %:mpMerchantOrderId%")
    Page<Factura> searchmpMerchantOrderId(@Param("mpMerchantOrderId")Long mpMerchantOrderId, Pageable pageable);

}
