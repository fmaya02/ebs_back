package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.services.UsuarioServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "ebs/usuarios")
public class UsuarioControllerImpl extends BaseControllerImpl<Usuario, UsuarioServiceImpl> {

    @GetMapping("/searchUsuarioRol")
    public ResponseEntity<?> searchUsuarioRol(@RequestParam String rol, Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchUsuarioRol(rol, pageable));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }

    @GetMapping("/searchUsuarioUsrnm")
    public ResponseEntity<?> searchUsuarioUsrnm(@RequestParam String username, Pageable pageable){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchUsuarioUsrnm(username, pageable));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }
}
