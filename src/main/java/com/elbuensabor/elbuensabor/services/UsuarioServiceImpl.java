package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Persona;
import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.enums.Rol;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static String auth0counter = "0"; //esto simula la generacion de una id por auth0

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository, UsuarioRepository usuarioRepository){
        super(baseRepository);
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Page<Usuario> searchUsuarioRol(String rol, Pageable pageable) throws Exception {
        try{
            return usuarioRepository.searchUsuarioRol(rol, pageable);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Usuario> searchUsuarioUsrnm(String username, Pageable pageable) throws Exception {
        try{
            return usuarioRepository.searchUsuarioUsrnm(username, pageable);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario createUsuario(Persona persona, Rol rol) throws Exception {
        try {
            Usuario newUsuario = Usuario.builder()
                    .fechaAlta(new Date())
                    .rol(rol)
                    .username(""+persona.getNombre().toLowerCase()+persona.getApellido().toLowerCase()+"")
                    .contraseña("12345")
                    .auth0Id(auth0counter)
                    .build();
            usuarioRepository.save(newUsuario);
            return newUsuario;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
