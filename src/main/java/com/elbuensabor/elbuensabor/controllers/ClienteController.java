package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.services.ClienteService;
import com.elbuensabor.elbuensabor.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;
import java.util.Date;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import com.elbuensabor.elbuensabor.entities.Pedido;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> actualizarCliente(
            @PathVariable Long clienteId,
            @RequestBody Cliente clienteActualizado) {

        Cliente cliente = clienteService.obtenerClientePorId(clienteId);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        // Realiza la actualización de los datos
        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setApellido(clienteActualizado.getApellido());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setEmail(clienteActualizado.getEmail());

        return ResponseEntity.ok(clienteActualizado);
    }

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