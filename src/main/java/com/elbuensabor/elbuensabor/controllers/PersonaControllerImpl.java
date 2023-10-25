package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.dtos.DTOCliente;
import com.elbuensabor.elbuensabor.dtos.DTOEmpleado;
import com.elbuensabor.elbuensabor.entities.Persona;
import com.elbuensabor.elbuensabor.enums.Rol;
import com.elbuensabor.elbuensabor.services.PersonaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "/ebs/personas")
public class PersonaControllerImpl extends BaseControllerImpl<Persona, PersonaServiceImpl>{


    @GetMapping("/getRankingClientes")
    public ResponseEntity<?> getRankingClientes (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getRankingClientes());
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }



    @PutMapping("/editPersona")
    public ResponseEntity<?> editPersona (@RequestBody Persona persona, @RequestParam Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.update(id, persona));
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
    public ResponseEntity<?> patchCliente (@RequestBody DTOCliente dtoCliente, @RequestParam Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.patchCliente(dtoCliente, id));
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
    public ResponseEntity<?> patchEmpleado (@RequestBody DTOEmpleado dtoEmpleado, @RequestParam Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.patchEmpleado(dtoEmpleado, id));
        }catch (Exception e){
            String strErr = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":"+strErr+"}");
        }
    }
}
