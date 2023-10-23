package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.Pedido;
import com.elbuensabor.elbuensabor.services.PedidoServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "ebs/pedidos")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoServiceImpl> {

    @PostMapping("/confirmarPedido")
    public ResponseEntity<?> save(@RequestBody Pedido entity){
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
}
