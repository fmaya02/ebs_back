package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Pedido;
import java.util.Date;
import java.util.List;

public interface ClienteService {
    List<Pedido> getPedidosOrdenadosPorCriterio(String criterio);
    List<Pedido> getPedidosEnIntervaloDeFechas(Date fechaInicio, Date fechaFin);
}
