package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.Pedido;
import com.elbuensabor.elbuensabor.enums.EstadoPedido;
import com.elbuensabor.elbuensabor.services.PedidoServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/ebs/pedidos")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoServiceImpl> {

    @PostMapping("/confirmarPedido")
    public ResponseEntity<?> confirmPedido(@RequestBody Pedido entity){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.confirmPedido(entity));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("/getByCliente/{idCliente}")
    public ResponseEntity<?> getAllByCliente(@PathVariable Long idCliente, Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getAllByCliente(idCliente, pageable));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("/getByEstado/{estadoPedido}")
    public ResponseEntity<?> getPedidosByEstado(@PathVariable EstadoPedido estadoPedido, Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getPedidosByEstadoAndFecha(estadoPedido, pageable));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("/getActuales")
    public ResponseEntity<?> getPedidosActuales(Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getPedidosActuales(pageable));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
        }
    }

    @PostMapping("/updateEstado/{idPedido}/{estadoPedido}")
    public ResponseEntity<?> updateEstado(@PathVariable EstadoPedido estadoPedido, @PathVariable Long idPedido){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.updateEstado(estadoPedido, idPedido));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("/getByNumero/{nroPedido}")
    public ResponseEntity<?> searchPedidoByNumero(@PathVariable Long nroPedido){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchPedidoByNumero(nroPedido));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
        }
    }
}
