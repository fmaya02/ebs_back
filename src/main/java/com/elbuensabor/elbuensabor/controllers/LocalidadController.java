package com.elbuensabor.elbuensabor.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.elbuensabor.elbuensabor.services.LocalidadService;
import com.elbuensabor.elbuensabor.entities.Localidad;

@RestController
@RequestMapping("/ebs/localidades")
public class LocalidadController {

    private final LocalidadService localidadService;

    @Autowired
    public LocalidadController(LocalidadService localidadService) {
        this.localidadService = localidadService;
    }

    @GetMapping
    public ResponseEntity<List<Localidad>> getAllLocalidades() {
        List<Localidad> localidades = localidadService.getAllLocalidades();
        return new ResponseEntity<>(localidades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Localidad> getLocalidadById(@PathVariable Long id) {
        Localidad localidad = localidadService.getLocalidadById(id);
        if (localidad != null) {
            return new ResponseEntity<>(localidad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Localidad> createLocalidad(@RequestBody Localidad localidad) {
        Localidad createdLocalidad = localidadService.createLocalidad(localidad);
        return new ResponseEntity<>(createdLocalidad, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Localidad> updateLocalidad(@PathVariable Long id, @RequestBody Localidad localidad) {
        Localidad updatedLocalidad = localidadService.updateLocalidad(id, localidad);
        if (updatedLocalidad != null) {
            return new ResponseEntity<>(updatedLocalidad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocalidad(@PathVariable Long id) {
        boolean deleted = localidadService.deleteLocalidad(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
