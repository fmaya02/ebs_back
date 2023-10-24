package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.Articulo;
import com.elbuensabor.elbuensabor.services.ArticuloServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/ebs/articulo")
public class ArticuloController extends BaseControllerImpl<Articulo, ArticuloServiceImpl> {

    @GetMapping("/findByName")
    public ResponseEntity<?> findByName(@RequestParam String name, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.servicio.findByName(pageable, name));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/findMostSold")
    public ResponseEntity<?> findMostSold(@RequestParam Date date1, Date date2, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.servicio.findMostSold(date1, date2, pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
