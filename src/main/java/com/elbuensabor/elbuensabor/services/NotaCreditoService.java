package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.DetallePedido;
import com.elbuensabor.elbuensabor.entities.Factura;
import com.elbuensabor.elbuensabor.entities.NotaCredito;

import java.math.BigDecimal;

public interface NotaCreditoService extends BaseService<NotaCredito,Long> {
    public NotaCredito createNotaCredito(Factura factura,int nro2)throws Exception;
}
