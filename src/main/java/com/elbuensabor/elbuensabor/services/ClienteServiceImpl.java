package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Pedido;
import com.elbuensabor.elbuensabor.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> getPedidosOrdenadosPorCriterio(String criterio) {
        switch (criterio) {
            case "fecha":
                return pedidoRepository.findAllByOrderByFechaPedidoDesc();
            case "cantidad":
                return pedidoRepository.findAllByOrderByCantidadDesc();
            case "total":
                return pedidoRepository.findAllByOrderByTotalDesc();
            default:
                return null; // Manejo de criterio desconocido
        }
    }

    @Override
    public List<Pedido> getPedidosEnIntervaloDeFechas(Date fechaInicio, Date fechaFin) {
        return pedidoRepository.findByFechaPedidoBetween(fechaInicio, fechaFin);
    }
}