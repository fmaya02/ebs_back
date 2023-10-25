package com.elbuensabor.elbuensabor.services;
import com.elbuensabor.elbuensabor.dtos.DTOMovimientosMonetarios;
import com.elbuensabor.elbuensabor.entities.Comprobante;
import com.elbuensabor.elbuensabor.entities.Factura;
import com.elbuensabor.elbuensabor.entities.Pedido;
import com.elbuensabor.elbuensabor.enums.FormaPago;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface FacturaService extends BaseService<Factura,Long> {
Page<Factura> searchFacturaPedido (Long pedido_id, Pageable pageable) throws  Exception;
public Factura createFactura (Pedido pedido, FormaPago formaPago, int nro1 ) throws Exception;

public DTOMovimientosMonetarios getFacturasByFecha(String fechaDesde, String fechaHasta) throws Exception;

}