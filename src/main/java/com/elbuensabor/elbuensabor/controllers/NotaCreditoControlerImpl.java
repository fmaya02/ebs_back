package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.Factura;
import com.elbuensabor.elbuensabor.entities.NotaCredito;
import com.elbuensabor.elbuensabor.services.NotaCreditoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/ebs/NotaCredito")
public class NotaCreditoControlerImpl extends BaseControllerImpl <NotaCredito, NotaCreditoServiceImpl> {
    @PostMapping("/CreateNotaCredito")
    public ResponseEntity<?> createNotaCredito (@RequestBody Factura factura, int nro2){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.createNotaCredito(factura,nro2));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }
}
