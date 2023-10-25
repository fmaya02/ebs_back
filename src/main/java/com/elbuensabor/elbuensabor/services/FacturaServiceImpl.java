package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dtos.DTOMovimientosMonetarios;
import com.elbuensabor.elbuensabor.entities.Comprobante;
import com.elbuensabor.elbuensabor.entities.DetalleComprobante;
import com.elbuensabor.elbuensabor.entities.Factura;
import com.elbuensabor.elbuensabor.entities.Pedido;
import com.elbuensabor.elbuensabor.enums.FormaPago;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.FacturaRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import org.springframework.aot.generate.GeneratedTypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FacturaServiceImpl extends BaseServiceImpl<Factura, Long> implements FacturaService {
    @Autowired
    FacturaRepository facturaRepository;

    public FacturaServiceImpl(BaseRepository<Factura, Long> baseRepository, FacturaRepository facturaRepository) {
        super(baseRepository);
        this.facturaRepository = facturaRepository;
    }
@Override
public Page<Factura> searchFacturaPedido (Long pedido_id, Pageable pageable) throws  Exception {
    try {
        return facturaRepository.searchFacturaPedido(pedido_id, pageable);
    } catch (Exception e) {
        throw new Exception((e.getMessage()));
    }
}

    @Override
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
        facturaRepository.save(newFactura);
        return newFactura;
    }catch (Exception e){
        throw new Exception(e.getMessage());
    }
    }

    @Override
    public DTOMovimientosMonetarios getFacturasByFecha(String fechaDesde, String fechaHasta) throws Exception {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaDesdeFiltro=sdf.parse(fechaDesde);
            Date fechaHastaFiltro=sdf.parse(fechaHasta);
            List<Factura> facturas= facturaRepository.getFacturasByFecha(fechaDesdeFiltro, fechaHastaFiltro);
            BigDecimal ingresos=new BigDecimal(0);
            BigDecimal costos=new BigDecimal(0);
            for(Factura factura : facturas){
                ingresos=ingresos.add(factura.getPedido().getTotal());
                costos=costos.add((factura.getPedido().getTotalCosto()));
            }
            BigDecimal ganancias=ingresos.subtract(costos);
            DTOMovimientosMonetarios movimientosMonetarios=DTOMovimientosMonetarios.builder()
                    .ingresos(ingresos)
                    .costos(costos)
                    .ganancias(ganancias)
                    .build();
            return movimientosMonetarios;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

