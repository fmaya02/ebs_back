package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dtos.DTOArticulosMasVendidos;
import com.elbuensabor.elbuensabor.entities.DetallePedido;

import java.util.Date;
import java.util.List;

public interface DetallePedidoService extends BaseService<DetallePedido, Long> {

    public List<DTOArticulosMasVendidos> findMostSold(int page, int size, Date date1, Date date2) throws Exception;

}
