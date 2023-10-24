package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dto.DTOCliente;
import com.elbuensabor.elbuensabor.entities.Persona;
import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.enums.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonaService extends BaseService<Persona,Long>{

    Page<Persona> searchPersonaApellido(String apellido, Pageable pageable) throws Exception;

    Page<Persona> searchPersonaNombre(String nombre, Pageable pageable) throws Exception;

    Persona signUp (Persona persona, Rol rol, String pswd1, String pswd2) throws Exception;

    List<Persona> findPersonaByEmail (String email) throws Exception;

    String signIn (String email, String password) throws Exception;

    Persona updateCliente (DTOCliente dtoCliente) throws Exception;

    Persona updateEmpleado (Persona persona, Long id, Rol rol) throws Exception;

}
