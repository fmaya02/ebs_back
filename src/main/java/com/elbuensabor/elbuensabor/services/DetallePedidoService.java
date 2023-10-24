package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dtos.DTOArticulosMasVendidos;
import com.elbuensabor.elbuensabor.entities.DetallePedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface DetallePedidoService extends BaseService<DetallePedido, Long> {

    public Page<DTOArticulosMasVendidos> findMostSold(Pageable pageable, Date date1, Date date2) throws Exception;

}
