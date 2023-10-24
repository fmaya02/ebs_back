package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;
import java.util.Date;
import com.elbuensabor.elbuensabor.entities.Pedido;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/pedidos/ordenados/{criterio}")
    public List<Pedido> getPedidosOrdenados(@PathVariable String criterio) {
        return clienteService.getPedidosOrdenadosPorCriterio(criterio);
    }

    @GetMapping("/pedidos/intervalo-fechas")
    public List<Pedido> getPedidosEnIntervaloDeFechas(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFin) {
        return clienteService.getPedidosEnIntervaloDeFechas(fechaInicio, fechaFin);
    }
}