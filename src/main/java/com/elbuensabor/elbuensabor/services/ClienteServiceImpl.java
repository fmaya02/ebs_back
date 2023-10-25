package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Pedido;
import com.elbuensabor.elbuensabor.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elbuensabor.elbuensabor.entities.Cliente;
import com.elbuensabor.elbuensabor.repositories.ClienteRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Date;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    private ClienteService clienteService;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteService clienteService) {
        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
    }

    @Override
    public Cliente obtenerClientePorId(Long clienteId) {
        return clienteRepository.findById(clienteId).orElse(null);
    }
    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
        // Realiza la actualización del cliente en el servicio
        cliente = clienteService.actualizarCliente(cliente);
        // Devuelve la respuesta
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    public Cliente actualizarCliente(Cliente cliente) {
        // Agrega validaciones
        return clienteRepository.save(cliente);
    }

    //pedidosclientes
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