package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Persona;
import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.enums.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService extends BaseService<Usuario, Long> {

    public Page<Usuario> searchUsuarioRol(String rol, Pageable pageable) throws Exception;

    public Page<Usuario> searchUsuarioUsrnm(String username, Pageable pageable) throws Exception;

    public Usuario createUsuario(Persona persona, Rol rol, String pswd1, String pswd2, List<Persona> mailcheck) throws Exception;
}
