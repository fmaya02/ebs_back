package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.Factura;
import com.elbuensabor.elbuensabor.entities.Pedido;
import com.elbuensabor.elbuensabor.enums.FormaPago;
import com.elbuensabor.elbuensabor.services.FacturaServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/ebs/Factura")
public class FacturaControllerImpl extends BaseControllerImpl<Factura, FacturaServiceImpl> {


@GetMapping("/SearchFacturapedido")
    public ResponseEntity<?> searchFacturaPedido (@RequestParam Long pedidoId, Pageable pageable){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchFacturaPedido(pedidoId, pageable));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }

@PostMapping("/CreateFactura")
    public ResponseEntity<?> createFactura (@RequestBody Pedido pedido, FormaPago formaPago, int nro1){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.createFactura(pedido,formaPago,nro1));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }

    @GetMapping("/MovimientosMonetarios")
    public ResponseEntity<?> getMovimientosMonetarios (@RequestParam String fechaDesde, @RequestParam String fechaHasta){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getFacturasByFecha(fechaDesde, fechaHasta));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }
}
