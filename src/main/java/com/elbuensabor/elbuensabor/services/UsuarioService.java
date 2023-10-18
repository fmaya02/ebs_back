package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService extends BaseService<Usuario, Long> {

    public Page<Usuario> searchUsuarioRol(String rol, Pageable pageable) throws Exception;

    public Page<Usuario> searchUsuarioUsrnm(String username, Pageable pageable) throws Exception;
}
