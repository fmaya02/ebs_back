package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.Insumo;
import com.elbuensabor.elbuensabor.services.InsumoServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/ebs/insumo")
public class InsumoController extends BaseControllerImpl<Insumo, InsumoServiceImpl> {

    @GetMapping("/findLowStock")
    public ResponseEntity<?> findLowStock(@RequestParam Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.servicio.findLowStock(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
