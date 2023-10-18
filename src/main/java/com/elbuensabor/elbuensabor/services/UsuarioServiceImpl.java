package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository, UsuarioRepository usuarioRepository){
        super(baseRepository);
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Page<Usuario> searchUsuarioRol(String rol, Pageable pageable) throws Exception {
        try{
            Page<Usuario> usuarios = usuarioRepository.searchUsuarioRol(rol, pageable);
            return usuarios;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Usuario> searchUsuarioUsrnm(String username, Pageable pageable) throws Exception {
        try{
            Page<Usuario> usuarios = usuarioRepository.searchUsuarioUsrnm(username, pageable);
            return usuarios;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
