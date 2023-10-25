package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.dto.DTOCliente;
import com.elbuensabor.elbuensabor.dto.DTOEmpleado;
import com.elbuensabor.elbuensabor.entities.Persona;
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

    @GetMapping("/getAllClientes")
    public ResponseEntity<?> getAllClientes (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getAllClientes());
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }

    @GetMapping("/getCliente")
    public ResponseEntity<?> getCliente (@RequestParam Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getCliente(id));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }

    @PatchMapping("/patchCliente")
    public ResponseEntity<?> updateCliente (@RequestBody DTOCliente dtoCliente, @RequestParam Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.updateCliente(dtoCliente, id));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }

    @GetMapping("/getAllEmpleado")
    public ResponseEntity<?> getAllEmpleados (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getAllEmpleados());
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }

    @GetMapping("/getEmpleado")
    public ResponseEntity<?> getEmpleado (@RequestParam Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getEmpleado(id));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }

    @PatchMapping("/patchEmpleado")
    public ResponseEntity<?> updateEmpleado (@RequestBody DTOEmpleado dtoEmpleado, @RequestParam Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.updateEmpleado(dtoEmpleado, id));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }
}
