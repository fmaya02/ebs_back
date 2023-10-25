package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Comprobante;
import com.elbuensabor.elbuensabor.entities.DetalleComprobante;
import com.elbuensabor.elbuensabor.entities.Factura;
import com.elbuensabor.elbuensabor.entities.Pedido;
import com.elbuensabor.elbuensabor.enums.FormaPago;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.FacturaRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.aot.generate.GeneratedTypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class FacturaServiceImpl extends BaseServiceImpl<Factura, Long> implements FacturaService {
    @Autowired
    FacturaRepository facturaRepository;

    public FacturaServiceImpl(BaseRepository<Factura, Long> baseRepository, FacturaRepository facturaRepository) {
        super(baseRepository);
        this.facturaRepository = facturaRepository;
    }
@Override
public Page<Factura> searchFacturaPedido(Long Pedidoid, Pageable pageable) throws  Exception {
    try {
        return facturaRepository.searchFacturaPedido(Pedidoid, pageable);
    } catch (Exception e) {
        throw new Exception((e.getMessage()));
    }
}

    @Override
    @Transactional
public Factura createFactura(Pedido pedido, FormaPago formaPago, int nro1) throws Exception {
    try{
    Factura newFactura = Factura.builder()

            .fechaComprobante(new Date())
            .nro(nro1)
            .total(pedido.getTotal())
            .fechaFacturacion(new Date())
            .formaPago(formaPago)
            .pedido(pedido)
            .build();
        newFactura.setPedido(pedido);
        facturaRepository.save(newFactura);
        return newFactura;

    }catch (Exception e){
        throw new Exception(e.getMessage());
    }

    }

}

