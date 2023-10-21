package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Factura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface FacturaService extends BaseService<Factura,Long> {
    // Page<Factura> searchFacturaFecha(Date fechaFacturacion, Pageable pageable) throws Exception;
    //Page<Factura> searchFacturampMerchantOrderId(Long mpMerchantOrderId,Pageable pageable) throws Exception;
}