package com.elbuensabor.elbuensabor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.elbuensabor.elbuensabor.entities.Domicilio;
import com.elbuensabor.elbuensabor.services.DomicilioService;

@RestController
@RequestMapping("/ebs/domicilios")
public class DomicilioController {

    private final DomicilioService domicilioService;

    @Autowired
    public DomicilioController(DomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    @GetMapping
    public ResponseEntity<List<Domicilio>> getAllDomicilios() {
        List<Domicilio> domicilios = domicilioService.getAllDomicilios();
        return new ResponseEntity<>(domicilios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Domicilio> getDomicilioById(@PathVariable Long id) {
        Domicilio domicilio = domicilioService.getDomicilioById(id);
        if (domicilio != null) {
            return new ResponseEntity<>(domicilio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Domicilio> createDomicilio(@RequestBody Domicilio domicilio) {
        Domicilio createdDomicilio = domicilioService.createDomicilio(domicilio);
        return new ResponseEntity<>(createdDomicilio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Domicilio> updateDomicilio(@PathVariable Long id, @RequestBody Domicilio domicilio) {
        Domicilio updatedDomicilio = domicilioService.updateDomicilio(id, domicilio);
        if (updatedDomicilio != null) {
            return new ResponseEntity<>(updatedDomicilio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDomicilio(@PathVariable Long id) {
        boolean deleted = domicilioService.deleteDomicilio(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
