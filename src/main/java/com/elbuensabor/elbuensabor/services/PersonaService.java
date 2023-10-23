package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Persona;
import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.enums.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonaService extends BaseService<Persona,Long>{

    Page<Persona> searchPersonaApellido(String apellido, Pageable pageable) throws Exception;

    Page<Persona> searchPersonaNombre(String nombre, Pageable pageable) throws Exception;

    Persona signUp (Persona persona, Rol rol) throws Exception;

}
