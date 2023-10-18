package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.Cliente;
import com.elbuensabor.elbuensabor.services.ClienteServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "ebs/clientes")
public class ClienteControllerImpl extends BaseControllerImpl<Cliente, ClienteServiceImpl>{

    @GetMapping("/searchClienteApellido")
    public ResponseEntity<?> searchClienteApellido (@RequestParam String apellido, Pageable pageable){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchClienteApellido(apellido, pageable));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }

    @GetMapping("/searchClienteNombre")
    public ResponseEntity<?> searchClienteNombre (@RequestParam String nombre, Pageable pageable){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchClienteNombre(nombre, pageable));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }
}
