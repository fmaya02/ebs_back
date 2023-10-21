package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Factura;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FacturaServiceImpl extends BaseServiceImpl<Factura, Long> implements FacturaService {
    @Autowired
    FacturaRepository facturaRepository;

    public FacturaServiceImpl(BaseRepository<Factura, Long> baseRepository, FacturaRepository facturaRepository) {
        super(baseRepository);
        this.facturaRepository = facturaRepository;
    }
  /*  @Override
    public Page<Factura> searchFacturaFecha(Date fechaFacturacion, Pageable pageable) throws Exception {
        try {
            Page<Factura> facturas = facturaRepository.searchFacturaFecha(String.valueOf(fechaFacturacion), pageable);
            return facturas;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public Page<Factura> searchFacturampMerchantOrderId(Long mpMerchantOrderId, Pageable pageable) throws Exception {
        try {
            Page<Factura> facturas = facturaRepository.searchFacturaFecha(String.valueOf(mpMerchantOrderId), pageable);
            return facturas;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }*/
}
