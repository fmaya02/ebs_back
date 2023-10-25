package com.elbuensabor.elbuensabor.services;
import com.elbuensabor.elbuensabor.dtos.DTOMovimientosMonetarios;
import com.elbuensabor.elbuensabor.entities.Factura;
import com.elbuensabor.elbuensabor.entities.Pedido;
import com.elbuensabor.elbuensabor.enums.FormaPago;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FacturaService extends BaseService<Factura,Long> {
public Page<Factura> searchFacturaPedido (Long pedidoId, Pageable pageable) throws  Exception;
public Factura createFactura (Pedido pedido, FormaPago formaPago, int nro1 ) throws Exception;

public DTOMovimientosMonetarios getFacturasByFecha(String fechaDesde, String fechaHasta) throws Exception;

}