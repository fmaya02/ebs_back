package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.entities.Persona;
import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.enums.Rol;
import com.elbuensabor.elbuensabor.services.PersonaServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "ebs/personas")
public class PersonaControllerImpl extends BaseControllerImpl<Persona, PersonaServiceImpl>{

    @GetMapping("/searchPersonaApellido")
    public ResponseEntity<?> searchPersonaApellido (@RequestParam String apellido, Pageable pageable){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchPersonaApellido(apellido, pageable));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }

    @GetMapping("/searchPersonaNombre")
    public ResponseEntity<?> searchPersonaNombre (@RequestParam String nombre, Pageable pageable){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchPersonaNombre(nombre, pageable));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }

    @PostMapping("/signUpCliente")
    public ResponseEntity<?> signUpCliente (@RequestBody Persona persona, String pswd1, String pswd2){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.signUp(persona,Rol.CLIENTE,pswd1,pswd2));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }

    @PostMapping("/signUpEmpleado")
    public ResponseEntity<?> signUpEmpleado (@RequestBody Persona persona, Rol rol, String pswd1, String pswd2){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.signUp(persona,rol,pswd1,pswd2));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn (@RequestParam String email, @RequestParam String password){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.signIn(email, password));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }
}
